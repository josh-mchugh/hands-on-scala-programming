println("Immutable Sets Example")

val s = Set(1, 2, 3)
println(s"s.contains(2): ${s.contains(2)}")
println(s"s.contains(4): ${s.contains(4)}")

val s2 = Set(1, 2, 3) + 4 + 5
println(s"s2: $s2")

val s3 = Set(1, 2, 3) - 2
println(s"s3: $s3")

val s4 = Set(1, 2, 3) ++ Set(2, 3, 4)
println(s"s4: $s4")

for i <- Set(1, 2, 3, 4, 5) do println(i)
