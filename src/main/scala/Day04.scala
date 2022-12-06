class Solver_04:
    def read_input(day_num: Int): List[String] =
        val h = Helper()
        h.read_input(day_num)

    def parse_line(ln: String) =
        ln.split(",").map(_.split("-").map(_.toInt))

    def a_in_b(a: Array[Int], b: Array [Int]): Boolean = 
        //a.map((b(0) to b(1)) contains _).reduce(_&&_)
        a.map((b(0) to b(1)) contains _).reduce(_||_)
    
    def x_y_overlap(ln: Array[Array[Int]]): Boolean =
        val x = ln(0)
        val y = ln(1)
        a_in_b(x,y) || a_in_b(y,x)

    def solve() =
        val input = read_input(4)
        println(input.map(parse_line).map(x_y_overlap).filter(_ == true).length)