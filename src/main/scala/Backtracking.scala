package pawntour {

  import pawntour.Backtracking.Rotation.Rotation
  import pawntour.Backtracking._
  import pawntour.pawntourBase._

  object Backtracking {

    //type Rotation = String
    val DefaultDirection = "N"

    object Rotation extends Enumeration {
      type Rotation = Value
      val LeftRotation, RightRotation = Value
    }

    def apply(dimension: Int, direction: Direction = DefaultDirection,
    rotation: Rotation = Rotation.RightRotation): Backtracking =
      new Backtracking(dimension, direction, rotation)

    def movesInDirection(moves: Seq[(Direction, Move)], direction: Direction): Seq[Move] = {
      direction match{
        case DefaultDirection => moves.map { m => m._2 }
        case _ => {
          val index = moves.indexWhere(t => t._1 == direction)
          (moves.slice(index, moves.size) ++ moves.slice(0, index))map { m => m._2 }
        }
      }
    }
  }

  class Backtracking(val dim:Int, direction: Direction = DefaultDirection,
                rotation: Rotation = Rotation.RightRotation) extends pawntourBase(dim) {


    def run(startTile: String = "0,0"): (Int, pawntourBase.Path) = {

      val tile = startTiles(startTile)
      val moves = movesInDirection(pawntourBase.Moves, direction)
      val nextMoves = (path: Path) => {
        validMoves(path, rotateMoves(path, moves, rotation))
      }
      findPath(tile, nextMoves)
    }

    def findPath(previousPath: Path, selectMoves: Path => Seq[Move], iterations: Int = 0): (Int, Path) = {
      var path = previousPath
      var iter = iterations + 1
      if (iterations < maxIterations) {
        val availableMoves = selectMoves(path)
        for (move <- availableMoves
        ) {
          val nextPath = previousPath ++ Array(nextTile(previousPath.last, move))
          val (foundIterations, foundPath) = findPath(nextPath, selectMoves, iter)
          if (foundPath.size == requiredPathLength) return (foundIterations, foundPath)
          iter = foundIterations
          if (foundPath.size > path.size) path = foundPath
        }
      }
      (iter, path)
    }

    //prepare to try other alternative moves (rotate set of moves in given direction around last move)
    def rotateMoves(path: Path, moves: Seq[Move], rotation: Rotation): Seq[Move] = {
      if (path.size > 1) {
        val previousMoveIndex = moves.indexWhere { m => m == moveBetweenTiles(path(path.size - 2), path.last) }
        if (rotation == Rotation.RightRotation ){
            val clockwise = moves.slice(previousMoveIndex, moves.size)
            val antiClockwise = moves.slice(0, previousMoveIndex)
            clockwise ++ antiClockwise
          }
        else if (rotation == Rotation.LeftRotation)  {
            val antiClockwise = moves.slice(0, previousMoveIndex + 1).reverse
            val clockwise = moves.slice(previousMoveIndex + 1, moves.size).reverse
            antiClockwise ++ clockwise
          }
        else {
            throw new IllegalArgumentException(s"Unsupported rotation ${rotation}")
          }
      } else {
        // First move
        moves
      }
    }
  }

}
