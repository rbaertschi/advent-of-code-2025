package ch.ebynaqon.aoc.aoc25.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day06 {

    static List<Operation> parseProblem(RawProblemInput input, boolean vertical) {
        List<String> lines = input.getLines();
        String operatorLine = lines.getLast();
        //noinspection OptionalGetWithoutIsPresent
        int maxColumn = lines.stream().mapToInt(String::length).max().getAsInt() + 1;
        List<String> numberLines = lines.subList(0, lines.size() - 1);
        List<Integer> problemStartColumn = determineProblemStartColumns(operatorLine);
        int numberOfProblems = problemStartColumn.size();
        ArrayList<Operation> problems = new ArrayList<>(numberOfProblems);
        for (int problemNumber = 0; problemNumber < numberOfProblems; problemNumber++) {
            Integer startColumn = problemStartColumn.get(problemNumber);
            Integer endColumn = (problemNumber == numberOfProblems - 1 ? maxColumn : problemStartColumn.get(problemNumber + 1)) - 1;
            ArrayList<Integer> numbers = vertical
                    ? determineProblemInputNumbersVertical(numberLines, startColumn, endColumn)
                    : determineProblemInputNumbersHorizontal(numberLines, startColumn, endColumn);
            problems.add(Operation.resolve(operatorLine.charAt(startColumn), numbers));
        }
        return problems;
    }

    private static List<Integer> determineProblemStartColumns(String operatorLine) {
        List<Integer> problemStartColumn = new ArrayList<>();
        for (int i = 0; i < operatorLine.length(); i++) {
            char charInColumn = operatorLine.charAt(i);
            if (charInColumn == '*' || charInColumn == '+') {
                problemStartColumn.add(i);
            }
        }
        return problemStartColumn;
    }

    private static ArrayList<Integer> determineProblemInputNumbersHorizontal(List<String> numberLines, int startColumn, Integer endColumn) {
        int numberCount = numberLines.size();
        ArrayList<Integer> numbers = new ArrayList<>(numberCount);
        for (String line : numberLines) {
            numbers.add(Integer.parseInt(line.substring(startColumn, Math.min(endColumn, line.length())).trim()));
        }
        return numbers;
    }

    private static ArrayList<Integer> determineProblemInputNumbersVertical(List<String> numberLines, int startColumn, Integer endColumn) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int column = startColumn; column <= endColumn; column++) {
            StringBuilder number = new StringBuilder();
            for (var line : numberLines) {
                if (column < line.length()) {
                    number.append(line.charAt(column));
                }
            }
            String numberToParse = number.toString().trim();
            if (!numberToParse.isBlank()) {
                numbers.add(Integer.parseInt(numberToParse));
            }
        }
        return numbers;
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
