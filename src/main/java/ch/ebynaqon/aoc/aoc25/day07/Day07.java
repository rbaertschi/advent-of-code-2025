package ch.ebynaqon.aoc.aoc25.day07;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day07 {

    static long solvePart1(RawProblemInput input) {
        List<String> lines = input.getLines();
        String lastLine = lines.getFirst();
        int width = lastLine.length();
        int numberOfSplits = 0;
        for (int lineNumber = 1; lineNumber < lines.size(); lineNumber++) {
            String currentLine = lines.get(lineNumber);
            StringBuilder updatedCurrentLine = new StringBuilder();
            for (int column = 0; column < width; column++) {
                char charOnCurrentLine = currentLine.charAt(column);
                if (charOnCurrentLine == '.') {
                    boolean becomesLaser =
                            isLaser(lastLine.charAt(column))
                            || (column > 0 && isSplitter(currentLine.charAt(column - 1)) && isLaser(lastLine.charAt(column - 1)))
                            || (column < width - 1 && isSplitter(currentLine.charAt(column + 1)) && isLaser(lastLine.charAt(column + 1)));
                    updatedCurrentLine.append(becomesLaser ? "|" : ".");
                } else {
                    updatedCurrentLine.append(charOnCurrentLine);
                    if (isLaser(lastLine.charAt(column))) {
                        numberOfSplits++;
                    }
                }
            }
            lines.set(lineNumber, updatedCurrentLine.toString());
            lastLine = updatedCurrentLine.toString();
        }
        return numberOfSplits;
    }

    private static boolean isLaser(char c) {
        return c == '|' || c == 'S';
    }

    private static boolean isSplitter(char c) {
        return c == '^';
    }

    static long solvePart2(RawProblemInput input) {
        List<String> lines = input.getLines();
        int width = lines.getFirst().length();
        long[] laserCounts = new long[width];
        for (var line : lines) {
            long[] newLaserCounts = new long[width];
            for (int column = 0; column < width; column++) {
                char charOnLine = line.charAt(column);
                if (charOnLine == '.') {
                    newLaserCounts[column] += laserCounts[column];
                } else if (charOnLine == 'S') {
                    newLaserCounts[column] = 1;
                } else if (charOnLine == '^') {
                    if (column > 0) {
                        newLaserCounts[column - 1] += laserCounts[column];
                    }
                    if (column < width - 1) {
                        newLaserCounts[column + 1] += laserCounts[column];
                    }
                } else {
                    throw new IllegalStateException("Unexpected character: " + charOnLine);
                }
            }
            laserCounts = newLaserCounts;
        }
        return Arrays.stream(laserCounts).sum();
    }
}
