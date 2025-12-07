package ch.ebynaqon.aoc.aoc25.day07;

import ch.ebynaqon.aoc.helper.RawProblemInput;

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
        return 0;
    }
}
