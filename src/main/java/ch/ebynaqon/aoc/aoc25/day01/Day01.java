package ch.ebynaqon.aoc.aoc25.day01;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;

interface Day01 {

    static List<Integer> parseProblem(RawProblemInput input) {
        return input.getLines().stream().map(Day01::parseLine).toList();
    }

    private static Integer parseLine(String input) {
        return (input.startsWith("L") ? -1 : 1) * Integer.parseInt(input.substring(1));
    }

    static long solvePart1(RawProblemInput input) {
        List<Integer> rotations = parseProblem(input);
        int position = 50;
        int numberOfZeros = 0;
        for (int rotation : rotations) {
            position = (100 + (position + rotation) % 100) % 100;
            if (position == 0) {
                numberOfZeros++;
            }
        }
        return numberOfZeros;
    }

    static long solvePart2(RawProblemInput input) {
        List<Integer> problem = parseProblem(input);
        return 0;
    }
}
