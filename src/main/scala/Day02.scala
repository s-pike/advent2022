import scala.io.Source._
import scala.collection.mutable.ListBuffer

class Solver_02:
    def read_input(day_num: Int): List[String] =
        val puzzle_dir = f"day-$day_num%02d/input.txt"
        val bufferedSource = fromResource(puzzle_dir)
        val input = (for (line <- fromResource(puzzle_dir).getLines()) yield line).toList
        bufferedSource.close
        input

    def solve() =
        val input = read_input(1)


