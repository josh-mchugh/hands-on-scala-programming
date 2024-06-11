println("Mutable Maps Example")

val m = collection.mutable.Map("one" -> 1, "two" -> 2, "three" -> 3)
println(s"m: $m")
println(s"m.remove(\"two\"): ${m.remove("two")}")
m("five") = 5
println(s"m: $m")

println(s"m.getOrElseUpdate(\"three\", -1): ${m.getOrElseUpdate("three", -1)}")
println(s"m: $m")
println(s"m.getOrElseUpdate(\"four\", -1): ${m.getOrElseUpdate("four", -1)}")
println(s"m: $m")
