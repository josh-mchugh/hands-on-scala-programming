println("Builders Example")

val b = Array.newBuilder[Int]
b += 1
b += 2
println(s"b: ${b.result().mkString(",")}")


