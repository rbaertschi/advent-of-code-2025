package ch.ebynaqon.aoc.aoc25.day01;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/1
class Day01Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
                """);

        // when
        var actual = Day01.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(List.of(
                -68,
                -30,
                48,
                -5,
                60,
                -55,
                -1,
                -99,
                14,
                -82
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
        var result = Day01.solvePart1(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/1/input
        RawProblemInput input = RawProblemInput.fromResource("/day01.txt");

        // when
        var result = Day01.solvePart1(input);

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
        var result = Day01.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day01.txt");

        // when
        var result = Day01.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

