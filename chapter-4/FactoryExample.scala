println("Factory Example")

val fill = Array.fill(5)("hello")
println(s"fill: $fill")
val tabulate = Array.tabulate(5)(n => s"hello $n")
println(s"tabulate: $tabulate")
val concatenating = Array(1, 2, 3) ++ Array(4, 5, 6)
println(s"concatenating: $concatenating")
