package pawntour.tests {

import org.scalatest.FlatSpec
import pawntour.{Backtracking, pawntourBase}
import pawntour.pawntourBase.Tile

class BacktrackingSpec extends FlatSpec{
  "movesInDirection" should "return moves starting from the given direction" in {
    for(move <- pawntourBase.Moves){
      val moves = Backtracking.movesInDirection(pawntourBase.Moves, move._1)
      assert(moves(0).equals(move._2))
    }

  }
}

}
