package ch.ebynaqon.aoc.aoc25.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day06 {

    static List<Operation> parseProblem(RawProblemInput input, boolean vertical) {
        List<String> lines = input.getLines();
        List<List<String>> subProblems = splitIntoProblems(lines);
        return subProblems.stream().map(subProblem -> {
            char operation = subProblem.getLast().trim().charAt(0);
            List<String> numberLines = subProblem.subList(0, subProblem.size() - 1);
            return Operation.resolve(operation, parseNumbers(numberLines, vertical));
        }).toList();
    }

    static List<Integer> parseNumbers(List<String> lines, boolean vertical) {
        return (vertical ? rotateLines(lines) : lines).stream().map(String::trim).map(Integer::parseInt).toList();
    }

    static List<String> rotateLines(List<String> lines) {
        int numberOfColumns = lines.stream().mapToInt(String::length).reduce(0, Math::max);
        ArrayList<String> rotated = new ArrayList<>(numberOfColumns);
        for (int column = 0; column < numberOfColumns; column++) {
            StringBuilder newLine = new StringBuilder();
            for (String line : lines) {
                if (column < line.length()) {
                    newLine.append(line.charAt(column));
                }
            }
            rotated.add(newLine.toString());
        }
        return rotated;
    }

    static List<List<String>> splitIntoProblems(List<String> inputLines) {
        List<List<String>> problems = new ArrayList<>();
        int maxLength = inputLines.stream().mapToInt(String::length).reduce(0, Math::max);
        int nextStartColumn = 0;
        for (int column = 0; column < maxLength - 1; column++) {
            if (isBlankColumn(column, inputLines)) {
                int startColumn = nextStartColumn;
                int endColumn = column;
                problems.add(inputLines.stream().map(line -> line.substring(startColumn, Math.min(endColumn, line.length()))).toList());
                nextStartColumn = column + 1;
            }
        }
        // handle last column
        if (nextStartColumn < maxLength) {
            int startColumn = nextStartColumn;
            problems.add(inputLines.stream().map(line -> line.substring(startColumn)).toList());
        }
        return problems;
    }

    static boolean isBlankColumn(int column, List<String> lines) {
        return lines.stream().allMatch(line -> line.length() <= column || line.charAt(column) == ' ');
    }

    static long solvePart1(RawProblemInput input) {
        return parseProblem(input, false).stream().mapToLong(Operation::compute).sum();
    }

    static long solvePart2(RawProblemInput input) {
        return parseProblem(input, true).stream().mapToLong(Operation::compute).sum();
    }
}

sealed interface Operation {
    long compute();

    static Operation resolve(char op, List<Integer> numbers) {
        return switch (op) {
            case '+' -> new Addition(numbers);
            case '*' -> new Multiplication(numbers);
            default -> throw new IllegalArgumentException("Unknown operation: " + op);
        };
    }
}

record Addition(List<Integer> numbers) implements Operation {
    @Override
    public long compute() {
        return numbers.stream().mapToLong(i -> i).sum();
    }
}

record Multiplication(List<Integer> numbers) implements Operation {
    @Override
    public long compute() {
        return numbers.stream().mapToLong(i -> i).reduce(1, (a, b) -> a * b);
    }
}

