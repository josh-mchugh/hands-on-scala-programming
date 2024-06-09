println("Function Values")
var g: Int => Int = i => i + 1
println(s"g(10): ${g(10)}")
g = i => i * 2
println(s"g(10): ${g(10)}")


class Box(var x: Int):
  def update(f: Int => Int) = x = f(x)
  def printMsg(msg: String) =
    println(msg + x)

val b = Box(1)
b.printMsg("Hello")
println(s"b.update(i => i + 5)")
b.update(i => i + 5)
b.printMsg("Hello")
b.update(_ + 5)
b.printMsg("Hello")

def increment(i: Int) = i + 1
val b2 = Box(123)
b2.update(increment)
b2.update(x => increment(x))
b2.update(x => increment(x))
b2.update(increment(_))
b2.printMsg("result: ")


def myLoop(start: Int, end: Int)(callback: Int => Unit) =
  for i <- Range(start, end) do callback(i)

myLoop(5, 10) { i => println(s"i has value $i") }
