import org.scalatest._
import day07.Puzzle


class Day07Part1 extends FlatSpec{
  val input1 = Array(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)
  "case 1" should "return max thruster signal 43210" in {
    assertResult(43210) {Puzzle.calculate_thruster_signal(
      input1,
      Array(4,3,2,1,0)) }

    assertResult(43210) {Puzzle.solve_first(input1) }
  }

  val input2 = Array(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0)
  "case 2" should "return max thruster signal 54321" in {
    assertResult(54321) {Puzzle.calculate_thruster_signal(
      input2,
      Array(0,1,2,3,4)) }

    assertResult(54321) {Puzzle.solve_first(input2) }
  }

  val input3 = Array(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0)
  "case 3" should "return max thruster signal 65210" in {
    assertResult(65210) {Puzzle.calculate_thruster_signal(
      input3,
      Array(1,0,4,3,2))
    }

    //assertResult(65210) {Puzzle.solve_first(
    //  input3)
    //}
  }
}
