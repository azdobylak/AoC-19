package day01

import scala.io.Source
import math._

object Day01
{
  val input = day01.Day01.getClass.getResource("input.txt")
  val lines: List[Int] = Source.fromURL(input).getLines().toList.map(_.toInt)

  def main(args: Array[String]): Unit = {
    println(solve_first(lines))
    println(solve_second(lines))
  }

  def requiredFuel(mass:Int) = math.ceil((mass / 3)).toInt - 2

  def solve_first(masses: List[Int]): Int = {
    return masses.map(requiredFuel).sum
  }

  def requiredFuelWithItsMass(mass: Int): Int = {
    if(mass > 0)
    {
      val fuel_mass: Int = scala.math.max(requiredFuel(mass), 0)
      return fuel_mass + requiredFuelWithItsMass(fuel_mass)
    }
    else
        return 0
  }

  def solve_second(masses: List[Int]): Int = {
    return masses.map(requiredFuelWithItsMass).sum
  }
}