println("Case Classes Example")

case class Point(x: Int, y: Int):
  def z = x + y

val p = Point(1, 2)
println(s"p: $p")
println(s"p.x: ${p.x}")
println(s"p.y: ${p.y}")
println(s"p.z: ${p.z}")

println(s"p.toString: ${p.toString}")

val p2 = Point(1, 2)
println(s"p == p2: ${p == p2}")

val p3 = p.copy(y = 10)
println(s"p3: $p3")

val p4 = p3.copy(x = 20)
println(s"p4: $p4")
