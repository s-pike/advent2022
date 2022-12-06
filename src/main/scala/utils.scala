import scala.io.Source._


class Helper:
    def read_input(day_num: Int): List[String] =
        val puzzle_dir = f"day-$day_num%02d/input.txt"
        val bufferedSource = fromResource(puzzle_dir)
        val input = (for (line <- fromResource(puzzle_dir).getLines()) yield line).toList
        bufferedSource.close
        input