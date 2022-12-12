
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.annotation.tailrec

case class File(name: String, size: Int = 0)

case class Dir(name: String, parent: String, parent_path: String, var size: Int = 0, var files: List[File] = List[File]())

case class Cmd(cmd_type: String, name: String = "", size: Int = 0)

class Graph():
    var Dirs: Map[String, Dir] = Map[String, Dir]()

    def add_dir(name: String, parent: String, parent_path: String): String =
        val path = parent_path + "_" + name
        Dirs(path) = Dir(name, parent, parent_path)
        path
        
    def add_file(name: String, path: String, size: Int) = 
        Dirs(path).files = Dirs(path).files :+ File(name, size)

    def get_dir_size(path: String): Int =
        val children = Dirs.filter((t) => t._2.parent_path == path)
        val child_size = children.map((t) => get_dir_size(t._1)).sum
        Dirs(path).size = Dirs(path).files.map(_.size).sum + child_size
        Dirs(path).size

    def _parse_cmd(cmd: String): Cmd =
        cmd match
            case "$ cd .." => Cmd("cd_up")
            case cmd if cmd.substring(0, 4) == "$ cd" => Cmd("cd", cmd.substring(4, cmd.size))
            case "$ ls" => Cmd("ls")
            case cmd if cmd.substring(0, 4) == "dir " => Cmd("dir", cmd.split(" ")(1))
            case _ => Cmd("file", cmd.split(" ")(1), cmd.split(" ")(0).toInt)

    def build(console: scala.collection.mutable.Queue[String]) =

        while console.nonEmpty do
            val cmd = console.dequeue
            val parsed_cmd = _parse_cmd(cmd)
            println(parsed_cmd)

class Solver_07:

    def solve() =
        val input = Helper().read_input(7)
        val graph = Graph()
        val console = scala.collection.mutable.Queue[String]() 
        console.enqueueAll(input)
        graph.build(console)

        graph.add_dir("/", "", "")
        graph.add_dir("c", "/", "_/")
        graph.add_file("f1", "_/", 100)
        graph.add_file("f2", "_/", 100)
        graph.add_file("f3", "_/", 100)
        graph.add_file("f4", "_/_c", 400)
        println(graph.get_dir_size("_/"))