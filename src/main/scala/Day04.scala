class Solver_04:
    def read_input(day_num: Int): List[String] =
        val h = Helper()
        h.read_input(day_num)

    def solve() =
        val input = read_input(4)
        input.foreach(println)