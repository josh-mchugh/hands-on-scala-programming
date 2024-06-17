println("Streaming Contributors Example")

val gitLog = os.proc("git", "log").spawn()
val grepAuthors = os.proc("grep", "Author: ").spawn(stdin = gitLog.stdout)
val output = grepAuthors.stdout.lines().distinct

println(s"authors: $output")