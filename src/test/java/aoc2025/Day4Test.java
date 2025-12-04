package aoc2025;

public class Day4Test extends DayTest {
    @Override
    protected Day dayInstance() {
        return new Day4();
    }

    @Override
    protected String part1ExampleInput() {
        return """
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
                """;
    }

    @Override
    protected long part1ExampleResult() {
        return 13L;
    }

    @Override
    protected long part1Result() {
        return 1349L;
    }

    @Override
    protected long part2ExampleResult() {
        return 43L;
    }

    @Override
    protected long part2Result() {
        return 8277L;
    }

}
