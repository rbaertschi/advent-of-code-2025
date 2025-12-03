package ch.ebynaqon.aoc.aoc25.day03;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day03 {

    static List<BatteryBank> parseProblem(RawProblemInput input) {
        return input.getLines().stream().map(Day03::parseLine).toList();
    }

    private static BatteryBank parseLine(String input) {
        return new BatteryBank(Arrays.stream(input.split("")).map(Integer::parseInt).toList());
    }

    static long solvePart1(RawProblemInput input) {
        List<BatteryBank> problem = parseProblem(input);
        return 0;
    }

    static long solvePart2(RawProblemInput input) {
        List<BatteryBank> problem = parseProblem(input);
        return 0;
    }
}


record BatteryBank(List<Integer> joltages) {
}

