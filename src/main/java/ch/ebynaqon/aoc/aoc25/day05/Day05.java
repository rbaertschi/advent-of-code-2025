package ch.ebynaqon.aoc.aoc25.day05;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;
import java.util.stream.Stream;

interface Day05 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] freshRangesAndIds = input.getWholeInput().split("\n\n");
        List<FreshRange> freshRanges = Stream.of(freshRangesAndIds[0].split("\n")).map(Day05::parseFreshRange).toList();
        List<Long> ids = Stream.of(freshRangesAndIds[1].split("\n")).map(Long::parseLong).toList();
        return new ProblemInput(freshRanges, ids);
    }

    private static FreshRange parseFreshRange(String input) {
        String[] fromAndTo = input.split("-");
        return new FreshRange(Long.parseLong(fromAndTo[0]), Long.parseLong(fromAndTo[1]));
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.ids().stream().filter(id -> problem.freshRanges().stream().anyMatch(freshRange -> freshRange.contains(id))).count();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

record ProblemInput(List<FreshRange> freshRanges, List<Long> ids) {
}

record FreshRange(long from, long to) {
    public boolean contains(long id) {
        return id >= from && id <= to;
    }
}

