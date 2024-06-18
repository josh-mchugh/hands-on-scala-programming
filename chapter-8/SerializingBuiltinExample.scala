println("Serializing Builtins Example")

val numbers = upickle.default.read[Seq[Int]]("[1,2,3,4]")
println(s"numbers: $numbers")

val numbersString = upickle.default.write(numbers)
println(s"numbersString: $numbersString")

val tuples = upickle.default.read[Seq[(Int, Boolean)]]("[[1, true], [2, false]]")
println(s"tuples: $tuples")

val tuplesString = upickle.default.write(tuples)
println(s"tuplesString: $tuplesString")

val input = """{"weasle": ["i", "am"], "baboon": ["i", "r"]}"""
val parsed = upickle.default.read[Map[String, Seq[String]]](input)
println(s"parsed: $parsed")

val parsedString = upickle.default.write(parsed)
println(s"parsedString: $parsedString")
