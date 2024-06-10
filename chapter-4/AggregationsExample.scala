println("Aggregations Example")

val arrayString = Array(1, 2, 3, 4, 5, 6, 7).mkString(",")
println(s"arrayString: $arrayString")

val arrayString2 = Array(1, 2, 3, 4, 5, 6, 7).mkString("[", ",", "]")
println(s"arrayString2: $arrayString2")

val foldLeftSum = Array(1, 2, 3, 4, 5, 6, 7).foldLeft(0)((x, y) => x + y)
println(s"foldLeftSum: $foldLeftSum")

val foldLeftProduct = Array(1, 2, 3, 4, 5, 6, 7).foldLeft(1)((x, y) => x * y)
println(s"foldLeftProduct: $foldLeftProduct")

val foldLeftProductShorthand = Array(1, 2, 3, 4, 5, 6, 7).foldLeft(1)(_ * _)
println(s"foldLeftProductShorthand: $foldLeftProductShorthand")

val grouped = Array(1, 2, 3, 4, 5, 6, 7).groupBy(_ % 2)
println(s"grouped(0): ${grouped(0).mkString(",")}")
println(s"grouped(1): ${grouped(1).mkString(",")}")
