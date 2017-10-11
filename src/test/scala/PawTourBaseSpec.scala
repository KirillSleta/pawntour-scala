package pawntour.tests {

  import org.scalatest.FlatSpec
  import pawntour.pawntourBase
  import pawntour.pawntourBase.Tile

  class pawntourBaseSpec extends FlatSpec {
    "A Tile" should "be on board if coords are lower than dimensions" in {
      val dim = 10
      val tile = Tile(1,9)

      val result = pawntourBase.tileIsOnBoard(tile, dim)
      assert(result==true)
    }
    it should "be not on board if coords are greater than board dimensions" in {
      val dim = 8
      val tile = Tile(1,9)

      val result = pawntourBase.tileIsOnBoard(tile, dim)
      assert(result==false)
    }
    it should "be visited if exists in path earlier" in {
      val tile = Tile(2,2)
      val path = Array(tile,Tile(3,3))
      val result = pawntourBase.tileVisited(path, tile)
      assert(result == true)
    }
  }

}