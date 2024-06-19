println("Binary Serialization")

case class Author(login: String, id: Int, site_admin: Boolean)

implicit val authorRw: upickle.default.ReadWriter[Author] =
  upickle.default.macroRW[Author]

val blob = upickle.default.writeBinary(Author("haoyi", 31337, true))
println(s"blob: $blob")

val author = upickle.default.readBinary[Author](blob)
println(s"author: $author")

val data = Map(
  1 -> Nil,
  2 -> List(Author("haoyi", 1337, true), Author("lihaoyi", 31337, true))
)

val blob2 = upickle.default.writeBinary(data)
println(s"blob2: $blob2")

val map = upickle.default.readBinary[Map[Int, List[Author]]](blob2)
println(s"map: $map")

val unpackedBlob = upack.read(blob)
println(s"unpackedBlob: $unpackedBlob")

val unpackedBlob2 = upack.read(blob2)
println(s"unpackedBlob2: $unpackedBlob2")

val msg = upack.Obj(
  upack.Str("login") -> upack.Str("haoyi"),
  upack.Str("id") -> upack.Int32(31337),
  upack.Str("site_admin") -> upack.True
)

val blob3 = upack.write(msg)
println(s"blob3: $blob3")

val deserialized = upickle.default.readBinary[Author](blob3)
println(s"deserialized: $deserialized")
