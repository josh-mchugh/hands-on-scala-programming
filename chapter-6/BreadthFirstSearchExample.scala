val graph1 = Map(
  "a" -> Seq("b", "c"),
  "b" -> Seq("a"),
  "c" -> Seq("b")
)
println(s"graph1: $graph1")

val graph2 = Map(
  "a" -> Seq("b", "c"),
  "b" -> Seq("c", "d"),
  "c" -> Seq("d"),
  "d" -> Seq()
)
println(s"graph2: $graph2")

def search[T](start: T, graph: Map[T, Seq[T]]): Set[T] =
  val seen = collection.mutable.Set(start)
  val queue = collection.mutable.ArrayDeque(start)
  while queue.nonEmpty do
    val current = queue.removeHead()
    for next <- graph(current) if !seen.contains(next) do
      seen.add(next)
      queue.append(next)

  seen.to(Set)

println(s"search(start = \"c\", graph = graph1): ${search(start = "c", graph = graph1)}")
println(s"search(start = \"a\", graph = graph2): ${search(start = "a", graph = graph2)}")
println(s"search(start = \"c\", graph = graph2): ${search(start = "c", graph = graph2)}")
