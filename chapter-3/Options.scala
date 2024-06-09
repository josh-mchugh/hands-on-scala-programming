println("Options")

def hello(title: String, firstName: String, lastNameOpt: Option[String]) =
  lastNameOpt match
    case Some(lastName) => println(s"Hello $title. $lastName")
    case None => println(s"Hello $firstName")

hello("Mr", "Haoyi", None)
hello("Mr", "Haoyi", Some("Li"))

println(s"Some(\"Li\").getOrElse(\"<unknown>\"): ${Some("Li").getOrElse("<unknown>")}")
println(s"None.getOrElse(\"<unknown>\"): ${None.getOrElse("<unknown>")}")
