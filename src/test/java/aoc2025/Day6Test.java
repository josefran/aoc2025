package aoc2025;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Test extends DayTest {
    @Override
    protected Day dayInstance() {
        return new Day6();
    }

    @Override
    protected String part1ExampleInput() {
        return """
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
                """;
    }

    @Override
    protected long part1ExampleResult() {
        return 4277556L;
    }

    @Override
    protected long part1Result() {
        return 5060053676136L;
    }

    @Override
    protected long part2ExampleResult() {
        return 3263827L;
    }

    @Override
    protected long part2Result() {
        return 9695042567249L;
    }

    @Nested
    class ProblemResolveTests {
        @ParameterizedTest
        @CsvSource
        ({
            "123, 45, 6, *, 33210",
            "328, 64, 98, +, 490",
            "51, 387, 215, *, 4243455",
            "64, 23, 314, +, 401"
        })
        void testProblemResolve(long num1, long num2, long num3, String opSymbol, long expectedResult) {
            Day6.Operation operation = Day6.Operation.build(opSymbol);
            Day6.Problem problem = new Day6.Problem(operation);
            problem.addNumber(0, num1);
            problem.addNumber(1, num2);
            problem.addNumber(2, num3);
            long result = problem.resolve();
            assertThat(result).isEqualTo(expectedResult);
        }

    }

}
