println("Mapped Serializer Examples")

implicit val pathRw: upickle.default.ReadWriter[os.Path] =
  upickle.default.readwriter[String].bimap[os.Path](
    p => p.toString,
    s => os.Path(s)
  )

val str = upickle.default.write(os.pwd)
println(s"str: $str")

val path = upickle.default.read[os.Path](str)
println(s"path: $path")

val str2 = upickle.default.write(Array(os.pwd, os.home, os.root))
println(s"str2: $str2")

