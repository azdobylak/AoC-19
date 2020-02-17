package day04

import java.net.URL
import scala.io.Source


object Day04
{
    val inputPath = this.getClass.getResource("input.txt")

    def readInput(path: URL): (Int, Int)= {
        val input: Array[Int] = Source.fromURL(path).getLines.next.split("-").map(_.toInt)
        (input(0), input(1))
    }

    def main(args: Array[String]): Unit = {
        println(solve_first(readInput(inputPath)))
        println(solve_second(readInput(inputPath)))
    }

    def solve_first(input: (Int, Int)): Int = {
            (input._1 to input._2).count{v => isPasswordValid(v.toString)}
    }

    def solve_second(input: (Int, Int)): Int = {
        (input._1 to input._2).count{v => isPasswordValid(v.toString, restrictiveDoubles = true)}
    }

    def isPasswordValid(password: String, restrictiveDoubles: Boolean = false): Boolean = {
        val hasSixDigits: Boolean = (password.length == 6)
        val hasIncreasingDigitsOnly: Boolean = (password.sliding(2).forall(s => s(0) <= s(1)))
        var hasAtleastOneDouble: Boolean = false

        val adjacentPairsEquality: List[Boolean] = password.sliding(2).map{s => s(0) == s(1)}.toList

        if (restrictiveDoubles)
            hasAtleastOneDouble = (adjacentPairsEquality.sliding(3).exists { s => !s(0) && s(1) && !s(2) }
                || (adjacentPairsEquality(0) && (password.charAt(1) != password.charAt(2)))
                || (adjacentPairsEquality.last && (password.charAt(password.length - 2) != password.charAt(password.length - 3))))
        else
            hasAtleastOneDouble = (adjacentPairsEquality.contains(true))

        (hasSixDigits && hasIncreasingDigitsOnly && hasAtleastOneDouble)
    }

}
