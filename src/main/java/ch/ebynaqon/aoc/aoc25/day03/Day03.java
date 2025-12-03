package ch.ebynaqon.aoc.aoc25.day03;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day03 {

    static List<BatteryBank> parseProblem(RawProblemInput input) {
        return input.getLines().stream().map(Day03::parseLine).toList();
    }

    private static BatteryBank parseLine(String input) {
        return new BatteryBank(Arrays.stream(input.split("")).map(Integer::parseInt).toArray(Integer[]::new));
    }

    static long solvePart1(RawProblemInput input) {
        return parseProblem(input).stream().mapToInt(BatteryBank::getMaxJoltage).sum();
    }

    static long solvePart2(RawProblemInput input) {
        List<BatteryBank> problem = parseProblem(input);
        return 0;
    }
}


record BatteryBank(Integer ...joltages) {
    public int getMaxJoltage() {
        int maxJoltage = 0;
        for (int i = 0; i < joltages.length - 1; i++) {
            for (int j = i + 1; j < joltages.length; j++) {
                int currentJoltage = joltages[i] * 10 + joltages[j];
                if (currentJoltage > maxJoltage) {
                    maxJoltage = currentJoltage;
                }
            }
        }
        return maxJoltage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BatteryBank(Integer[] otherJoltages)) {
            return List.of(this.joltages).equals(List.of(otherJoltages));
        }
        return false;
    }
}

