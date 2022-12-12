
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.annotation.tailrec

case class File(name: String, size: Int = 0)

case class Dir(name: String, parent: String, parent_path: String, var size: Int = 0, var files: List[File] = List[File]())

class Graph():
    var Dirs: Map[String, Dir] = Map[String, Dir]()

    def add_dir(name: String, parent: String, parent_path: String) =
        val path = parent_path + "_" + name
        Dirs(path) = Dir(name, parent, parent_path)
        
    def add_file(name: String, path: String, size: Int) = 
        Dirs(path).files = Dirs(path).files :+ File(name, size)

    def get_dir_size(path: String): Int =
        val children = Dirs.filter((t) => t._2.parent_path == path)
        val child_size = children.map((t) => get_dir_size(t._1)).sum
        Dirs(path).size = Dirs(path).files.map(_.size).sum + child_size
        Dirs(path).size

class Solver_07:

    def solve() =
        val input = Helper().read_input(7)(0)
        val graph = Graph()
        
        graph.add_dir("/", "", "")
        graph.add_dir("c", "/", "_/")
        graph.add_file("f1", "_/", 100)
        graph.add_file("f2", "_/", 100)
        graph.add_file("f3", "_/", 100)
        graph.add_file("f4", "_/_c", 400)
        println(graph.get_dir_size("_/"))