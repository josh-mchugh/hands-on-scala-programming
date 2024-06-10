println("Combining Operation Examples")

def stdDev(a: Array[Double]): Double =
  val mean = a.sum / a.length
  val squareErrors = a.map(_ - mean).map(x => x * x)
  math.sqrt(squareErrors.sum / a.length)

println(s"stdDev(Array(1, 2, 3, 4, 5)): ${stdDev(Array(1, 2, 3, 4, 5))}")
println(s"stdDev(Array(3, 3, 3)): ${stdDev(Array(3, 3, 3))}")

def isValidSudoku(grid: Array[Array[Int]]): Boolean =
  !Range(0, 9).exists { i =>
    val row = Range(0, 9).map(grid(i)(_))
    val col = Range(0, 9).map(grid(_)(i))
    val square = Range(0, 9).map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3))
    row.distinct.length != row.length ||
      col.distinct.length != col.length ||
      square.distinct.length != square.length
  }

val sudoku = Array(
  Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
  Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
  Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
  Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
  Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
  Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
  Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
  Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
  Array(3, 4, 5, 2, 8, 6, 1, 7, 9)
)
println(s"Is valid sudoku: ${isValidSudoku(sudoku)}")
