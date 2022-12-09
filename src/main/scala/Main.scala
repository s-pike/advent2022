import scala.io.Source._

@main def hello: Unit = 
  println("Hello world!")
  println(msg)
  val s = Solver_07()
  s.solve()
  println("Goodbye world!")

def msg = "I was compiled by Scala 3. :)"