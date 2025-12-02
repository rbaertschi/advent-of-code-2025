package ch.ebynaqon.aoc.aoc25.day02;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/2
class Day02Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124
                """);

        // when
        var actual = Day02.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(List.of(
                new IdRange(11, 22),
                new IdRange(95, 115),
                new IdRange(998, 1012),
                new IdRange(1188511880, 1188511890),
                new IdRange(222220, 222224),
                new IdRange(1698522, 1698528),
                new IdRange(446443, 446449),
                new IdRange(38593856, 38593862),
                new IdRange(565653, 565659),
                new IdRange(824824821, 824824827),
                new IdRange(2121212118, 2121212124)
        ));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124
                """);

        // when
        var result = Day02.solvePart1(input);

        // then
        assertThat(result).isEqualTo(1227775554);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/2/input
        RawProblemInput input = RawProblemInput.fromResource("/day02.txt");

        // when
        var result = Day02.solvePart1(input);

        // then
        assertThat(result).isEqualTo(12586854255L);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day02.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day02.txt");

        // when
        var result = Day02.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

