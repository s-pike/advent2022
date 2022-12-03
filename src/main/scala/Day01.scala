import scala.io.Source._
import scala.collection.mutable.ListBuffer

class Solver:
    def read_input(day_num: Int): List[String] =
        val puzzle_dir = f"day-$day_num%02d/input.txt"
        val bufferedSource = fromResource(puzzle_dir)
        val input = (for (line <- fromResource(puzzle_dir).getLines()) yield line).toList
        bufferedSource.close
        input
    
    def print_input() =  
        val lines = read_input(1)
        for line <- lines do println(line)

    def convert_input(input: List[String]): List[Int] = 

        /*
        val splitter: String => Boolean = s => s.isEmpty

        def splitList[A](l: List[A], p: A => Boolean):List[List[A]] = l match 
            case Nil => Nil
            case _ =>
                val (h, t) = l.span(a => !p(a))
                h :: splitList(t.drop(1), p)

        splitList(input, splitter)
    */
        var elf_cal: List[Int] = List[Int]()
        var elf_cals: List[Int] = List[Int]()
        for i <- input
        do 
            if i.nonEmpty then
                elf_cal = i.toInt :: elf_cal                
            else
                elf_cals = elf_cal.sum :: elf_cals
                elf_cal = List[Int]()

        
        elf_cals



    def solve() =
        val input = read_input(1)
        val elves_cals: List[Int] = convert_input(input)
        //val cal_sums: Seq[Int] = for e <- elves_cals do e.foreach(sum)
        println("solving...")
        println(elves_cals.max)
        println("solved")
