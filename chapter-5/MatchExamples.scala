println("Match Examples")

// Matching on Ints
def dayOfWeek(x: Int) =
  x match
    case 1 => "Monday"
    case 2 => "Tuesday"
    case 3 => "Wednesday"
    case 4 => "Thursday"
    case 5 => "Friday"
    case 6 => "Saturday"
    case 7 => "Sunday"
    case _ => "Unknow"

println(s"dayOfWeek(5): ${dayOfWeek(5)}")
println(s"dayOfWeek(-1): ${dayOfWeek(-1)}")

// Matching on Strings
def indexOfDay(d: String) =
  d match
    case "Mon" => 1
    case "Tue" => 2
    case "Wed" => 3
    case "Thu" => 4
    case "Fri" => 5
    case "Sat" => 6
    case "Sun" => 7
    case _ => -1

println(s"indexOfDay(\"Fri\"): ${indexOfDay("Fri")}")
println(s"indexOfDay(\"???\"): ${indexOfDay("???")}")

// Matching on Tuple
for i <- Range.inclusive(1, 100) do
  val s = (i % 3, i % 5) match
    case (0, 0) => "FizzBuzz"
    case (0, _) => "Fizz"
    case (_, 0) => "Buzz"
    case _ => i
  println(s)

// Matching on Case Classes
case class Point(x: Int, y: Int)

def direction(p: Point) =
  p match
    case Point(0, 0) => "origin"
    case Point(_, 0) => "horizontal"
    case Point(0, _) => "vertical"
    case _ => "diagonal"

println(s"direction(Point(0, 0)): ${direction(Point(0, 0))}")
println(s"direction(Point(1, 1)): ${direction(Point(1, 1))}")
println(s"direction(Point(10, 0)): ${direction(Point(10, 0))}")

def splitDate(s: String) =
  s match
    case s"$day-$month-$year" => s"day: $day, mon: $month, yr: $year"
    case _ => "not a date"

println(s"splitDate(\"9-8-1965\"): ${splitDate("9-8-1965")}")
println(s"splitDate(\"9-8\"): ${splitDate("9-8")}")
