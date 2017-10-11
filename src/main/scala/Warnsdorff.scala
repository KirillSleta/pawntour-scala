package pawntour {

  import pawntour.pawntourBase._
  import pawntour.Warnsdorff._

  object Warnsdorff {
    def apply(dimension: Int): Warnsdorff =
      new Warnsdorff(dimension)
  }

  class  Warnsdorff(val dim:Int) extends pawntourBase(dim) {

    val availableMoves = Moves.map(_._2)

    def run(startTile: String = "0,0"): (Int, Path) = {
      val tile = startTiles(startTile)
      findPath(tile, nextMoves)
    }

    //get a move with minimal degree (minimum number of unvisited adjacent)
    def selectMove(aMoves:Seq[(Int, Move)]): Move = aMoves.minBy(_._1)._2

    //get a list of possible next moves with degree
    def nextMoves(path:Path):Seq[(Int, Move)] = {
      val moves = validMoves(path, availableMoves)
      for {move <- moves
        nextPath = path ++ Array(nextTile(path.last, move))
        pathMoves = validMoves(nextPath, availableMoves)
      }
        yield (pathMoves.size,move)
    }

    def findPath(previousPath: Path, selectMoves: Path => Seq[(Int, Move)], iterations: Int = 0): (Int, Path) = {
        val moves = selectMoves(previousPath)
        if(!moves.isEmpty) {
          val move = selectMove(moves)
          val nextPath = previousPath ++ Array(nextTile(previousPath.last, move))
          val (foundIterations, foundPath) = findPath(nextPath, selectMoves, iterations + 1)
          if (foundPath.size == requiredPathLength || foundPath.size > previousPath.size)
            (foundIterations, foundPath) else (foundIterations, previousPath)
        } else (iterations, previousPath)
    }
  }

}
