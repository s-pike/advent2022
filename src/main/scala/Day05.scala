class Solver_05:
    def read_input(day_num: Int): List[String] =
        val h = Helper()
        h.read_input(day_num)

    def solve() =
        val input = Helper().read_input(5)
        input.take(5).foreach(println)