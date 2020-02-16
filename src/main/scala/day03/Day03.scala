package day03

import java.net.URL
import scala.io.Source
import scala.util.control.Breaks._
import scala.collection.mutable.ListBuffer
import scalaz._, Scalaz._


object Day03
{
    val inputPath = this.getClass.getResource("input.txt")

    def readInput(path: URL): List[String] = {
        Source.fromURL(path).getLines.toList
    }

    def main(args: Array[String]): Unit = {
        println(solve_first(readInput(inputPath)))
        println(solve_second(readInput(inputPath)))
    }

    def solve_first(input: List[String]): Int = {
        val firstField = markVisitedFields(input(0))
        val secondField = markVisitedFields(input(1))
        val intersections = firstField.intersect(secondField).filter(_ != (0, 0))
        shortestDistance(intersections)
    }

    def solve_second(input: List[String]): Int = {
        val firstField = markVisitedFields(input(0))
        val secondField = markVisitedFields(input(1))
        val intersections = firstField.intersect(secondField).filter(_ != (0, 0))
        val pathLengths: List[Int] = (
            intersections.map{ item => firstField.indexOf(item)},
            intersections.map{ item => secondField.indexOf(item)})
                .zipped.map(_ + _)

        val shortestPath: (Int, Int) = pathLengths.zipWithIndex.min
        shortestPath._1
    }

    private def markVisitedFields(input: String): List[(Int, Int)] = {
        val instructions: List[String] = input.split(",").toList
        val visitedOrigins: List[(Int, Int)] = instructions map {
            case s if s.startsWith("L") => (-s.tail.toInt, 0)
            case s if s.startsWith("R") => (s.tail.toInt, 0)
            case s if s.startsWith("D") => (0, -s.tail.toInt)
            case s if s.startsWith("U") => (0, s.tail.toInt)
        }

        var currentPos: (Int, Int) = (0, 0)

        var visited: ListBuffer[(Int, Int)] = ListBuffer[(Int, Int)](currentPos)
        for(origin <- visitedOrigins){
            val sign_1: Int = if (origin._1 !=0) math.signum(origin._1) else 1
            val sign_2: Int = if (origin._2 !=0) math.signum(origin._2) else 1
            val newVisited = for(i <- 0 to origin._1 by sign_1; j <- 0 to origin._2 by sign_2)
                yield (currentPos._1+ i, currentPos._2 + j)

            currentPos = currentPos |+| origin
            // remove redundant item added by currentPos + (0, 0)
            visited = visited ++ newVisited.tail
        }
        //visited.drop(0).toList
        visited.toList
    }

    private def shortestDistance(intersections: List[(Int, Int)]): Int = {
        val distances = intersections.map{p => math.abs(p._1) + math.abs(p._2)}
        distances.min
    }
}
