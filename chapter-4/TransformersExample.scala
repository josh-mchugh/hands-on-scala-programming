println("Transformers Example")

// Transforms
val map = Array(1, 2, 3, 4, 5).map(i => i * 2)
println(s"map: $map")
val filter = Array(1, 2, 3, 4, 5).filter(i => i % 2 == 1)
println(s"filter: $filter")
val take = Array(1, 2, 3, 4, 5).take(2)
println(s"take: $take")
val slice = Array(1, 2, 3, 4, 5).slice(1, 4)
println(s"slice: $slice")
val distinct = Array(1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7, 8).distinct
println(s"distinct: $distinct")

val a = Array(1, 2, 3, 4, 5)
val a2 = a.map(x => x + 10)
println(s"a(0): ${a(0)}")
println(s"a2(0): ${a2(0)}")
