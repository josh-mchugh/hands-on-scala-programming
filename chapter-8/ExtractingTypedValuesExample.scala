println("Extracting Typed Values Example")

val data = ujson.read(os.read(os.pwd / "ammonite-releases.json"))

val small = ujson.Arr(
  ujson.Obj("hello" -> "world", "answer" -> 42),
  true
)

small(0).obj.remove("hello")
small.arr.append(123)
println(s"small: $small")

val string = data(0)("url").str
println(s"url: $string")
val number = data(0)("author")("id").num.toInt
println(s"number: $number")
