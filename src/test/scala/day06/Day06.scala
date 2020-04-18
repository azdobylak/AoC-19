import org.scalatest._
import day06.Puzzle

class Day06Puzzle extends FlatSpec{
    assert(Puzzle.solve_first(
        List("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G",
        "G)H", "D)I", "E)J", "J)K", "K)L")) == 42)
}
