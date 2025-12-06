package ch.ebynaqon.aoc.aoc25.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/6
class Day06Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   + \s
                """);

        // when
        var actual = Day06.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(List.of(
                new Multiplication(List.of(123, 45, 6)),
                new Addition(List.of(328, 64, 98)),
                new Multiplication(List.of(51, 387, 215)),
                new Addition(List.of(64, 23, 314))
        ));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   + \s
                """);

        // when
        var result = Day06.solvePart1(input);

        // then
        assertThat(result).isEqualTo(4277556);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/6/input
        RawProblemInput input = RawProblemInput.fromResource("/day06.txt");

        // when
        var result = Day06.solvePart1(input);

        // then
        assertThat(result).isEqualTo(4878670269096L);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day06.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day06.txt");

        // when
        var result = Day06.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

