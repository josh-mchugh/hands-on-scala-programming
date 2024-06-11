println("Mutable ArrayDeques Example")

val arrayDeque = collection.mutable.ArrayDeque(1, 2, 3, 4, 5)
println(s"arrayDeque: $arrayDeque")

println(s"arrayDeque.removeHead(): ${arrayDeque.removeHead()}")
println(s"arrayDeque.append(6): ${arrayDeque.append(6)}")
println(s"arrayDeque.removeHead(): ${arrayDeque.removeHead()}")
println(s"arrayDueue: $arrayDeque")
