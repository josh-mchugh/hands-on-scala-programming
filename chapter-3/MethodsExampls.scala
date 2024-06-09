println("Method and Function Examples")
def printHello(times: Int = 0) =
  println("hello " + times)

def hello(i: Int = 0) = "hello " + i

printHello(1)
// argument name is provided explicity
printHello(times = 2)
// Default value
printHello()

println(s"hello(1): ${hello(1)}")
println(s"hello(): ${hello()}")
val helloHello = hello(123) + " " + hello(456);
println(s"hello(123) + \" \" + hello(456): $helloHello")
println(s"helloHello.reverse: ${helloHello.reverse}")
