import $ivy.`org.jsoup:jsoup:1.17.2`, org.jsoup.*
import scala.jdk.CollectionConverters.*

val doc = Jsoup.connect("https://en.wikipedia.org/").get()
val headlines = doc.select("#mp-itn b a").asScala
val titleLinks = for headline <- headlines yield (headline.attr("title"), headline.attr("href"))
println(s"titleLinks: $titleLinks")
