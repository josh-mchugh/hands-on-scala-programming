println("Classes Examples")

class Foo(val x: Int):
  def printMsg(msg: String) = println(msg + x)

val f = Foo(1)
f.printMsg("hello")
println(s"f.x: ${f.x}")


class Bar(val x: Int):
  def printMsg(msg: String) = println(msg + x)

val b = Bar(1)
println(s"b.x: ${b.x}")

class Qux(var x: Int):
  def printMsg(msg: String) =
    x += 1
    println(msg + x)

val q = Qux(1)
q.printMsg("hello")
q.printMsg("hello")

class Baz(x: Int):
  val bangs = "!" * x
  def printMsg(msg: String) = println(msg + bangs)

val z = Baz(3)
z.printMsg("hello")
