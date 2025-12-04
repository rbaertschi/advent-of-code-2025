package ch.ebynaqon.aoc.aoc25.day04;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/4
class Day04Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
                """);

        // when
        var actual = Day04.parseProblem(input);

        // then
        assertThat(actual).usingRecursiveComparison().isEqualTo(new PaperRollMap(
                new int[][]{
                        {0, 0, 1, 1, 0, 1, 1, 1, 1, 0},
                        {1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
                        {1, 0, 1, 1, 1, 1, 0, 0, 1, 0},
                        {1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
                        {0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0}
                }
        ));
    }

    @Test
    void mapAccessWorks() {
        PaperRollMap map = new PaperRollMap(
                new int[][]{
                        {0, 0, 1},
                        {1, 1, 1},
                        {1, 1, 0}
                }
        );

        assertThat(map.get(-1,-1)).isEqualTo(0);
        assertThat(map.get(-1,0)).isEqualTo(0);
        assertThat(map.get(-1,1)).isEqualTo(0);
        assertThat(map.get(-1,2)).isEqualTo(0);
        assertThat(map.get(-1,3)).isEqualTo(0);

        assertThat(map.get(0,-1)).isEqualTo(0);
        assertThat(map.get(0,0)).isEqualTo(0);
        assertThat(map.get(0,1)).isEqualTo(0);
        assertThat(map.get(0,2)).isEqualTo(1);
        assertThat(map.get(0,3)).isEqualTo(0);

        assertThat(map.get(1,-1)).isEqualTo(0);
        assertThat(map.get(1,0)).isEqualTo(1);
        assertThat(map.get(1,1)).isEqualTo(1);
        assertThat(map.get(1,2)).isEqualTo(1);
        assertThat(map.get(1,3)).isEqualTo(0);

        assertThat(map.get(2,-1)).isEqualTo(0);
        assertThat(map.get(2,0)).isEqualTo(1);
        assertThat(map.get(2,1)).isEqualTo(1);
        assertThat(map.get(2,2)).isEqualTo(0);
        assertThat(map.get(2,3)).isEqualTo(0);

        assertThat(map.get(3,-1)).isEqualTo(0);
        assertThat(map.get(3,0)).isEqualTo(0);
        assertThat(map.get(3,1)).isEqualTo(0);
        assertThat(map.get(3,2)).isEqualTo(0);
        assertThat(map.get(3,3)).isEqualTo(0);
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
                """);

        // when
        var result = Day04.solvePart1(input);

        // then
        assertThat(result).isEqualTo(13);
    }

    @Test
    @Disabled
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/4/input
        RawProblemInput input = RawProblemInput.fromResource("/day04.txt");

        // when
        var result = Day04.solvePart1(input);

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
        var result = Day04.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day04.txt");

        // when
        var result = Day04.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

