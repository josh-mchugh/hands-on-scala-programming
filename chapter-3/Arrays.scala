println("Arrays")
val a = Array(1, 2, 3, 4)
println(s"a: $a")
println(s"a(0): ${a(0)}")
println(s"a(3): ${a(3)}")
val a2 = Array(
  "one", "two",
  "three", "four"
)
println(s"a2: $a2")
println(s"a2(1): ${a2(1)}")
val a3 = new Array[Int](4)
println(s"a3: ${a3.mkString(",")}")
a3(0) = 1
a3(2) = 100
println(s"a3: ${a3.mkString(",")}")
val multi = Array(Array(1, 2), Array(3, 4))
println(s"multi: ${multi}")
println(s"multi(0)(0): ${multi(0)(0)}")
println(s"multi(0)(1): ${multi(0)(1)}")
println(s"multi(1)(0): ${multi(1)(0)}")
println(s"multi(1)(1): ${multi(1)(1)}")
