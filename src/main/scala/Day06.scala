
class Solver_06:

    def string_unique_chars(s: String): Int =
        s.toSet.size

    def start_index(signal: String, match_len: Int): Int =
        signal.sliding(match_len).toList.map(string_unique_chars).indexWhere(_==match_len)+match_len

    def solve() =
        // input here is just the first line
        val test_input_1 = "bvwbjplbgvbhsrlpgdmjqwftvncz" // 5, 23
        val test_input_2 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" //10, 29
        val input = Helper().read_input(6)(0)

        println(start_index(input, 14))