println("Serializing Case Classes")

case class Author(login: String, id: Int, site_admin: Boolean)
implicit val authorRW: upickle.default.ReadWriter[Author] = upickle.default.macroRW

val data = ujson.read(os.read(os.pwd / "ammonite-releases.json"))
val author = upickle.default.read[Author](data(0)("author"))
println(s"author: ${author.login}")

val author2 = upickle.default.read[Author](
  """{"login": "lihaoyi",  "id": 313373, "site_admin": true}"""
)
val author2String = upickle.default.write(author2)
println(s"author2String: $author2String")

val authorMap = upickle.default.read[Map[String, Author]](
"""{
     "haoyi": {"login": "lihaoyi", "id": 1337, "site_admin": true},
     "bot": {"login": "ammonite-bot", "id": 31337, "site_admin": false}
   }"""
)
println(s"authorMap: $authorMap")
