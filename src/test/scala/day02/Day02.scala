import org.scalatest._
import day02.Day02

class Day02FirstPuzzle extends FlatSpec{
    assert(Day02.solve_first(Array(1, 0, 0, 0, 99)).sameElements(Array(2, 0, 0, 0, 99)))
}
