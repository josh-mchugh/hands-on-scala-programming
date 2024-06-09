println("Options")

def hello(title: String, firstName: String, lastNameOpt: Option[String]) =
  lastNameOpt match
    case Some(lastName) => println(s"Hello $title. $lastName")
    case None => println(s"Hello $firstName")

hello("Mr", "Haoyi", None)
hello("Mr", "Haoyi", Some("Li"))

println(s"Some(\"Li\").getOrElse(\"<unknown>\"): ${Some("Li").getOrElse("<unknown>")}")
println(s"None.getOrElse(\"<unknown>\"): ${None.getOrElse("<unknown>")}")

def hello2(name: Option[String]) =
  for s <- name do
    println(s"Hello $s")

hello2(None)
hello2(Some("Haoyi"))

def nameLength(name: Option[String]) =
  name.map(_.length).getOrElse(-1)

println(s"nameLength(Some(\"Haoyi)): ${nameLength(Some("Haoyi"))}")
println(s"nameLength(None): ${nameLength(None)}")
