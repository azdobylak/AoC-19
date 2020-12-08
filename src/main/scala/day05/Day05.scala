package day05

import java.net.URL
import scala.io.Source
import scala.util.control.Breaks._
import scala.collection.mutable.ListBuffer


object Day05
{
    val inputPath = this.getClass.getResource("input.txt")

    def readInput(path: URL): Array[Int] = {
        val line: String = Source.fromURL(path).getLines().next()
        return line.split(",").map(_.toInt)
    }

    def main(args: Array[String]): Unit = {
        val input = readInput(inputPath)
        println(solve_first(1, input).mkString(", "))
        //println(solve_second(readInput(inputPath), 19690720))
    }

    def solve_first(input: Int, program: Array[Int]): Seq[Int] = {
        processCodes(input, program)
    }

    private[this] def processCodes(input: Int, codes: Array[Int]) : Seq[Int] = {
        var output = new ListBuffer[Int]()
        breakable
        {
          var optCodeIndx = 0

          while(optCodeIndx < codes.size)
          {
              val instruction: Int = codes(optCodeIndx)
              val optCode: Int = instruction % 100
              if (optCode == 99) break

              val indxShift = optCode match {
                case 1 | 2 => 4
                case 3 | 4 => 2
              }

              val positionModes: List[Boolean] = (instruction / 100).toString.reverse.padTo(3, '0').toList.slice(0, indxShift-1).map(
                v => v match {
                  case '0' => true
                  case '1' => false
                  case digit => throw new Exception("Unable to parse code: " + digit)
                }
              )

              val a_ind: Int = codes(optCodeIndx + 1)
              val b_ind: Int= codes(optCodeIndx + 2)
              val outputIndex: Int = codes(optCodeIndx + 3)

              val values = positionModes.slice(0, indxShift-1).zipWithIndex.map{ case (v, i) => {val indx = codes(optCodeIndx+1+i); if(v) codes(indx) else indx}}

              if (optCode == 4)
                output += values(0)
              else if (optCode == 3)
                codes(a_ind) = input
              else
                codes(outputIndex) = optCode match {
                    case 1 => values(0) + values(1)
                    case 2 => values(0) * values(1)
                }

              optCodeIndx = optCodeIndx + indxShift
          }
        }
        output.toList
    }
}
