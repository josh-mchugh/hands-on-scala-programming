println("Mutable Sets Example")

val s = collection.mutable.Set(1, 2, 3)
println(s"s: $s")
println(s"s.contains(2): ${s.contains(2)}")
println(s"s.contains(4): ${s.contains(4)}")
println(s"s.add(4): ${s.add(4)}")
println(s"s.remove(1): ${s.remove(1)}")
println(s"s: $s")
