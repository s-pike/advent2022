class Node:
    private var _size: Int = 0
    private var _children: Array[Node] = new Array[Node](0)

    def size: Int = _size

    def size_(new_value:Int): Unit =
        _size = new_value

    def children: Array[Node] = _children

    def add_child(Node): Unit =
        _children +: Node


class Solver_07:

    def solve() =
        val input = Helper().read_input(7)(0)

        println(input)