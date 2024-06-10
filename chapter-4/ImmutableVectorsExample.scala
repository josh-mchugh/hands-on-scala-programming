println("Immutable Vectors")

val v = Vector(1, 2, 3, 4, 5)
println(s"v: $v")
println(s"v(0): ${v(0)}")

val v2 = v.updated(2, 10)
println(s"v2: $v2")
println(s"v: $v")

val v3 = Vector[Int]()
println(s"v3: $v3")
val v4 = v3 :+ 1
println(s"v4: $v4")
val v5 = 4 +: v4
println(s"v5: $v5")
val v6 = v5.tail
println(s"v6: $v6")
