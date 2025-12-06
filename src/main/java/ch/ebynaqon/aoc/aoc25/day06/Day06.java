package ch.ebynaqon.aoc.aoc25.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day06 {

    static List<Operation> parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        List<List<String>> parts = lines.stream().map(line -> List.of(line.trim().split("\\s+"))).toList();
        int numberOfProblems = parts.getLast().size();
        int numberOfNumbersPerProblem = lines.size() - 1;
        ArrayList<Operation> problems = new ArrayList<>(numberOfProblems);
        for (int problemNumber = 0; problemNumber < numberOfProblems; problemNumber++) {
            ArrayList<Integer> numbers = new ArrayList<>(numberOfNumbersPerProblem);
            for (int currentNumberIndex = 0; currentNumberIndex < numberOfNumbersPerProblem; currentNumberIndex++) {
                numbers.add(Integer.parseInt(parts.get(currentNumberIndex).get(problemNumber)));
            }
            problems.add(Operation.resolve(parts.getLast().get(problemNumber), numbers));
        }
        return problems;
    }

    static long solvePart1(RawProblemInput input) {
        List<Operation> operations = parseProblem(input);
        return operations.stream().mapToLong(Operation::compute).sum();
    }

    static long solvePart2(RawProblemInput input) {
        List<Operation> operations = parseProblem(input);
        return 0;
    }
}

sealed interface Operation {
    long compute();

    static Operation resolve(String op, List<Integer> numbers) {
        return switch (op) {
            case "+" -> new Addition(numbers);
            case "*" -> new Multiplication(numbers);
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
