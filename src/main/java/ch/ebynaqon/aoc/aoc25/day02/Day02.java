package ch.ebynaqon.aoc.aoc25.day02;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.HashSet;
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
        return parseProblem(input).stream().flatMap(IdRange::invalidIds).reduce(0L, Long::sum);
    }

    static long solvePart2(RawProblemInput input) {
        return parseProblem(input).stream().flatMap(IdRange::invalidIdsWithMoreRepetitions).reduce(0L, Long::sum);
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
        int patternLength = asString.length() / 2;
        return firstPart(start, patternLength);
    }

    private long firstPart(long number, int patternLength) {
        if (patternLength == 0) {
            return 1;
        }
        return Long.parseLong(String.valueOf(number).substring(0, patternLength));
    }

    public Stream<Long> invalidIdsWithMoreRepetitions() {
        HashSet<Long> invalidIds = new HashSet<>();
        int startLength = (int) Math.ceil(Math.log10(start + 1));
        int endLength = (int) Math.ceil(Math.log10(end + 1));
        for (int patternLength = 1; patternLength <= endLength / 2; patternLength++) {
            long minPattern = Math.powExact(10, patternLength - 1);
            long maxPattern = Math.powExact(10, patternLength) - 1;
            for (long pattern = minPattern; pattern <= maxPattern; pattern++) {
                int minRepetitions = Math.max(2, startLength / patternLength);
                int maxRepetitions = Math.max(2, endLength / patternLength);
                for (int repetitions = minRepetitions; repetitions <= maxRepetitions; repetitions++) {
                    long candidate = expand(pattern, repetitions);
                    if (candidate >= start && candidate <= end) {
                        invalidIds.add(candidate);
                    }
                }
            }
        }
        return invalidIds.stream();
    }

    private long expand(long pattern, int repetitions) {
        long result = pattern;
        // get log 10 of pattern as long
        int factor = Math.powExact(10, (int) Math.ceil(Math.log10(pattern + 1)));
        for (int i = 1; i < repetitions; i++) {
            result = result * factor + pattern;
        }
        return result;
    }

}

