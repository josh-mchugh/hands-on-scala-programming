println("Output ujson Example")

val output = ujson.Arr(
  ujson.Obj("hello" -> "world", "answer" -> 42),
  true
)

output(0)("hello") = "goodby"
output(0)("tags") = ujson.Arr("awesome", "yay", "wonderful")

println(output)
