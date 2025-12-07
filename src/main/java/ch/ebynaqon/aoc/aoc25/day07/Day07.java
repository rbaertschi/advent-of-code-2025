package ch.ebynaqon.aoc.aoc25.day07;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day07 {

    static long solvePart1(RawProblemInput input) {
        return solveTachyonSplitter(input).numberOfSplits();
    }

    static long solvePart2(RawProblemInput input) {
        return solveTachyonSplitter(input).numberOfTachionPaths();
    }

    private static SplittingResult solveTachyonSplitter(RawProblemInput input) {
        List<String> lines = input.getLines();
        int width = lines.getFirst().length();
        long[] laserCounts = new long[width];
        int numberOfSplits = 0;
        for (var line : lines) {
            long[] newLaserCounts = new long[width];
            for (int column = 0; column < width; column++) {
                char charOnLine = line.charAt(column);
                long laserCountOnCurrentColumnOfLastLine = laserCounts[column];
                if (charOnLine == 'S') {
                    newLaserCounts[column] = 1;
                } else if (charOnLine == '.') {
                    newLaserCounts[column] += laserCountOnCurrentColumnOfLastLine;
                } else if (charOnLine == '^') {
                    if (laserCountOnCurrentColumnOfLastLine > 0) {
                        numberOfSplits++;
                        if (column > 0) {
                            newLaserCounts[column - 1] += laserCountOnCurrentColumnOfLastLine;
                        }
                        if (column < width - 1) {
                            newLaserCounts[column + 1] += laserCountOnCurrentColumnOfLastLine;
                        }
                    }
                } else {
                    throw new IllegalStateException("Unexpected character: " + charOnLine);
                }
            }
            laserCounts = newLaserCounts;
        }
        return new SplittingResult(numberOfSplits, Arrays.stream(laserCounts).sum());
    }
}

record SplittingResult(int numberOfSplits, long numberOfTachionPaths) {
}
