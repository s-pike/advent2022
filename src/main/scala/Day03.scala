
import scala.io.Source._
import scala.collection.mutable.ListBuffer

class Solver_03:
    def read_input(day_num: Int): List[String] =
        val h = Helper()
        h.read_input(day_num)

    def matches(l_split: (String, String)): Set[Char] =
        val m = l_split(0).filter(l_split(1) contains _)
        m.toSet

    def split_packing_list(l: String): (String, String) =
        val l_split: (String, String) = l.splitAt(l.length/2)
        l_split

    def priority_val(items: Set[Char]): Int =
        val item_labels = ('a' to 'z').toList ++ ('A' to 'Z').toList
        val item_priorities = item_labels.zip(1 to 52).toMap
        items.map(item_priorities(_)).sum

    def find_badge(packing_lists: List[String]): Set[Char] = 
        packing_lists.toList.reduceLeft((x,y)=>x.filter(y contains _)).toSet

    def solve() =
        val input = read_input(3)
        println(input.map(split_packing_list).map(matches).map(priority_val).sum)
        println(input.grouped(3).map(find_badge).map(priority_val).sum)