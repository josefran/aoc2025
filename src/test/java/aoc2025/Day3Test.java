package aoc2025;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test extends DayTest {
    @Override
    protected Day dayInstance() {
        return new Day3();
    }

    @Override
    protected String part1ExampleInput() {
        return """
                987654321111111
                811111111111119
                234234234234278
                818181911112111
                """;
    }

    @Override
    protected long part1ExampleResult() {
        return 357L;
    }

    @Override
    protected long part1Result() {
        return 17278L;
    }

    @Override
    protected long part2ExampleResult() {
        return 3121910778619L;
    }

    @Override
    protected long part2Result() {
        return 171528556468625L;
    }

    @Nested
    class MaximumJoltageTest {
        // Add specific tests for MaximumJoltage here
        @ParameterizedTest
        @CsvSource({
            "987654321111111, 98",
            "811111111111119, 89",
            "234234234234278, 78",
            "818181911112111, 92"
        })
        void maximumJoltageWith2ActivateBatteriesTest(String bankInput, long expectedJoltage) {
            Day3.BatteryBank bank = new Day3.BatteryBank(bankInput);
            long maximumJoltage = bank.maximumJoltage(2);
            assertThat(maximumJoltage).as("Maximum Joltage").isEqualTo(expectedJoltage);
        }

        @ParameterizedTest
        @CsvSource({
                "987654321111111, 987654321111",
                "811111111111119, 811111111119",
                "234234234234278, 434234234278",
                "818181911112111, 888911112111"
        })
        void maximumJoltageWith12ActivateBatteriesTest(String bankInput, long expectedJoltage) {
            Day3.BatteryBank bank = new Day3.BatteryBank(bankInput);
            long maximumJoltage = bank.maximumJoltage(12);
            assertThat(maximumJoltage).as("Maximum Joltage").isEqualTo(expectedJoltage);
        }

    }
}
