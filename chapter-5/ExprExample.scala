println("Expr Example")

sealed trait Expr
case class BinOp(left: Expr, op: String, right: Expr) extends Expr
case class Literal(value: Int) extends Expr
case class Variable(name: String) extends Expr

def stringify(expr: Expr): String =
  expr match
    case BinOp(left, op, right) => s"(${stringify(left)} $op ${stringify(right)})"
    case Literal(value) => value.toString
    case Variable(name) => name

val smallExpr = BinOp(
  Variable("x"),
  "+",
  Literal(1)
)

println(s"stringify(smallExpr): ${stringify(smallExpr)}")

val largeExpr = BinOp(
  BinOp(Variable("x"), "+", Literal(1)),
  "*",
  BinOp(Variable("y"), "-", Literal(1))
)

println(s"stringify(largeExpr): ${stringify(largeExpr)}")

def evaluate(expr: Expr, values: Map[String, Int]): Int =
  expr match
    case BinOp(left, "+", right) => evaluate(left, values) + evaluate(right, values)
    case BinOp(left, "-", right) => evaluate(left, values) - evaluate(right, values)
    case BinOp(left, "*", right) => evaluate(left, values) * evaluate(right, values)
    case Literal(value) => value
    case Variable(name) => values(name)

println(s"evaluate(smallExpr, Map(\"x\" -> 10)): ${evaluate(smallExpr, Map("x" -> 10))}")
println(s"evaluate(largeExpr, Map(\"x\" -> 10, \"y\" -> 20)): ${evaluate(largeExpr, Map("x" -> 10, "y" -> 20))}")
println(s"")
