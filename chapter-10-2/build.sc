import $ivy.`com.lihaoyi::scalatags:0.13.1`, scalatags.Text.all._
import $ivy.`org.commonmark:commonmark:0.22.0`
import mill._

def mdNameToHtml(name: String) = name.replace(" ", "-").toLowerCase + ".html"

val postInfo = interp.watchValue {
  os.list(os.pwd / "post")
    .map { p =>
      val s"$prefix - $suffix.md" = p.last
      (prefix, suffix, p)
    }
    .sortBy(_._1.toInt)
}

def bootstrap = T {
  os.write(
    T.dest / "bootstrap.css",
    requests.get("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css").text()
  )
  PathRef(T.dest / "bootstrap.css")
}

def renderMarkdown(s: String) = {
  val parser = org.commonmark.parser.Parser.builder().build()
  val document = parser.parse(s)
  val renderer = org.commonmark.renderer.html.HtmlRenderer.builder().build()
  renderer.render(document)
}

def renderHtmlPage(dest: os.Path, boostrapUrl: String, contents: Frag*) = {
  os.write(
    dest,
    doctype("html")(
      html(
        head(
          link(rel := "stylesheet", href := boostrapUrl)
        ),
        body(contents)
      )
    )
  )
  PathRef(dest)
}

object post extends Cross[PostModule](postInfo.map(_._1))
trait PostModule extends Cross.Module[String] {
  val Some((_, suffix, markdownPath)) = postInfo.find(_._1 == crossValue)
  def path = T.source(markdownPath)
  def preview = T {
    renderMarkdown(os.read.lines(path().path).takeWhile(_.nonEmpty).mkString("\n"))
  }
  def render = T {
    renderHtmlPage(
      T.dest / mdNameToHtml(suffix),
      "../boostrap.css",
      h1(
        a(href := "/index.html")("Blog"), "/", suffix
      ),
      raw(renderMarkdown(os.read(path().path)))
    )
  }
}

def links = T.input { postInfo.map(_._2) }
val posts = T.sequence(postInfo.map(_._1).map(post(_).render))
val previews = T.sequence(postInfo.map(_._1).map(post(_).preview))

def index = T {
  renderHtmlPage(
    T.dest / "index.html",
    "boostrap.css",
    h1("Blog"),
    for ((suffix, preview) <- links().zip(previews()))
    yield frag(
      h2(
        a(href := (s"post/${mdNameToHtml(suffix)}"))(suffix)
      ),
      raw(preview)
    )
  )
}

def dist = T {
  for (post <- posts()) {
    os.copy(post.path, T.dest / "post" / post.path.last, createFolders = true)
  }
  os.copy(index().path, T.dest / "index.html")
  os.copy(bootstrap().path, T.dest / "boostrap.css")
  PathRef(T.dest)
}






