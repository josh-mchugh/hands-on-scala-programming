println("Relative Paths")
println(s"os.RelPath(\"post\"): ${os.RelPath("post")}")
println(s"os.RelPath(\"../hello/world\"): ${os.RelPath("../hello/world")}")

val helloRelPath = os.RelPath("../hello")
println(s"helloRelPath: $helloRelPath")
println(s"os.home / helloRelPath: ${os.home / helloRelPath}")
println(s"hellRelPath / os.RelPath(\"post\"): ${helloRelPath / os.RelPath("post")}")

val githubPath = os.Path("/Users/lihaoyi/GitHub")
val usersPath = os.Path("/Users")
println(s"githubPath.relativeTo(usersPath): ${githubPath.relativeTo(usersPath)}")
println(s"usersPath.relativeTo(githubPath): ${usersPath.relativeTo(githubPath)}")