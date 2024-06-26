println("Queries Example")

val findSome = Array(1, 2, 3, 4, 5, 6, 7).find(i => i % 2 == 0 && i > 4)
println(s"findSome: $findSome")
val findNone = Array(1, 2, 3, 4, 5, 6, 7).find(i => i % 2 == 0 && i > 10)
println(s"findNone: $findNone")
val existsTrue = Array(1, 2, 3, 4, 5, 6, 7).exists(x => x > 1)
println(s"existsTrue: $existsTrue")
val existsFalse = Array(1, 2, 3, 4, 5, 6, 7).exists(_ < 0)
println(s"existsFalse: $existsFalse")
