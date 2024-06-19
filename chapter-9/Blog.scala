import $ivy.`com.lihaoyi::scalatags:0.13.1`, scalatags.Text.all.*
import $ivy.`org.commonmark:commonmark:0.22.0`

interp.watch(os.pwd / "post")
val postInfo = os.list(os.pwd / "post")
  .map { p =>
    val s"$prefix - $suffix.md" = p.last
    (prefix, suffix, p)
  }
  .sortBy(_(0).toInt)

os.remove.all(os.pwd / "out")
os.makeDir.all(os.pwd / "out" / "post")

val bootstrapCSS = link(
  rel := "stylesheet",
  href := "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
)

def mdNameToHtml(name: String) = name.replace(" ", "-").toLowerCase + ".html"

for (_, suffix, path) <- postInfo do
  val parser = org.commonmark.parser.Parser.builder().build()
  val document = parser.parse(os.read(path))
  val renderer = org.commonmark.renderer.html.HtmlRenderer.builder().build()
  val output = renderer.render(document)
  os.write(
    os.pwd / "out" / "post" / mdNameToHtml(suffix),
    doctype("html")(
      html(
        head(bootstrapCSS),
        body(
          h1(a(href := "../index.html")("Blog"), "/", suffix),
          raw(output)
        )
      )
    )
  )

os.write(
  os.pwd / "out" / "index.html",
  doctype("html")(
    html(
      body(
        head(bootstrapCSS),
        h1("Blog"),
        for (_, suffix, _) <- postInfo yield h2(a(href := (s"post/${mdNameToHtml(suffix)}"), suffix))
      )
    )
  )
)






