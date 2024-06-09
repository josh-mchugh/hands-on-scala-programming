println("For Loops")
var total = 0
val items = Array(1, 10, 100, 1000)
for item <- items do total += item
println(s"total: $total")


total = 0
for i <- Range(0, 5) do
  println(s"Looping $i")
  total += i
println(s"total: $total")

val multi = Array(Array(1, 2, 3), Array(4, 5, 6))
for arr <- multi; i <- arr do
  println(s"multi: $i")

for arr <- multi; i <- arr; if i % 2 == 0 do
  println(s"multi guard: $i")
