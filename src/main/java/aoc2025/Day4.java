package aoc2025;

import java.util.List;

public class Day4 implements Day {
    @Override
    public long executePart1(String input) {
        // Implement the logic for part 1
        return RollsOfPaperCalculator.countAccesibleRolls(input);
    }

    @Override
    public long executePart2(String input) {
        return RollsOfPaperCalculator.countAccesibleRollsWithIterations(input);
    }

    private static class RollsOfPaperCalculator {

        private static final char ROLL_OF_PAPER = '@';
        private static final char REMOVED_ROLL = 'x';
        private static final int ACCESIBLE_THRESHOLD = 4;

        static long countAccesibleRolls(String input) {
            Grid grid = buildGrid(input);
            return replaceAndCountAccesibleRolls(grid).count();
        }

        static long countAccesibleRollsWithIterations(String input) {
            Grid grid = buildGrid(input);
            long totalCount = 0;
            boolean changed = true;
            while (changed) {
                Result result = replaceAndCountAccesibleRolls(grid);
                totalCount += result.count();
                changed = result.count() > 0;
                grid = result.grid();
            }
            return totalCount;
        }

        private static Result replaceAndCountAccesibleRolls(Grid grid) {
            Grid newGrid = new Grid(grid.cells);
            long count = 0;
            for (int col = 0; col < grid.cols(); col++) {
                for (int row = 0; row < grid.rows(); row++) {
                    char cell = grid.get(row, col);
                    if (cell == ROLL_OF_PAPER) {
                        long adjacentCount = grid.countAdjacentChar(row, col, ROLL_OF_PAPER);
                        if (adjacentCount < ACCESIBLE_THRESHOLD) {
                            newGrid.set(row, col, REMOVED_ROLL);
                            count++;
                        }
                    }
                }
            }
            return new Result(newGrid, count);
        }

        private static Grid buildGrid(String input) {
            List<String> lines = input.lines().toList();
            char[][] cells = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                cells[i] = lines.get(i).toCharArray();
            }
            return new Grid(cells);
        }
    }

    record Result(Grid grid, long count) {
    }

    static final class Grid {

        private final char[][] cells;

        Grid(char[][] cells) {
            int cols = cells[0].length;
            char[][] copy = new char[cells.length][cols];
            for (int i = 0; i < cells.length; i++) {
                System.arraycopy(cells[i], 0, copy[i], 0, cols);
            }
            this.cells = copy;
        }

        int rows() {
            return cells.length;
        }

        int cols() {
            return cells[0].length;
        }

        char get(int row, int col) {
            return cells[row][col];
        }

        @SuppressWarnings("SameParameterValue")
        void set(int row, int col, char value) {
            cells[row][col] = value;
        }

        char[] adjacents(int row, int col) {
            StringBuilder adjacentChars = new StringBuilder();
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue;
                    int newRow = row + dr;
                    int newCol = col + dc;
                    if (newRow >= 0 && newRow < rows() && newCol >= 0 && newCol < cols()) {
                        adjacentChars.append(get(newRow, newCol));
                    }
                }
            }
            return adjacentChars.toString().toCharArray();
        }


        @SuppressWarnings("SameParameterValue")
        long countAdjacentChar(int row, int col, char charToCount) {
            char[] adjacents = adjacents(row, col);
            long adjacentCount = 0;
            for (char adjacent : adjacents) {
                if (adjacent == charToCount) {
                    adjacentCount++;
                }
            }
            return adjacentCount;
        }

    }
}
