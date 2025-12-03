package ch.ebynaqon.aoc.aoc25.day03;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(List.of(
                new BatteryBank(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1),
                new BatteryBank(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9),
                new BatteryBank(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8),
                new BatteryBank(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1)
        ));
    }

    @Nested
    class BatteryBankTest {
        public static Stream<Arguments> batteryBanksWith2BatteriesActive() {
            return Stream.of(
                    Arguments.of(new BatteryBank(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1), 98L),
                    Arguments.of(new BatteryBank(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9), 89L),
                    Arguments.of(new BatteryBank(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8), 78L),
                    Arguments.of(new BatteryBank(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1), 92L)
            );
        }
        public static Stream<Arguments> batteryBanksWith12BatteriesActive() {
            return Stream.of(
                    Arguments.of(new BatteryBank(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1), 987654321111L),
                    Arguments.of(new BatteryBank(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9), 811111111119L),
                    Arguments.of(new BatteryBank(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8), 434234234278L),
                    Arguments.of(new BatteryBank(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1), 888911112111L)
            );
        }

        @ParameterizedTest
        @MethodSource("batteryBanksWith2BatteriesActive")
        void getMaxJoltageWithTwoBatteries(BatteryBank batteryBank, Long expectedJoltage) {
            // when
            long actual = batteryBank.getMaxJoltageWithNBatteries(2);

            // then
            assertThat(actual).isEqualTo(expectedJoltage);
        }

        @ParameterizedTest
        @MethodSource("batteryBanksWith12BatteriesActive")
        void getMaxJoltageWithTwelveBatteries(BatteryBank batteryBank, Long expectedJoltage) {
            // when
            long actual = batteryBank.getMaxJoltageWithNBatteries(12);

            // then
            assertThat(actual).isEqualTo(expectedJoltage);
        }
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                987654321111111
                811111111111119
                234234234234278
                818181911112111
                """);

        // when
        var result = Day03.solvePart1(input);

        // then
        assertThat(result).isEqualTo(357);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2025/day/3/input
        RawProblemInput input = RawProblemInput.fromResource("/day03.txt");

        // when
        var result = Day03.solvePart1(input);

        // then
        assertThat(result).isEqualTo(17766);
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                987654321111111
                811111111111119
                234234234234278
                818181911112111
                """);

        // when
        var result = Day03.solvePart2(input);

        // then
        assertThat(result).isEqualTo(3121910778619L);
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

