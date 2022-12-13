
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.annotation.tailrec

case class File(name: String, size: Int = 0)

case class Dir(name: String, parent_path: List[String], var size: Int = 0, var files: List[File] = List[File]())

case class Cmd(cmd_type: String, name: String = "", size: Int = 0)

class Graph():
    var Dirs: Map[List[String], Dir] = Map[List[String], Dir]()

    def add_dir(name: String, parent_path: List[String]): List[String] =
        val path: List[String] = parent_path ::: List[String](name)
        Dirs(path) = Dir(name, parent_path)
        path
        
    def add_file(name: String, path: List[String], size: Int) = 
        Dirs(path).files = Dirs(path).files :+ File(name, size)

    def get_dir_size(path: List[String]): Int =
        val children = Dirs.filter((t) => t._2.parent_path == path)
        val child_size = children.map((t) => get_dir_size(t._1)).sum
        Dirs(path).size = Dirs(path).files.map(_.size).sum + child_size
        Dirs(path).size

    def _parse_cmd(cmd: String): Cmd =
        cmd match
            case "$ cd .." => Cmd("cd_up")
            case cmd if cmd.substring(0, 4) == "$ cd" => Cmd("cd", cmd.substring(5, cmd.size))
            case "$ ls" => Cmd("ls")
            case cmd if cmd.substring(0, 4) == "dir " => Cmd("dir", cmd.split(" ")(1))
            case _ => Cmd("file", cmd.split(" ")(1), cmd.split(" ")(0).toInt)

    def build(console: scala.collection.mutable.Queue[String]) =
        var cur_path: List[String] = List[String]()
        while console.nonEmpty do
            var raw_cmd = console.dequeue
            var cmd = _parse_cmd(raw_cmd)
            cmd.cmd_type match
                case "cd_up" => cur_path = cur_path.dropRight(1)
                case "cd" => cur_path = add_dir(cmd.name, cur_path)
                case "file" => add_file(cmd.name, cur_path, cmd.size)
                case _ => 
             

class Solver_07:

    def solve() =
        val input = Helper().read_input(7)
        val graph = Graph()
        val console = scala.collection.mutable.Queue[String]() 
        console.enqueueAll(input)
        graph.build(console)
        //graph.Dirs.foreach(println)

        //println(graph.Dirs(List("/")))
        println(graph.get_dir_size(List("/")))

        //total size
        println(graph.Dirs.filter((t) => t._2.size <= 100000).map((t) => t._2.size).sum)

        /*
        graph.add_dir("/", List[String]())
        graph.add_dir("c", List("/"))
        graph.add_file("f1", List("/"), 100)
        graph.add_file("f2", List("/"), 100)
        graph.add_file("f3", List("/"), 100)
        graph.add_file("f4", List("/", "c"), 400)
        graph.Dirs.foreach(println)
        println(graph.get_dir_size(List("/")))
        */