import $ivy.`org.jsoup:jsoup:1.17.2`, org.jsoup.*
import scala.jdk.CollectionConverters.*

val indexDoc = Jsoup.connect("https://developer.mozilla.org/en-US/docs/Web/API").get()
val links = indexDoc.select("h2#interfaces").nextAll.select("div.index a").asScala
val linkData = links.map(link => (link.attr("href"), link.text))

val articles =
  for (url, name) <- linkData
  yield
    println(s"Scraping $name")
    val doc = Jsoup.connect(s"https://developer.mozilla.org$url").get()
    val summary = doc.select("main#content > .main-page-content > .section-content").asScala.headOption
      match
        case Some(n) => n.text
        case None => ""
    val instanceProperties = doc.select("section[aria-labelledby='instance_properties'] dl dt").asScala
      .map(element => (
        element.text,
        element.nextElementSibling match
          case null => ""
          case x => x.text)
      )
    (url, name, summary, instanceProperties)

println(s"articles.length: ${articles.length}")
println(s"articles.map(_(5).length).sum: ${articles.map(article => article(3).length).sum}")
os.write.over(os.pwd / "docs.json", upickle.default.write(articles, indent = 4))
os.read(os.pwd / "docs.json")
