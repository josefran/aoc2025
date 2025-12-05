package aoc2025;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Test extends DayTest {
    @Override
    protected Day dayInstance() {
        return new Day5();
    }

    @Override
    protected String part1ExampleInput() {
        return """
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
                """;
    }

    @Override
    protected long part1ExampleResult() {
        return 3L;
    }

    @Override
    protected long part1Result() {
        return 529L;
    }

    @Override
    protected long part2ExampleResult() {
        return 14L;
    }

    @Override
    protected long part2Result() {
        return 344260049617193L;
    }

    @Nested
    class FreshTest {

        @ParameterizedTest
        @org.junit.jupiter.params.provider.CsvSource({
                "3-5,4,true",
                "10-14,9,false",
                "16-20,20,true",
                "12-18,19,false"
        })
        void testIsFresh(String rangeString, int number, boolean expected) {
            Day5.Range range = Day5.Range.parseRange(rangeString);
            Day5.IngredientFreshnessChecker checker = new Day5.IngredientFreshnessChecker();
            boolean result = checker.isFresh(range, number);
            assertThat(result).isEqualTo(expected);
        }
    }
}
