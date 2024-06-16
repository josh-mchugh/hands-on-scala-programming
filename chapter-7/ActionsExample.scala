println("Actions Example")
println(s"os.write(os.pwd / \"new.txt\", \"Hello\"): ${os.write(os.pwd / "new.txt", "Hello")}")
println(s"os.list(os.pwd): ${os.list(os.pwd)}")
println(s"os.read(os.pwd / \"new.txt\"): ${os.read(os.pwd / "new.txt")}")

println("*" * 37)
println(s"os.move(os.pwd / \"new.txt\", os.pwd / \"newer.txt\"): ${os.move(os.pwd / "new.txt", os.pwd / "newer.txt")}")
println(s"os.list(os.pwd): ${os.list(os.pwd)}")

println("*" * 37)
println(s"os.remove(os.pwd / \"newer.txt\"): ${os.remove(os.pwd / "newer.txt")}")
println(s"os.list(os.pwd): ${os.list(os.pwd)}")