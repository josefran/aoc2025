package aoc2025;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2Test extends DayTest {
    @Override
    protected Day dayInstance() {
        return new Day2();
    }

    @Override
    protected String part1ExampleInput() {
        return "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
    }

    @Override
    protected long part1ExampleResult() {
        return 1227775554;
    }

    @Override
    protected long part1Result() {
        return 44854383294L;
    }

    @Override
    protected long part2ExampleResult() {
        return 4174379265L;
    }

    @Override
    protected long part2Result() {
        return 55647141923L;
    }

    @Nested
    class OnlyOfSomeSequenceRepeatedAtLeastTwicePatternTest {

        @ParameterizedTest
        @ValueSource(longs = {12341234, 123123123, 1212121212, 1111111})
        void matches_ShouldReturnTrue_ForIdsWithSomeSequenceRepeatedAtLeastTwice(long id) {
            Day2.OnlyOfSomeSequenceRepeatedAtLeastTwicePattern pattern = new Day2.OnlyOfSomeSequenceRepeatedAtLeastTwicePattern();
            assertThat(pattern.matches(id)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(longs = {1, 101, 10101, 123456, 121345, 1231234})
        void matches_ShouldReturnFalse_ForIdsWithoutSomeSequenceRepeatedAtLeastTwice(long id) {
            Day2.OnlyOfSomeSequenceRepeatedAtLeastTwicePattern pattern = new Day2.OnlyOfSomeSequenceRepeatedAtLeastTwicePattern();
            assertThat(pattern.matches(id)).isFalse();
        }

    }
}
