package ch.ebynaqon.aoc.aoc25.day03;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/3
class Day03Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                987654321111111
                811111111111119
                234234234234278
                818181911112111
                """);

        // when
        var actual = Day03.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(List.of(
                new BatteryBank(List.of(9,8,7,6,5,4,3,2,1,1,1,1,1,1,1)),
                new BatteryBank(List.of(8,1,1,1,1,1,1,1,1,1,1,1,1,1,9)),
                new BatteryBank(List.of(2,3,4,2,3,4,2,3,4,2,3,4,2,7,8)),
                new BatteryBank(List.of(8,1,8,1,8,1,9,1,1,1,1,2,1,1,1))
        ));
    }

    @Test
    @Disabled
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day03.solvePart1(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/3/input
        RawProblemInput input = RawProblemInput.fromResource("/day03.txt");

        // when
        var result = Day03.solvePart1(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day03.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day03.txt");

        // when
        var result = Day03.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

