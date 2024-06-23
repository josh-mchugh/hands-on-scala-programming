import $ivy.`org.asynchttpclient:async-http-client:2.5.2`
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

import java.util.concurrent.Executors

implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(8))
val asyncHttpClient = org.asynchttpclient.Dsl.asyncHttpClient()

println("Parallel Web Crawling")

def fetchLinks(title: String): Seq[String] =
  val resp = requests.get(
    "https://en.wikipedia.org/w/api.php",
    params= Seq(
      "action" -> "query",
      "titles" -> title,
      "prop" -> "links",
      "format" -> "json"
    )
  )

  for
    page <- ujson.read(resp)("query")("pages").obj.values.toSeq
    links <- page.obj.get("links").toSeq
    link <- links.arr
  yield link("title").str
end fetchLinks

def fetchAllLinksRec(startTitle: String, depth: Int): Set[String] =
  def rec(current: Set[String], seen: Set[String], recDepth: Int): Set[String] =
    if recDepth >= depth then seen
    else
      val futures = for title <- current yield Future(fetchLinks(title))
      val nextTitles = futures.map(Await.result(_, Inf)).flatten
      rec(nextTitles.filter(!seen.contains(_)), seen ++ nextTitles, recDepth + 1)
  end rec
  rec(Set(startTitle), Set(startTitle), 0)
end fetchAllLinksRec

def fetchLinksAsync(title: String)(implicit ec: ExecutionContext): Future[Seq[String]] =
  val p = Promise[String]()
  val listenableFut = asyncHttpClient.prepareGet("https://en.wikipedia.org/w/api.php")
    .addQueryParam("titles", title)
    .addQueryParam("action", "query")
    .addQueryParam("prop", "links")
    .addQueryParam("format", "json")
    .execute()

  listenableFut.addListener(() => p.success(listenableFut.get().getResponseBody), null)
  val scalaFut: Future[String] = p.future
  scalaFut.map { responseBody =>
    for
      page <- ujson.read(responseBody)("query")("pages").obj.values.toSeq
      links <- page.obj.get("links").toSeq
      link <- links.arr
    yield link("title").str
  }
end fetchLinksAsync

def fetchAllLinks(startTitle: String, depth: Int): Set[String] =
  var seen = Set(startTitle)
  var current = Set(startTitle)
  for i <- Range(0, depth) do
    val futures = for title <- current yield Future(fetchLinks(title))
    val nextTitleLists = futures.map(Await.result(_, Inf))
    current = nextTitleLists.flatten.filter(!seen.contains(_))
    seen = seen ++ current
  seen
end fetchAllLinks

def fetchAllLinksAsync(startTitle: String, depth: Int): Future[Set[String]] =
  def rec(current: Set[String], seen: Set[String], recDepth: Int): Future[Set[String]] =
    if recDepth >= depth then Future.successful(seen)
    else
      val futures = for title <- current yield fetchLinksAsync(title)
      Future.sequence(futures)
        .map { nextTitleLists =>
          val nextTitles = nextTitleLists.flatten
          rec(nextTitles.filter(!seen.contains(_)), seen ++ nextTitles, recDepth + 1)
        }
        .flatten
  end rec
  rec(Set(startTitle), Set(startTitle), 0)
end fetchAllLinksAsync

//val results = fetchAllLinksRec("Singapore", 2)
//println(s"results: $results")

val results = Await.result(fetchAllLinksAsync("Singapore", 3), Inf)
println(s"results: $results")
