println("Immutable Maps Example")

val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
println(s"m: $m")
println(s"m.contains(\"two\"): ${m.contains("two")}")
println(s"m(\"two\"): ${m("two")}")

val getSome = m.get("one")
println(s"m.get(\"one\"): $getSome")
val getNone = m.get("four")
println(s"m.get(\"four\"): $getNone")

val convertedMap = Vector(("one", 1), ("two", 2), ("three", 3)).toMap
println(s"convertedMap: $convertedMap")

for (k, v) <- convertedMap do println(s"$k $v")
