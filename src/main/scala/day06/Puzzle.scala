package day06

import java.net.URL
import scala.io.Source
import scala.annotation.tailrec



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
    }

    def solve_first(input: List[String]): Int = {

        val orbitPairs: List[(String, String)] = input.map(s => {
            val split = s.split("\\)")
            (split(0), split(1))
        })

        def countOrbits(orbits: List[(String, String)], current_head: String, depth: Int): Int = {
            val (orbit_targets, orbits_rest) = orbits.partition(_._1 == current_head)

            if (orbit_targets.isEmpty)
                return depth
            else
                {
                    val depths = orbit_targets.map(p => countOrbits(orbits_rest, p._2, depth+1))
                    depth + depths.sum
                }
        }

        countOrbits(orbitPairs, "COM", 0)
    }

}
