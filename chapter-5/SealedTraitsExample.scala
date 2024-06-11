println("Sealed Traits Example")

sealed trait Point
case class Point2D(x: Double, y: Double) extends Point
case class Point3D(x: Double, y: Double, z: Double) extends Point

def hypotenuse(p: Point) =
  p match
    case Point2D(x, y) => math.sqrt(x * x + y * y)
    case Point3D(x, y, z) => math.sqrt(x * x + y * y + z * z)

val points = Array(Point2D(1, 2), Point3D(4, 5, 6))

for p <- points do println(hypotenuse(p))
