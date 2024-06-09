println("Strings")
val helloWorld = "hello world"
println(s"$helloWorld")
println(s"helloWorld.substring(0, 5): ${helloWorld.substring(0, 5)}")
println(s"helloWorld.substring(5, 10): ${helloWorld.substring(5, 10)}")
val hello = "hello"
val world = "world"
println(s"hello + 1 + ' ' + world + 2: ${hello + 1 + " " + world + 2}")
val x = 1
val y = 2
println(s"hello x world y: $hello $x $world $y")
println(s"hello {x + y} world {x - y}: $hello ${x + y} $world ${x - y}")
