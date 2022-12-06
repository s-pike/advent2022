import scala.collection.mutable.Stack

class Solver_05:

    def parse_level(raw_level: String, n_stacks: Int): List[Char] = 
        val char_idxs: List[Int] = Range(0,n_stacks).toList.map(_*4+1)
        char_idxs.map(raw_level.charAt(_))
   
    def parse_stacks(raw: List[String], n_stacks: Int) = 
        val rev_raw = raw.reverse
        val char_idxs: List[Int] = Range(0,n_stacks).toList.map(_*4+1)
        val parsed_raw: List[List[Char]] = rev_raw.map(parse_level(_, n_stacks)).transpose.map(_.filter(_ != ' '))
        val stacks: List[Stack[Char]] = parsed_raw.map(new Stack[Char]().pushAll(_))
        stacks

    def parse_move(raw_move: String): List[Int] = 
        val re = "\\d+".r
        re.findAllIn((raw_move)).toList.map(_.toInt-1)

    def make_move(move: List[Int], stacks: List[Stack[Char]]) =
        val n_moves = move(0)
        val from = move(1)
        val to = move(2)
        for i <- (0 to n_moves)
        do
            stacks(to).push(stacks(from).pop)

    def solve() =
        val input = Helper().read_input(5)
        val max_start_height = 8
        val n_stacks = 9
        val raw_stacks = input.take(max_start_height)
        val stacks = parse_stacks(raw_stacks, n_stacks)
        stacks.foreach(println)

        val move_start = 11
        val raw_moves = input.drop(move_start-1)
        val moves = raw_moves.map(parse_move)
        moves.take(5).foreach(println)
        moves.foreach(make_move(_, stacks))
        val top_crates = stacks.map(_.pop)
        println(top_crates.mkString)