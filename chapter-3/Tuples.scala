val t = (1, true, "hello")
println(s"t: $t")
println(s"t(0): ${t(0)}")
println(s"t(1): ${t(1)}")
println(s"t(2): ${t(2)}")

val (a, b, c) = t
println(s"a: $a")
println(s"b: $b")
println(s"c: $c")
