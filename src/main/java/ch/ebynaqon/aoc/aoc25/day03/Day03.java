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
        return parseProblem(input).stream().mapToLong(batteryBank -> batteryBank.getMaxJoltageWithNBatteries(2)).sum();
    }

    static long solvePart2(RawProblemInput input) {
        return parseProblem(input).stream().mapToLong(batteryBank -> batteryBank.getMaxJoltageWithNBatteries(12)).sum();
    }
}


record BatteryBank(Integer... joltages) {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BatteryBank(Integer[] otherJoltages)) {
            return List.of(this.joltages).equals(List.of(otherJoltages));
        }
        return false;
    }

    public long getMaxJoltageWithNBatteries(int numberOfBatteries) {
        long[][] cache = new long[joltages.length][numberOfBatteries];
        for (int i = 0; i < joltages.length; i++) {
            for (int j = 0; j < numberOfBatteries; j++) {
                cache[i][j] = -1;
            }
        }
        return getMaxJoltage(0, numberOfBatteries, cache);
    }

    private long getMaxJoltage(int startIndex, int numberOfBatteriesRemaining, long[][] cache) {
        if (numberOfBatteriesRemaining == 0) return 0;
        if (numberOfBatteriesRemaining > joltages.length - startIndex) return 0;
        if (cache[startIndex][numberOfBatteriesRemaining - 1] > -1) {
            return cache[startIndex][numberOfBatteriesRemaining - 1];
        }
        long power = Math.powExact(10L, numberOfBatteriesRemaining - 1);
        long maxJoltageWithFirstBattery = joltages[startIndex] * power + getMaxJoltage(startIndex + 1, numberOfBatteriesRemaining - 1, cache);
        long maxJoltageWithoutFirstBattery = getMaxJoltage(startIndex + 1, numberOfBatteriesRemaining, cache);
        long max = Math.max(maxJoltageWithFirstBattery, maxJoltageWithoutFirstBattery);
        cache[startIndex][numberOfBatteriesRemaining - 1] = max;
        return max;
    }
}
