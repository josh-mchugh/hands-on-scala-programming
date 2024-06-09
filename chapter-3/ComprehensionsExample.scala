println("Comprehensions Example")
val a = Array(1, 2, 3, 4)
println(s"a: $a")
val a2 = for i <- a yield i * i
println(s"a2: $a2")
val a3 = for i <- a yield "hello " + i
println(s"a3: $a3")
val a4 = for i <- a; if i % 2 == 0 yield "hello " + i
println(s"a4: $a4")
val b1 = Array(1, 2)
val b2 = Array("hello", "world")
val flattened =
  for
    i <- b1;
    s <- b2
  yield s + i
println(s"flattened: $flattened")


val fizzbuzz =
  for i <- Range.inclusive(1, 100) yield
    if i % 3 == 0 && i % 5 == 0 then "FizzBuzz"
    else if i % 3 == 0 then "Fizz"
    else if i % 5 == 0 then "Buzz"
    else i.toString
println(s"fizzbuzz: $fizzbuzz")
