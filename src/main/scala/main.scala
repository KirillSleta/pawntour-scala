import pawntour.pawntourBase.Path
import pawntour.{Backtracking, Warnsdorff}

object pawntourAssignment {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      printHelp
      return
    }
    val startTile:String = {if (args.length == 3) {args(2) }else "0,0"}
    val dimensions = Integer.parseInt(args(1))
    val (iterations, path)  = args(0) match {
      case "B" => Backtracking(dimensions).run(startTile)
      case "W" => Warnsdorff(dimensions).run(startTile)
      case _ => {
        println("choose B - Backtracking or W - Warnsdorff")
        return
      }
    }
    println(s" iterations=${iterations} path.size=${path.size}")
    printPath(path, dimensions)
  }

  def printPath(path:Path, dim:Int):Unit = {
    val arr = Array.ofDim[Int](dim, dim)
    var index = 0
    for (tile <- path)
      {
        index = index + 1
        arr(tile.y)(tile.x) = index
      }
    for (g <- arr) println(g map (i => f"$i%4d") mkString)
  }

  def printHelp() = {
    println("usage: [B|W] [dimensions]")
  }
}
