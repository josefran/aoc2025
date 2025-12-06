package aoc2025;

import java.util.Arrays;
import java.util.List;

public class Day6 implements Day {
    @Override
    public long executePart1(String input) {
        Problem[] problems = buildProblems(input);
        return Arrays.stream(problems).mapToLong(Problem::resolve).sum();
    }

    @Override
    public long executePart2(String input) {
        Problem[] problems = buildProblemsForCephalopods(input);
        return Arrays.stream(problems).mapToLong(Problem::resolve).sum();
    }

    private Problem[] buildProblems(String input) {
        List<String> lines = input.lines().toList();
        Problem[] problems = prepareProblems(lines);
        List<String> linesWithoutOperators = lines.subList(0, lines.size() - 1);

        for (int i = 0; i < linesWithoutOperators.size(); i++) {
            String[] parts = linesWithoutOperators.get(i).trim().split("\\s+");
            for (int j = 0; j < problems.length; j++) {
                problems[j].addNumber(i, Long.parseLong(parts[j]));
            }
        }
        return problems;
    }

    private Problem[] buildProblemsForCephalopods(String input) {
        List<String> lines = input.lines().toList();
        Problem[] problems = prepareProblems(lines);

        List<String> linesWithoutOperators = lines.subList(0, lines.size() - 1);
        char[][] map = buildMap(linesWithoutOperators);

        int problemIndex = 0;
        int numberIndex = 0;
        for (int colIndex=0; colIndex < map[0].length; colIndex++) {
            if (isEmptyCol(map, colIndex)) {
                problemIndex++;
                numberIndex = 0;
            } else {
                for (int rowIndex=0; rowIndex<map.length; rowIndex++) {
                    StringBuilder numberBuilder = new StringBuilder();
                    while (rowIndex < map.length && Character.isDigit(map[rowIndex][colIndex])) {
                        numberBuilder.append(map[rowIndex][colIndex]);
                        rowIndex++;
                    }
                    if (!numberBuilder.isEmpty()) {
                        long number = Long.parseLong(numberBuilder.toString());
                        problems[problemIndex].addNumber(numberIndex, number);
                        numberIndex++;
                    }
                }
            }
        }
        return problems;
    }

    private char[][] buildMap(List<String> lines) {
        int rows = lines.size();
        int cols = lines.stream().mapToInt(String::length).max().orElse(0);
        char[][] map = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            String line = lines.get(r);
            for (int c = 0; c < cols; c++) {
                map[r][c] = c < line.length() ? line.charAt(c) : ' ';
            }
        }
        return map;
    }

    private boolean isEmptyCol(char[][] map, int colIndex) {
        for (char[] row : map) {
            if (row[colIndex] != ' ') return false;
        }
        return true;
    }

    private static Problem[] prepareProblems(List<String> lines) {
        String[] operators = lines.getLast().split("\\s+");
        return Arrays.stream(operators)
                .map(Operation::build)
                .map(Problem::new)
                .toArray(Problem[]::new);
    }


    static final class Problem {
        private long[] numbers;
        private final Operation operation;

        Problem(Operation operation) {
            this.numbers = new long[0];
            this.operation = operation;
        }

        public void addNumber(int index, long number) {
            if (index >= this.numbers.length) {
                long[] newNumbers = new long[index + 1];
                System.arraycopy(this.numbers, 0, newNumbers, 0, this.numbers.length);
                this.numbers = newNumbers;
            }
            this.numbers[index] = number;
        }

        public long resolve() {
            long result = operation == Operation.ADD ? 0 : 1;
            for (long number : numbers) {
                if (operation == Operation.ADD) {
                    result += number;
                } else if (operation == Operation.MULTIPLY) {
                    result *= number;
                }
            }
            return result;
        }
    }

    enum Operation {
        ADD, MULTIPLY;

        static Operation build(String symbol) {
            return symbol.equals("+") ? ADD : MULTIPLY;
        }
    }
}
