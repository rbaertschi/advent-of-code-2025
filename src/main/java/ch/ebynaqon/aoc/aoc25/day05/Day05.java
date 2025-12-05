package ch.ebynaqon.aoc.aoc25.day05;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

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
        List<FreshRange> freshRanges = parseProblem(input).freshRanges().stream()
                .sorted(Comparator.comparing(FreshRange::from)).toList();
        List<FreshRange> reducedFreshRanges = new ArrayList<>();
        for (FreshRange freshRange : freshRanges) {
            reducedFreshRanges = merge(reducedFreshRanges, freshRange);
        }
        return reducedFreshRanges.stream().mapToLong(FreshRange::length).sum();
    }

    static List<FreshRange> merge(List<FreshRange> reducedFreshRanges, FreshRange freshRange) {
        List<FreshRange> intersecting = reducedFreshRanges.stream().filter(freshRange::intersects).toList();
        List<FreshRange> reduced = new ArrayList<>(reducedFreshRanges.stream().filter(not(freshRange::intersects)).toList());
        if (intersecting.isEmpty()) {
            reduced.add(freshRange);
        } else {
            long from = Math.min(intersecting.stream().mapToLong(FreshRange::from).min().getAsLong(), freshRange.from());
            long to = Math.max(intersecting.stream().mapToLong(FreshRange::to).max().getAsLong(), freshRange.to());
            reduced.add(new FreshRange(from, to));
        }
        return reduced;
    }
}

record ProblemInput(List<FreshRange> freshRanges, List<Long> ids) {
}

record FreshRange(long from, long to) {
    public boolean contains(long id) {
        return id >= from && id <= to;
    }

    public long length() {
        return to - from + 1;
    }

    public boolean intersects(FreshRange other) {
        return contains(other.from()) || contains(other.to())
               || other.contains(from) || other.contains(to);
    }
}

