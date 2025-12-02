package ch.ebynaqon.aoc.aoc25.day02;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
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
        List<Long> longStream = problem.stream().flatMap(IdRange::invalidIds).toList();
        longStream.forEach(System.out::println);
        return longStream.stream().reduce(0L, Long::sum);
    }

    static long solvePart2(RawProblemInput input) {
        List<IdRange> problem = parseProblem(input);
        return 0;
    }
}

record IdRange(long start, long end) {
    public Stream<Long> invalidIds() {
        ArrayList<Long> invalidIds = new ArrayList<>();
        for (long i = firstHalf(start); doubleIt(i) <= end; i++) {
            long idToCheck = doubleIt(i);
            if (idToCheck >= start) {
                invalidIds.add(idToCheck);
            }
        }
        return invalidIds.stream();
    }

    private long doubleIt(long i) {
        return Long.parseLong(String.format("%d%d", i, i));
    }

    private long firstHalf(long start) {
        String asString = String.valueOf(start);
        return Long.parseLong(asString.substring(0, asString.length() / 2));
    }
}

