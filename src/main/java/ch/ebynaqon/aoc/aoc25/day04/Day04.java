package ch.ebynaqon.aoc.aoc25.day04;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

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
        return parseProblem(input).countAccessibleRolls();
    }

    static long solvePart2(RawProblemInput input) {
        return parseProblem(input).countIncrementallyAccessibleRolls();
    }
}

record PaperRollMap(int[][] grid, int rows, int cols) implements Day04 {
    PaperRollMap(int[][] grid) {
        this(grid, grid.length, grid[0].length);
    }

    public long countAccessibleRolls() {
        return countAccessible(false);
    }

    public long countIncrementallyAccessibleRolls() {
        return countAccessible(true);
    }

    private int countAccessible(boolean allowRemoval) {
        Queue<Position> candidates = getInitialRollPositions();
        Set<Position> removed = new HashSet<>();
        while (!candidates.isEmpty()) {
            Position position = candidates.poll();
            if (!removed.contains(position)) {
                List<Position> neighbouringRolls = getNeighbouringRolls(position);
                if (neighbouringRolls.size() < 4) {
                    removed.add(position);
                    if (allowRemoval) {
                        remove(position);
                        candidates.addAll(neighbouringRolls);
                    }
                }
            }
        }
        return removed.size();
    }

    private List<Position> getNeighbouringRolls(Position position) {
        return Stream.of(
                getRollOrNull(position.row() - 1, position.col() - 1),
                getRollOrNull(position.row() - 1, position.col()),
                getRollOrNull(position.row() - 1, position.col() + 1),
                getRollOrNull(position.row(), position.col() - 1),
                getRollOrNull(position.row(), position.col() + 1),
                getRollOrNull(position.row() + 1, position.col() - 1),
                getRollOrNull(position.row() + 1, position.col()),
                getRollOrNull(position.row() + 1, position.col() + 1)
        ).filter(Objects::nonNull).toList();
    }

    private Position getRollOrNull(int row, int col) {
        return get(row, col) > 0 ? new Position(row, col) : null;
    }

    public int get(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return 0;
        }
        return grid[row][col];
    }

    private void remove(Position position) {
        grid[position.row()][position.col()] = 0;
    }

    private LinkedList<Position> getInitialRollPositions() {
        LinkedList<Position> positions = new LinkedList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (get(row, col) > 0) {
                    positions.add(new Position(row, col));
                }
            }
        }
        return positions;
    }

}

record Position(int row, int col) {
}
