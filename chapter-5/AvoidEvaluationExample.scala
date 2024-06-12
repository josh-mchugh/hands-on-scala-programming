println("Avoiding Evaluation Example")
var logLevel = 1
def log(level: Int, msg: => String) =
  if level > logLevel then println(msg)

log(2, "Hello " + 123 + " Word" )

logLevel = 3

log(2, "Hello " + 123 + " World")
