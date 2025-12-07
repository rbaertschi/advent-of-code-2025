package ch.ebynaqon.aoc.aoc25.day07;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/7
class Day07Test {

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                .......S.......
                ...............
                .......^.......
                ...............
                ......^.^......
                ...............
                .....^.^.^.....
                ...............
                ....^.^...^....
                ...............
                ...^.^...^.^...
                ...............
                ..^...^.....^..
                ...............
                .^.^.^.^.^...^.
                ...............
                """);

        // when
        var result = Day07.solvePart1(input);

        // then
        assertThat(result).isEqualTo(21);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/7/input
        RawProblemInput input = RawProblemInput.fromResource("/day07.txt");

        // when
        var result = Day07.solvePart1(input);

        // then
        assertThat(result).isEqualTo(1560);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day07.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day07.txt");

        // when
        var result = Day07.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

