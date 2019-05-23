import java.time.LocalDateTime

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

val fut1 = Future {
  Thread.sleep(5000)
  println("expensive computation"  + LocalDateTime.now)
  5
}
val fut2 = Future {
  Thread.sleep(2000)
  println("Finished first! " + LocalDateTime.now)
  42
}
val requestsAsync: List[Future[Int]] = List(fut1, fut2)
val responsesAsync: Future[List[Int]] = Future.sequence(requestsAsync)

// Async version
//responsesAsync foreach println
// Sync version

println("Before async"  + LocalDateTime.now)

Await.result(responsesAsync, 6 seconds).foreach(println)


println("After async")
