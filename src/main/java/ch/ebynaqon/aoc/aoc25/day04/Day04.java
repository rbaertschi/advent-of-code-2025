package ch.ebynaqon.aoc.aoc25.day04;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;

interface Day04 {

    static PaperRollMap parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        int[][] values = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                values[i][j] = line.charAt(j) == '@' ? 1 : 0;
            }
        }
        return new PaperRollMap(values);
    }

    static long solvePart1(RawProblemInput input) {
        PaperRollMap problem = parseProblem(input);
        return 0;
    }

    static long solvePart2(RawProblemInput input) {
        PaperRollMap problem = parseProblem(input);
        return 0;
    }
}

record PaperRollMap(int[][] grid) {
}
