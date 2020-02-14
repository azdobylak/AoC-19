import org.scalatest._
import day01.Day01

class Day01FirstPuzzle extends FlatSpec{
    assert(Day01.requiredFuel(1969) == 654)
    assert(Day01.requiredFuel(100756) == 33583)

    assert(Day01.requiredFuelWithItsMass(1969) == 966)
    assert(Day01.requiredFuelWithItsMass(100756) == 50346)
}
