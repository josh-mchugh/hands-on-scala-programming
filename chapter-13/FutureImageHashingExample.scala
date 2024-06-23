import $ivy.`at.favre.lib:bcrypt:0.9.0`
import at.favre.lib.crypto.bcrypt.{BCrypt, LongPasswordStrategies}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

println("Future Image Hashing Example")

val hasher = BCrypt.`with`(LongPasswordStrategies.hashSha512(BCrypt.Version.VERSION_2A))

def hash(name: String) =
  val salt = name.take(16).padTo(16, ' ').getBytes
  val bytes = hasher.hash(17, salt, os.read.bytes(os.pwd / name))
  new String(bytes)

//println(s"hash(\"coffee1.jpg\"): ${hash("coffee1.jpg")}")
//println(s"hash(\"coffee2.jpg\"): ${hash("coffee2.jpg")}")

//val f1 = Future( { "hello" + 123 + "world" } )
//val f2 = Future( { hash("coffee1.jpg") } )
//println(s"f1: $f1")
//println(s"f2: ${Await.result(f2, Duration.Inf)}")

val f = Future( { hash("coffee1.jpg") } )
val result = hash("coffee2.jpg")
//println(s"result: $result")

val backgroundResult = Await.result(f, Duration.Inf)
//println(s"backgroundResult: $backgroundResult")

val (hashes, duration) = time {
  val futures = 
    for p <- os.list(os.pwd).filter(_.last.contains(".jpg")) yield
      Future ( {
        println(s"Hashing: $p")
        hash(p.last)
      })
  futures.map(Await.result(_, Duration.Inf))
}

