println("Combining Operations Example")

val results = os.walk(os.pwd).filter(os.isFile).map(p => (os.size(p), p)).sortBy(t => t(1)).take(5)
println(s"results: $results")