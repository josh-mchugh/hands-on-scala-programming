println("Subprocess Example")

val gitStatus = os.proc("git", "status").call()

println(s"gitStatus.exitCode: ${gitStatus.exitCode}")
println(s"gitStatus.out.text(): ${gitStatus.out.text()}")

for i <- 0 until 11 do
  os.proc("git", "checkout", "-B", i).call()

os.proc("git", "checkout", "main").call()

val gitBranches = os.proc("git", "branch").call().out.lines()
println(s"gitBranches: $gitBranches")

val otherBranches = gitBranches.collect { case s"  $branchName" => branchName }
println(s"otherBranches: $otherBranches")

for branch <- otherBranches do
  os.proc("git", "branch", "-D", branch).call()

println(s"git branches: ${os.proc("git", "branch").call().out.lines()}")