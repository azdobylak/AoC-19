package day05

import java.net.URL
import scala.io.Source
import scala.util.control.Breaks._
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue


object Day05
{
    val inputPath = this.getClass.getResource("input.txt")

    def readInput(path: URL): Array[Int] = {
        val line: String = Source.fromURL(path).getLines().next()
        return line.split(",").map(_.toInt)
    }

    def main(args: Array[String]): Unit = {
        val input = readInput(inputPath)
        println(solve_first(1, input.clone()))
        println(solve_second(5, input.clone()))
    }

    def solve_first(input: Int, program: Array[Int]): Int = {
        processCodes(input, program)
    }

    def solve_second(input: Int, program: Array[Int]): Int = {
      processCodes(input, program)
    }

    def processCodes(input: Int, codes: Array[Int]): Int = {
      processCodes(Queue(input), codes)
    }

    def processCodes(input: Queue[Int], codes: Array[Int]) : Int = {
        var output: Int = 0;
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
                case 5 | 6 => 3
                case 7 | 8 => 4
              }

              val positionModes: List[Boolean] = (instruction / 100).toString.reverse.padTo(3, '0').toList.slice(0, indxShift-1).map(
                v => v match {
                  case '0' => true
                  case '1' => false
                  case digit => throw new Exception("Unable to parse code: " + digit)
                }
              )

              val values = positionModes.slice(0, indxShift-1).zipWithIndex.map{ case (v, i) => {val indx = codes(optCodeIndx+1+i); if(v) codes(indx) else indx}}

              var jump = false

              if (optCode == 5 || optCode == 6)
              {
                jump = optCode match {
                  case 5 => values(0) != 0
                  case 6 => values(0) == 0
                }
                if (jump)
                  optCodeIndx = values(1)

              }
              else if (optCode == 7)
                codes(codes(optCodeIndx+3)) = if(values(0) < values(1)) 1 else 0
              else if (optCode == 8)
              {
                codes(codes(optCodeIndx+3)) = if(values(0) == values(1)) 1 else 0
              }
              else if (optCode == 4)
                output = values(0)
              else if (optCode == 3)
              {
                codes(codes(optCodeIndx+1)) = input.dequeue
              }
              else
                codes(codes(optCodeIndx+3)) = optCode match {
                    case 1 => values(0) + values(1)
                    case 2 => values(0) * values(1)
                }

              if (!jump)
                optCodeIndx = optCodeIndx + indxShift
          }
        }
        output
    }
}
