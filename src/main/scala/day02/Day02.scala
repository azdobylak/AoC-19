package day02

import java.net.URL
import scala.io.Source
import scala.util.control.Breaks._


object Day02
{
    val inputPath = this.getClass.getResource("input.txt")

    def readInput(path: URL): Array[Int] = {
        val line: String = Source.fromURL(path).getLines().next()
        return line.split(",").map(_.toInt)
    }

    def main(args: Array[String]): Unit = {
        val resultPuzzle1 = solve_first(readInput(inputPath))
        println(resultPuzzle1.mkString(", "))
        println(solve_second(readInput(inputPath), 19690720))
    }

    def solve_first(input: Array[Int]): Array[Int] = {
        input(1) = 12
        input(2) = 2
        processCodes(input)
    }

    def solve_second(input: Array[Int], expectedValue: Int): (Int, Int) = {
        for(noun <- 0 to 99)
        {
            for(verb <- 0 to 99)
            {
                val input_ = input.clone()
                input_(1) = noun
                input_(2) = verb
                val res = processCodes(input_)

                val output: Int = res(0)
                val nounRet: Int = res(1)
                val verbRet: Int = res(2)
                if (output == expectedValue) return (nounRet, verbRet)
            }
        }
        (-1, -1)
    }

    private[this] def processCodes(codes: Array[Int]) : Array[Int] = {
        breakable
        {
            for (optCodeIndx <- 0 until codes.size by 4)
            {
                val optCode: Int = codes(optCodeIndx)
                if (optCode == 99) break
                val outputIndex: Int = codes(optCodeIndx + 3)
                val a_ind: Int = codes(optCodeIndx + 1)
                val b_ind: Int= codes(optCodeIndx + 2)
                codes(outputIndex) = optCode match {
                    case 1 => codes(a_ind) + codes(b_ind)
                    case 2 => codes(a_ind) * codes(b_ind)
                }
            }
        }
        codes
    }
}
