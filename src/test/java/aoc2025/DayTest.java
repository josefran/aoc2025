package aoc2025;

import aoc2025.utils.InputProvider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class DayTest {

    protected String input() {
        String resourceName = dayInstance().getClass().getSimpleName().toLowerCase() + "/input";
        return InputProvider.inputToString(resourceName);
    }
    protected abstract Day dayInstance();
    protected abstract String part1ExampleInput();
    protected String part2ExampleInput() {
        return part1ExampleInput();
    }

    protected abstract long part1ExampleResult();

    protected abstract long part1Result();

    protected abstract long part2ExampleResult();

    protected abstract long part2Result();

    @Test
    void testPart1Example() {
        long result = dayInstance().executePart1(part1ExampleInput());
        System.out.println(result);
        assertThat(result).isEqualTo(part1ExampleResult());
    }

    @Test
    void testPart1() {
        long result = dayInstance().executePart1(input());
        System.out.println(result);
        assertThat(result).isEqualTo(part1Result());
    }

    @Test
    void testPart2Example() {
        long result = dayInstance().executePart2(part2ExampleInput());
        System.out.println(result);
        assertThat(result).isEqualTo(part2ExampleResult());
    }

    @Test
    void testPart2() {
        long result = dayInstance().executePart2(input());
        System.out.println(result);
        assertThat(result).isEqualTo(part2Result());
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
