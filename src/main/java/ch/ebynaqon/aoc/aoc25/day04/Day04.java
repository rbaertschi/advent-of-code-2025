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
        return problem.countAccessibleRolls();
    }

    static long solvePart2(RawProblemInput input) {
        PaperRollMap problem = parseProblem(input);
        return 0;
    }
}

record PaperRollMap(int[][] grid, int rows, int cols) implements Day04 {
    PaperRollMap(int[][] grid) {
        this(grid, grid.length, grid[0].length);
    }

    public int get(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return 0;
        }
        return grid[row][col];
    }

    public long countAccessibleRolls() {
        long accessible = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (get(row, col) > 0 && countRollsInNeighbourhood(row, col) < 4) {
                    accessible++;
                }
            }
        }
        return accessible;
    }

    private int countRollsInNeighbourhood(int row, int col) {
        return
                get(row - 1, col - 1) +
                get(row - 1, col) +
                get(row - 1, col + 1) +
                get(row, col - 1) +
                get(row, col + 1) +
                get(row + 1, col - 1) +
                get(row + 1, col) +
                get(row + 1, col + 1);
    }
}
