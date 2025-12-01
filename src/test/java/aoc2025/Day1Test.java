package aoc2025;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1Test extends DayTest {
    @Override
    protected Day dayInstance() {
        return new Day1();
    }

    @Override
    protected String part1ExampleInput() {
        return """
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
                """;
    }

    @Override
    protected long part1ExampleResult() {
        return 3;
    }

    @Override
    protected long part1Result() {
        return 1120;
    }

    @Override
    protected long part2ExampleResult() {
        return 6;
    }

    @Override
    protected long part2Result() {
        return 6554;
    }

    @Nested
    class DialTest {

        @ParameterizedTest
        @CsvSource({
                "50, L, 10, 40, 0",
                "50, R, 10, 60, 0 ",
                "0, L, 1, 99, 0",
                "1, L, 1, 0, 1",
                "1, L, 2, 99, 1",
                "99, R, 1, 0, 1",
                "5, L, 10, 95, 1",
                "95, R, 10, 5, 1",
                "5, L, 110, 95, 2",
                "95, R, 110, 5, 2",
                "0, L, 101, 99, 1",
                "1, L, 101, 0, 2",
                "1, L, 102, 99, 2",
        })
        void testRotate(int startPosition, char directionChar, int steps, int expectedPosition, int expectedCountZeroClicks) {
            Day1.Dial dial = new Day1.Dial(startPosition);
            Day1.Dial.Direction direction = Day1.Dial.Direction.fromChar(directionChar);

            Day1.Dial.RotationInfo rotate = dial.rotate(steps, direction);

            assertThat(rotate.position()).as("Position").isEqualTo(expectedPosition);
            assertThat(rotate.countZeroClicks()).as("CountZeroClicks").isEqualTo(expectedCountZeroClicks);
        }

    }

}
