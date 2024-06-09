println("Traits Example")

trait Point:
  def hypotenuse: Double

class Point2D(x: Double, y: Double) extends Point:
  def hypotenuse = math.sqrt(x * x + y * y)

class Point3D(x: Double, y: Double, z: Double) extends Point:
  def hypotenuse = math.sqrt(x * x + y * y + z * z)

val points: Array[Point] = Array(Point2D(1, 2), Point3D(4, 5, 6))
for p <- points do println(p.hypotenuse)
