package ch.ebynaqon.aoc.aoc25.day02;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;
import java.util.stream.Stream;

interface Day02 {

    static List<IdRange> parseProblem(RawProblemInput input) {
        return Stream.of(input.getWholeInput().split(",")).map(Day02::parseRange).toList();
    }

    private static IdRange parseRange(String input) {
        String[] startAndEnd = input.split("-");
        return new IdRange(Long.parseLong(startAndEnd[0]), Long.parseLong(startAndEnd[1]));
    }

    static long solvePart1(RawProblemInput input) {
        List<IdRange> problem = parseProblem(input);
        return 0;
    }

    static long solvePart2(RawProblemInput input) {
        List<IdRange> problem = parseProblem(input);
        return 0;
    }
}

record IdRange(long start, long end) {
}

