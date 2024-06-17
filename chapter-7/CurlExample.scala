println("Curl Example")

val url = "https://api.github.com/repositories/107214500/releases"

os.proc("curl", url).call(stdout = os.pwd / "github.json")

val fileInfo = os.proc("ls", "-lh", "github.json").call().out.text()
println(s"fileInfo: $fileInfo")

os.proc("gzip").call(stdin = os.pwd / "github.json", stdout = os.pwd / "github.json.gz")

val gzFileInfo = os.proc("ls", "-lh", "github.json.gz").call().out.text()
println(s"gzFileInfo: $gzFileInfo")