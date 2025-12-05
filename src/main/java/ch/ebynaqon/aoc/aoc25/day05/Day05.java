package ch.ebynaqon.aoc.aoc25.day05;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;
import java.util.stream.Stream;

interface Day05 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] freshRangesAndIds = input.getWholeInput().split("\n\n");
        List<FreshRange> freshRanges = Stream.of(freshRangesAndIds[0].split("\n")).map(Day05::parseFreshRange).toList();
        List<Integer> ids = Stream.of(freshRangesAndIds[1].split("\n")).map(Integer::parseInt).toList();
        return new ProblemInput(freshRanges, ids);
    }

    private static FreshRange parseFreshRange(String input) {
        String[] fromAndTo = input.split("-");
        return new FreshRange(Integer.parseInt(fromAndTo[0]), Integer.parseInt(fromAndTo[1]));
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

record ProblemInput(List<FreshRange> freshRanges, List<Integer> ids) {
}

record FreshRange(int from, int to) {
}

