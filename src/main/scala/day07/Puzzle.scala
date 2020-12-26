package day07

import day05.Day05
import java.net.URL
import scala.io.Source
import scala.collection.mutable.Queue


object Puzzle
{
    val inputPath = this.getClass.getResource("input.txt")

    def readInput(path: URL): Array[Int] = {
      val line: String = Source.fromURL(path).getLines().next()
      return line.split(",").map(_.toInt)
    }

    def main(args: Array[String]): Unit = {
      val input = readInput(inputPath)
      println(solve_first(input.clone()))
    }

    def solve_first(program: Array[Int]): Int = {
      val phases_base = Array(0, 1, 2, 3, 4)
      phases_base.permutations.map(p => calculate_thruster_signal(program.clone(), p)).toList.max
    }

    def calculate_thruster_signal(program: Array[Int], phases: Array[Int]): Int = {
      var program_input: Int = 0

      for((phase, i) <- phases.zipWithIndex){
        val input_queue: Queue[Int] = Queue(phase, program_input)
        program_input = Day05.processCodes(input_queue, program.clone())
      }
      program_input
    }

}
