class Solver_08:

    def tree_visible(t: List[Int], direction: String = "Left"): List[Boolean] =
        val h: List[Int] = if direction == "Right" then
            t.reverse
        else
            t
             
        val max_h: List[Int] = h.scanLeft(-1)((a,b) => List(a,b).max).dropRight(1)
        val t_visible: List[Boolean] = h.zip(max_h).map(_>_)
        if direction == "Right" then t_visible.reverse else t_visible
        // if direction == "Right" then max_h.reverse else max_h

    def solve() =
        val input = Helper().read_input(8)
        // val input = List("012345", "543230", "011210") //17 visible
        // val input = List("30373","25512","65332","33549","35390") //21 visible
        val trees: List[List[Int]] = input.map(_.toList.map(_.asDigit))
        val visible_left: List[List[Boolean]] = trees.map(tree_visible(_))
        val visible_right: List[List[Boolean]] = trees.map(tree_visible(_, direction="Right"))
        val visible_top: List[List[Boolean]] = trees.transpose.map(tree_visible(_)).transpose
        val visible_bottom: List[List[Boolean]] = trees.transpose.map(tree_visible(_, direction="Right")).transpose

        // println("left")
        // visible_left.foreach(println)
        // println("right")
        // visible_right.foreach(println)
        // println("top")
        // visible_top.foreach(println)
        // println("bottom")
        // visible_bottom.foreach(println)

        val all_visible: List[Boolean] = (
            visible_left.flatten lazyZip
            visible_right.flatten lazyZip
            visible_top.flatten lazyZip
            visible_bottom.flatten
        ).toList.map(_.toList.reduce(_||_))

        println(all_visible.filter(_==true).size)
    