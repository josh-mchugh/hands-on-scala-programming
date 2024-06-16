println("Streaming Example")

println("Print the lines of: ActionsExample.scala")
os.read.lines.stream(os.pwd / "ActionsExample.scala").foreach(println)

println("*" * 37)
println("Walk and stream the results of PWD")
os.walk.stream(os.pwd).foreach(println)