package pawntour {

  object pawntourBase {

    case class Tile(x: Int, y: Int) {
      override def toString() = s"(${x},${y})"
    }

    type Path = Seq[Tile]

    case class Move(x: Int, y: Int)

    type Direction = String

    val Moves: Array[(Direction, Move)] = Array(
      ("N", Move(0, -3)),
      ("NE", Move(2, -2)),
      ("E", Move(3, 0)),
      ("SE", Move(2, 2)),
      ("S", Move(0, 3)),
      ("SW", Move(-2, 2)),
      ("W", Move(-3, 0)),
      ("NW", Move(-2, -2))
    )

    //validate if given tile position is valid (on board of given dimension)
    def tileIsOnBoard(tile: Tile, dim:Int): Boolean = {
      tile.x >= 0 && tile.x < dim && tile.y >= 0 && tile.y < dim
    }

    def tileVisited(path: Path, tile: Tile): Boolean = {
      path.exists { t => t.x == tile.x && t.y == tile.y }
    }

    def startTiles(tileString: String): Path = {
      val tileName = tileString
      Array(parseTile(tileName))
    }

    def parseTile(tileString: String): Tile = {
      val Array(x, y) = tileString.split(",").map(Integer.parseInt)
      Tile(x, y)
    }

    def nextTile(currentTile: Tile, move: Move): Tile = Tile(
      (currentTile.x + move.x),
      (currentTile.y + move.y)
    )

    def moveBetweenTiles(fromTile: Tile, toTile: Tile): Move = {
      Move(
        (toTile.x - fromTile.x),
        (toTile.y - fromTile.y)
      )
    }
  }

  abstract class pawntourBase(val dimension:Int) {
    import pawntour.pawntourBase._

    val maxIterations = math.pow(dimension, 7)
    val requiredPathLength = dimension * dimension

    def validMoves(path: Path, moves: Seq[Move]): Seq[Move] = {
      moves.filter { move => moveIsValid(path, move) }
    }

    def moveIsValid(path: Path, move: Move): Boolean = {
      val tile = nextTile(path.last, move)
      tileIsOnBoard(tile, dimension) && !tileVisited(path, tile)
    }
  }
}