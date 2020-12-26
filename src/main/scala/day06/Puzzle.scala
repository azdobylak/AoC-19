package day06

import java.net.URL
import scala.io.Source
import scala.annotation.tailrec
import scala.math.{min, max}



object Puzzle
{
    val inputPath = this.getClass.getResource("input.txt")

    def readInput(path: URL): List[String] = {
        println(path)
        val lines= Source.fromURL(path).getLines()
        return lines.toList
    }

    def main(args: Array[String]): Unit = {
        val input = readInput(inputPath)
        println(solve_first(input))
        println(solve_second(input))
    }

    def solve_first(input: List[String]): Int = {
        val orbitPairs = preprocessOrbits(input)

        def countOrbits(orbits: List[(String, String)], current_head: String, depth: Int): Int = {
            val (orbit_targets, orbits_other) = orbits.partition(_._1 == current_head)

            if (orbit_targets.isEmpty)
                return depth
            else
                {
                    val depths = orbit_targets.map(p => countOrbits(orbits_other, p._2, depth+1))
                    depth + depths.sum
                }
        }

        countOrbits(orbitPairs, "COM", 0)
    }

    def solve_second(input: List[String]): Int = {
        val orbitPairs = preprocessOrbits(input)

        def countPathLength(orbits: List[(String, String)], current_head: String): (Int, Boolean, Boolean) = {
            val (orbit_targets, orbits_other) = orbits.partition(_._1 == current_head)

            val tailContainsYOU = orbits_other.exists(_._2 == "YOU")
            val tailContainsSAN = orbits_other.exists(_._2 == "SAN")

            val childOrbits = orbit_targets.map(s => countPathLength(orbits_other, s._2))
            lazy val tailPathLength: Int = childOrbits.map(_._1).sum

            val headVal = if(current_head == "YOU" || current_head == "SAN") 1 else 0

            val visited_you = childOrbits.exists(_._2) || current_head == "YOU"
            val visited_san = childOrbits.exists(_._3) || current_head == "SAN"

            val res = tailPathLength + headVal + min(tailPathLength, 1)

            if(visited_you && visited_san)
                (tailPathLength, visited_you, visited_san)
            else
                (res, visited_you, visited_san)
        }

        val head = countPathLength(orbitPairs, "COM")

        // subtract "YOU" and "SAN"
        head._1 - 2
    }

    private def preprocessOrbits(orbits: List[String]): List[(String, String)] = {
        orbits.map(s => {
            val split = s.split("\\)")
            (split(0), split(1))
        })
    }
}
