package aoc2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 implements Day {
    @Override
    public long executePart1(String input) {
        List<InvalidPattern> invalidIdPatterns = List.of(new EqualHalvesPattern());
        return new InvalidIdDetector(invalidIdPatterns).detect(input);
    }

    @Override
    public long executePart2(String input) {
        List<InvalidPattern> invalidIdPatterns = List.of(new OnlyOfSomeSequenceRepeatedAtLeastTwicePattern());
        return new InvalidIdDetector(invalidIdPatterns).detect(input);
    }

    static class InvalidIdDetector {

        final String RANGE_SEPARATOR = ",";
        final List<InvalidPattern> invalidIdPatterns;

        public InvalidIdDetector(List<InvalidPattern> invalidIdPatterns) {
            this.invalidIdPatterns = invalidIdPatterns;
        }

        long detect(String input) {
            String[] ranges = input.trim().split(RANGE_SEPARATOR);
            List<RangeId> rangeIdList = Arrays.stream(ranges)
                    .map(RangeId::build)
                    .toList();
            return rangeIdList.stream()
                    .flatMap(rangeId -> invalidIds(rangeId).stream())
                    .mapToLong(Long::longValue)
                    .sum();
        }

        List<Long> invalidIds(RangeId rangeId) {
            List<Long> invalidIds = new ArrayList<>();
            for (long id = rangeId.startId(); id <= rangeId.endId(); id++) {
                long finalId = id;
                boolean isInvalid = invalidIdPatterns.stream()
                        .anyMatch(p -> p.matches(finalId));
                if (isInvalid) {
                    invalidIds.add(id);
                }
            }
            return invalidIds;
        }
    }

    record RangeId(long startId, long endId) {

        public static final String ID_SEPARATOR = "-";

        private static RangeId build(String range) {
            String[] rangeIds = range.split(ID_SEPARATOR);
            long startId = Long.parseLong(rangeIds[0]);
            long startEnd = Long.parseLong(rangeIds[1]);
            return new RangeId(startId, startEnd);
        }
    }

    interface InvalidPattern {
        boolean matches(long id);
    }

    static class EqualHalvesPattern implements InvalidPattern {
        @Override
        public boolean matches(long id) {
            int length = String.valueOf(id).length();
            if (isOdd(length)) return false;

            String idStr = String.valueOf(id);
            String firstHalfStr = idStr.substring(0, length / 2);
            String secondHalfStr = idStr.substring(length / 2);
            return firstHalfStr.equals(secondHalfStr);
        }
    }

    static class OnlyOfSomeSequenceRepeatedAtLeastTwicePattern implements InvalidPattern {
        @Override
        public boolean matches(long id) {
            String stringId = String.valueOf(id);
            int length = stringId.length();
            for (int seqLength = 1; seqLength <= length / 2; seqLength++) {
                if (isNotDivisibleBy(length, seqLength)) continue;
                String sequence = stringId.substring(0, seqLength);
                int repetitions = length / seqLength;
                String repeatedSequence = sequence.repeat(repetitions);
                if (repeatedSequence.equals(stringId)) return true;
            }
            return false;
        }

    }

    private static boolean isDivisibleBy(int x, int y) {
        return x % y == 0;
    }

    private static boolean isNotDivisibleBy(int x, int y) {
        return !isDivisibleBy(x, y);
    }

    private static boolean isEven(int length) {
        return length % 2 == 0;
    }

    private static boolean isOdd(int length) {
        return !isEven(length);
    }

}
