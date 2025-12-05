package ch.ebynaqon.aoc.aoc25.day05;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/5
class Day05Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                3-5
                10-14
                16-20
                12-18
                
                1
                5
                8
                11
                17
                32
                """);

        // when
        var actual = Day05.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new FreshRange(3, 5),
                new FreshRange(10, 14),
                new FreshRange(16, 20),
                new FreshRange(12, 18)
        ), List.of(
                1,
                5,
                8,
                11,
                17,
                32
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                3-5
                10-14
                16-20
                12-18
                
                1
                5
                8
                11
                17
                32
                """);

        // when
        var result = Day05.solvePart1(input);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @Disabled
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/5/input
        RawProblemInput input = RawProblemInput.fromResource("/day05.txt");

        // when
        var result = Day05.solvePart1(input);

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
        var result = Day05.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day05.txt");

        // when
        var result = Day05.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

