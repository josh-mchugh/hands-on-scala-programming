println("Manipulating JSON")

val jsonString = os.read(os.pwd / "ammonite-releases.json")
val data = ujson.read(jsonString)

val small = ujson.Arr(
  ujson.Obj("hello" -> "world", "answer" -> 42),
  true
)
println(ujson.write(small))

//os.write(os.pwd / "out.json", small)
//val outJson = os.read(os.pwd / "out.json")
//println(s"outJson: $outJson")


//println("*" * 37)
//println(s"data(0): ${data(0)}")

println("*" * 37)
val url = data(0)("url")
println(s"data(0)(\"url\"): $url")
val authorId = data(0)("author")("id")
println(s"data(0)(\"author\")(\"id\"): $authorId")
