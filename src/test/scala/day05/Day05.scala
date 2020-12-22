import org.scalatest._
import day05.Day05

class Day05Case1 extends FlatSpec{
  //position mode

  "case 1, position mode" should "output 0 if the input was 0 or 1 if input was non-zero" in {
    val input1 = Array(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9)
    assertResult(1) {Day05.solve_second(3, input1.clone())}
    assertResult(1) {Day05.solve_second(-1, input1.clone())}
    assertResult(0) {Day05.solve_second(0, input1.clone())}
  }
}

class Day05Case2 extends FlatSpec{
  //immediate mode
  "case 1, immediate mode" should "output 0 if the input was 0 or 1 if input was non-zero" in {
    val input2 = Array(3,3,1105,-1,9,1101,0,0,12,4,12,99,1)
    assertResult(1) {Day05.solve_second(5, input2.clone())}
    assertResult(1) {Day05.solve_second(-1, input2.clone())}
    assertResult(0) {Day05.solve_second(0, input2.clone())}
  }

  "case 2.1" should "return 1 if input is equal to 8 else 0" in {
    val input21 = Array(3,9,8,9,10,9,4,9,99,-1,8)
    assertResult(1) {Day05.solve_second(8, input21.clone())}
    assertResult(0) {Day05.solve_second(-1, input21.clone())}
    assertResult(0) {Day05.solve_second(9, input21.clone())}
  }
}

class Day05Case3 extends FlatSpec{
    val input3 = Array(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31, 1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104, 999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99)
  "case 3.1" should "output 999 if input value is below 8" in {
    assertResult(999) {Day05.solve_second(2, input3.clone())}
    assertResult(999) {Day05.solve_second(7, input3.clone())}
  }

  "case 3.2" should "output 1000 if input value is equal to 8" in {
    assertResult(1000) {Day05.solve_second(8, input3.clone())}
  }

  "case 3.3" should "output 1001 if input value is greater than 8" in {
    assertResult(1001) {Day05.solve_second(9, input3.clone())}
    assertResult(1001) {Day05.solve_second(57, input3.clone())}
  }

}
