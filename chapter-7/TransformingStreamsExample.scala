println("Transforming Streams Example")

val lines = os.read.lines.stream(os.pwd / "ActionsExample.scala")
  .filter(!_.startsWith("println(\"*\""))
  .toList

println(s"lines: $lines")

val lines2 = os.read.lines.stream(os.pwd / "ActionsExample.scala")
  .collect { case s"$str" => str }
  .toList

println(s"lines2: $lines2")