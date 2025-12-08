package ch.ebynaqon.aoc.aoc25.day08;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day08 {

    static List<JunctionBox> parseProblem(RawProblemInput input) {
        return input.getLines().stream().map(Day08::parseLine).toList();
    }

    private static JunctionBox parseLine(String input) {
        String[] xYZ = input.split(",");
        if (xYZ.length != 3) {
            throw new IllegalArgumentException("Position of junction box does not have 3 coordinates: %s".formatted(input));
        }
        return new JunctionBox(
                Integer.parseInt(xYZ[0]),
                Integer.parseInt(xYZ[1]),
                Integer.parseInt(xYZ[2])
        );
    }

    static long solvePart1(RawProblemInput input) {
        List<JunctionBox> problem = parseProblem(input);
        return 0;
    }

    static long solvePart2(RawProblemInput input) {
        List<JunctionBox> problem = parseProblem(input);
        return 0;
    }
}

record JunctionBox(int x, int y, int z) {
}

