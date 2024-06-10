println("Immutable Lists Example")

val list = List(1, 2, 3, 4, 5)
println(s"list: $list")

val head = list.head
println(s"head: $head")

val tail = list.tail
println(s"tail: $tail")

val newList = 0 :: list
println(s"newList: $newList")

val newNewList = -1 :: list
println(s"newNewList: $newNewList")
