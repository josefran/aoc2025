package aoc2025;

public class Day3 implements Day {
    @Override
    public long executePart1(String input) {
        return BatteryJoltageCalculator.calculate(input, 2);
    }

    @Override
    public long executePart2(String input) {
        return BatteryJoltageCalculator.calculate(input, 12);
    }

    static class BatteryJoltageCalculator {
        static long calculate(String input, int numActiveBattery) {
            return input.lines()
                    .map(BatteryBank::new)
                    .map(batteryBank -> batteryBank.maximumJoltage(numActiveBattery))
                    .mapToLong(Long::longValue)
                    .sum();
        }
    }

    record BatteryBank(String bankInput) {
        public long maximumJoltage(int numActiveBattery) {
            int[] digits = new int[numActiveBattery];
            int bankLength = bankInput.length();
            int rangeLength = bankLength - numActiveBattery;
            int indexLastDigit = 0;
            for (int digitIndex = 0; digitIndex < numActiveBattery; digitIndex++) {
                int rangeStart = indexLastDigit;
                int rangeEnd = digitIndex + rangeLength;
                for (int rangeIndex = rangeStart; rangeIndex <= rangeEnd; rangeIndex++) {
                    int numericValue = Character.getNumericValue(bankInput.charAt(rangeIndex));
                    if (rangeIndex == rangeStart) {
                        digits[digitIndex] = numericValue;
                        indexLastDigit = rangeIndex + 1;
                    } else if (digits[digitIndex] < numericValue) {
                        digits[digitIndex] = numericValue;
                        indexLastDigit = rangeIndex + 1;
                    }
                }
            }
            return buildJoltage(digits);
        }

        private static long buildJoltage(int[] digits) {
            StringBuilder sb = new StringBuilder();
            for (int digit : digits) {
                sb.append(digit);
            }
            return Long.parseLong(sb.toString());
        }

    }
}
