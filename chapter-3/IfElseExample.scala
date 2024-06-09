println("If-Else Example")
var total = 0
for i <- Range(0, 10) do
  if i % 2 == 0 then total += i
  else total += 2
println(s"total: $total")


total = 0
for i <- Range(0, 10) do
  total += (if i % 2 == 0 then i else 2)
println(s"total: $total")
