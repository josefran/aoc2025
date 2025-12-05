package aoc2025;

import java.util.*;

public class Day5 implements Day {
    @Override
    public long executePart1(String input) {
        String[] sections = input.split("\n\n");
        String[] rangeStrings = sections[0].split("\n");
        String[] valueStrings = sections[1].split("\n");
        Range[] ranges = buildRanges(rangeStrings);
        Ingredient[] ingredients = buildIngredients(valueStrings);
        return IngredientFreshnessChecker.countFreshIngredients(ranges, ingredients);
    }

    @Override
    public long executePart2(String input) {
        String[] sections = input.split("\n\n");
        String[] rangeStrings = sections[0].split("\n");
        Range[] ranges = buildRanges(rangeStrings);
        return IngredientFreshnessChecker.countFreshIdsInRange(ranges);
    }

    private static Ingredient[] buildIngredients(String[] valueStrings) {
        Ingredient[] ingredients = new Ingredient[valueStrings.length];
        for (int i = 0; i < valueStrings.length; i++) {
            ingredients[i] = new Ingredient(Long.parseLong(valueStrings[i]));
        }
        return ingredients;
    }

    private static Range[] buildRanges(String[] rangeStrings) {
        Range[] ranges = new Range[rangeStrings.length];
        for (int i = 0; i < rangeStrings.length; i++) {
            ranges[i] = Range.parseRange(rangeStrings[i]);
        }
        return ranges;
    }

    static class IngredientFreshnessChecker {

        static long countFreshIngredients(Range[] ranges, Ingredient[] ingredients) {
            long freshCount = 0;
            for (Ingredient ingredient : ingredients) {
                if (Arrays.stream(ranges).anyMatch(ingredient::isFresh)) {
                    freshCount++;
                }
            }
            return freshCount;
        }

        public static long countFreshIdsInRange(Range[] ranges) {
            Range[] merged = fusionRanges(ranges);
            return countMergedRanges(merged);
        }

        private static Range[] fusionRanges(Range[] ranges) {
            if (ranges == null || ranges.length == 0) return new Range[0];

            Range[] sorted = Arrays.copyOf(ranges, ranges.length);
            Arrays.sort(sorted, Comparator.comparingLong(Range::lower));

            List<Range> merged = new ArrayList<>();

            Range currentRange = sorted[0];
            for (Range next : sorted) {
                if (next.lower() <= currentRange.upper() + 1) {
                    long newLower = currentRange.lower();
                    long newUpper = Math.max(currentRange.upper(), next.upper());
                    currentRange = new Range(newLower, newUpper);
                } else {
                    merged.add(currentRange);
                    currentRange = next;
                }
            }
            merged.add(currentRange);
            return merged.toArray(Range[]::new);
        }

        private static long countMergedRanges(Range[] merged) {
            long total = 0L;
            for (Range r : merged) {
                long lower = r.lower();
                long upper = r.upper();
                if (lower <= upper) {
                    long length = upper - lower + 1;
                    total += length;
                }
            }
            return total;
        }

        public boolean isFresh(Range range, long value) {
            return range.contains(value);
        }
    }

    record Ingredient(long value) {
        boolean isFresh(Range range) {
            return range.contains(value);
        }
    }

    record Range(long lower, long upper) {

        static Range parseRange(String range) {
            String[] parts = range.split("-");
            long lower = Long.parseLong(parts[0]);
            long upper = Long.parseLong(parts[1]);
            return new Range(lower, upper);
        }

        public boolean contains(long value) {
            return value >= lower && value <= upper;
        }
    }
}
