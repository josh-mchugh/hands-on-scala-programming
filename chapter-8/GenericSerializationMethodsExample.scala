println("Generic Serialization Methods Example")

case class Asset(id: Int, name: String)

implicit val assetRw: upickle.default.ReadWriter[Asset] =
  upickle.default.macroRW[Asset]

def myPrintJson[T: upickle.default.Writer](t: T) = println(upickle.default.write(t))

myPrintJson(Asset(1, "hello"))
myPrintJson(Seq(1, 2, 3))
myPrintJson(Seq(Asset(1, "hello"), Asset(2, "goodbye")))

def myReadJson[T: upickle.default.Reader](): T =
  print("Enter some JSON")
  upickle.default.read[T](scala.io.StdIn.readLine())

