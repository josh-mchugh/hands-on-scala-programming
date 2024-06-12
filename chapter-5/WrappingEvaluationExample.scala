println("Wrapping Evaluation Example")
def measureTime(f: => Unit) =
  val start = System.currentTimeMillis()
  f
  val end = System.currentTimeMillis()
  println(s"Evaluation took ${end - start} milliseconds")

measureTime(new Array[String](10 * 1000 * 1000).hashCode())

measureTime {
  new Array[String](100 * 1000 * 1000).hashCode()
}












