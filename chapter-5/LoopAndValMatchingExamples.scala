println("Loop and Vals Example")

// Loop Example
val a = Array[(Int, String)]((1, "one"), (2, "two"), (3, "three"))

for (i, s) <- a do println(s + i)

// Case Class Example
case class Point(x: Int, y: Int)

val p = Point(123, 456)

val Point(x, y) = p
println(s"x: $x, y: $y")


// Val Example
val s"$first $second" = "Hello World"
println(s"first: $first, second: $second")

val flipped = s"$second $first"
println(flipped)
