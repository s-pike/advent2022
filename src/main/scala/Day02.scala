import scala.io.Source._
import scala.collection.mutable.ListBuffer

class Solver_02:
    def read_input(day_num: Int): List[String] =
        val puzzle_dir = f"day-$day_num%02d/input.txt"
        val bufferedSource = fromResource(puzzle_dir)
        val input = (for (line <- fromResource(puzzle_dir).getLines()) yield line).toList
        bufferedSource.close
        input

    def score_round(move: String): Int =
        val my_plays = Map("X" -> 1, "Y" -> 2, "Z" -> 3)
        val results = Map(
            "AZ" -> 0, "BX" -> 0, "CY" -> 0,
            "AX" -> 3, "BY" -> 3, "CZ" -> 3,
            "AY" -> 6, "BZ" -> 6, "CX" -> 6
        )
        val my_play = move.takeRight(1)
        val score = results(move) + my_plays(my_play)
        score

    def solve() =
        val input = read_input(2)
        val moves: List[String] = input.map(_.replaceAll("\\s+", ""))
        val score: Int = moves.map(score_round(_)).sum
        println(s"Score: $score")
        




