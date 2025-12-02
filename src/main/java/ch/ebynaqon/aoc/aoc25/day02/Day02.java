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
        int startLength = String.valueOf(start).length();
        int endLength = String.valueOf(end).length();
        for (int patternLength = 1; patternLength <= endLength / 2; patternLength++) {
            long pattern = Math.min(firstPart(start, patternLength), firstPart(end, patternLength));
            int minRepetitions = Math.max(2, startLength /  patternLength);
            int maxRepetitions = endLength / patternLength;
            while (expand(pattern, minRepetitions) <= end) {
                for (int repetitions = minRepetitions; repetitions <= maxRepetitions; repetitions++) {
                    long candidate = expand(pattern, repetitions);
                    if (candidate >= start &&  candidate <= end) {
                        invalidIds.add(candidate);
                        System.out.println(candidate);
                    }
                }
                pattern++;
            }
        }
        return invalidIds.stream();
    }

    private long expand(long pattern, int repetitions) {
        return Long.parseLong(String.valueOf(pattern).repeat(repetitions));
    }

}

