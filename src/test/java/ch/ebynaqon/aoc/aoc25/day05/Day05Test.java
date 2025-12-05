package ch.ebynaqon.aoc.aoc25.day05;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Nested;
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
                1L,
                5L,
                8L,
                11L,
                17L,
                32L
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
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/5/input
        RawProblemInput input = RawProblemInput.fromResource("/day05.txt");

        // when
        var result = Day05.solvePart1(input);

        // then
        assertThat(result).isEqualTo(635);
    }

    @Test
    void solvePart2UsingExample() {
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
        var result = Day05.solvePart2(input);

        // then
        assertThat(result).isEqualTo(14);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day05.txt");

        // when
        var result = Day05.solvePart2(input);

        // then
        assertThat(result).isEqualTo(369761800782619L);
    }

    @Nested
    class FreshRangeTest {
        @Test
        void intersect_with_non_intersecting_before() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(-14, -4));
            assertThat(actual).isFalse();
        }

        @Test
        void intersect_with_non_intersecting_after() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(14, 20));
            assertThat(actual).isFalse();
        }

        @Test
        void intersect_with_intersecting_overflow_end() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(4, 20));
            assertThat(actual).isTrue();
        }

        @Test
        void intersect_with_intersecting_overflow_start() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(-4, 5));
            assertThat(actual).isTrue();
        }

        @Test
        void intersect_with_containing() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(4, 8));
            assertThat(actual).isTrue();
        }

        @Test
        void intersect_with_contained() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(-4, 18));
            assertThat(actual).isTrue();
        }

        @Test
        void intersect_with_connected_end() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(11, 20));
            assertThat(actual).isFalse();
        }

        @Test
        void intersect_with_connected_start() {
            boolean actual = new FreshRange(1, 10).intersects(new FreshRange(-5, 0));
            assertThat(actual).isFalse();
        }
    }
}

