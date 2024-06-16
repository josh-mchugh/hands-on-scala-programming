println("Sub Path Examples")
println(s"os.SubPath(\"post\"): ${os.SubPath("post")}")

val p1 = os.Path("/Users/lihaoyi/Github")
println(s"p1: $p1")

val p2 = os.Path("/Users")
println(s"p2: $p2")

println(s"p1.subRelativeTo(p2): ${p1.subRelativeTo(p2)}")