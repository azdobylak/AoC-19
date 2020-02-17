import org.scalatest._
import day04.Day04

class Day04Puzzle extends FlatSpec{
    assert(Day04.isPasswordValid("111111") == true)
    assert(Day04.isPasswordValid("223450") == false)
    assert(Day04.isPasswordValid("123789") == false)

    // is too short
    assert(Day04.isPasswordValid("12345") == false)

    // second part
    assert(Day04.isPasswordValid("112233", restrictiveDoubles=true) == true)
    assert(Day04.isPasswordValid("123444", restrictiveDoubles=true) == false)
    assert(Day04.isPasswordValid("111122", restrictiveDoubles=true) == true)
    assert(Day04.isPasswordValid("333666", restrictiveDoubles=true) == false)
}