import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import pawntour.pawntourBase
import pawntour.pawntourBase.Tile

object TileSpecification extends Properties("TileConfig") {

  val myGen = for {
    dim <- Gen.choose(1, 20)
    //0 - indexed
    x <- Gen.choose(0, dim - 1)
    y <- Gen.choose(0, dim - 1)
  } yield (dim, x, y)

  property("Tile always should be on board when coords are lower than dimension") = forAll(myGen) { input => {
    pawntourBase.tileIsOnBoard(Tile(input._2, input._3), input._1)
  }
  }

}
