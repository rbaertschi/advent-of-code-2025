package ch.ebynaqon.aoc.aoc25.day08;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2025/day/8
class Day08Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                162,817,812
                57,618,57
                906,360,560
                592,479,940
                352,342,300
                466,668,158
                542,29,236
                431,825,988
                739,650,466
                52,470,668
                216,146,977
                819,987,18
                117,168,530
                805,96,715
                346,949,466
                970,615,88
                941,993,340
                862,61,35
                984,92,344
                425,690,689
                """);

        // when
        var actual = Day08.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(List.of(
                new JunctionBox(162, 817, 812),
                new JunctionBox(57, 618, 57),
                new JunctionBox(906, 360, 560),
                new JunctionBox(592, 479, 940),
                new JunctionBox(352, 342, 300),
                new JunctionBox(466, 668, 158),
                new JunctionBox(542, 29, 236),
                new JunctionBox(431, 825, 988),
                new JunctionBox(739, 650, 466),
                new JunctionBox(52, 470, 668),
                new JunctionBox(216, 146, 977),
                new JunctionBox(819, 987, 18),
                new JunctionBox(117, 168, 530),
                new JunctionBox(805, 96, 715),
                new JunctionBox(346, 949, 466),
                new JunctionBox(970, 615, 88),
                new JunctionBox(941, 993, 340),
                new JunctionBox(862, 61, 35),
                new JunctionBox(984, 92, 344),
                new JunctionBox(425, 690, 689)
        ));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                162,817,812
                57,618,57
                906,360,560
                592,479,940
                352,342,300
                466,668,158
                542,29,236
                431,825,988
                739,650,466
                52,470,668
                216,146,977
                819,987,18
                117,168,530
                805,96,715
                346,949,466
                970,615,88
                941,993,340
                862,61,35
                984,92,344
                425,690,689
                """);

        // when
        var result = Day08.solvePart1(input, 9);

        // then
        assertThat(result).isEqualTo(40);
    }

    @Test
    @Disabled
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/8/input
        RawProblemInput input = RawProblemInput.fromResource("/day08.txt");

        // when
        var result = Day08.solvePart1(input, 1000);

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
        var result = Day08.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day08.txt");

        // when
        var result = Day08.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

