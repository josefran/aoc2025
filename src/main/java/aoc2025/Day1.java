package aoc2025;

import java.util.List;

public class Day1 implements Day {
    @Override
    public long executePart1(String input) {
        return PasswordDecoder.decode(input);
    }

    @Override
    public long executePart2(String input) {
        return PasswordDecoder.decode0x434C49434B(input);
    }

    static class PasswordDecoder {
        public static long decode(String input) {
            List<String> lines = input.lines().toList();
            Dial dial = new Dial();
            Dial.RotationInfo rotationInfo;
            long countZeroPositions = 0;
            for (String line : lines) {
                Dial.Direction direction = Dial.Direction.fromChar(line.charAt(0));
                int steps = Integer.parseInt(line.substring(1));
                rotationInfo = dial.rotate(steps, direction);
                if (rotationInfo.position == 0) countZeroPositions++;
            }
            return countZeroPositions;
        }

        public static long decode0x434C49434B(String input) {
            List<String> lines = input.lines().toList();
            Dial dial = new Dial();
            long countZeroClicks = 0;
            for (String line : lines) {
                Dial.Direction direction = Dial.Direction.fromChar(line.charAt(0));
                int steps = Integer.parseInt(line.substring(1));
                Dial.RotationInfo turn = dial.rotate(steps, direction);
                countZeroClicks += turn.countZeroClicks();
            }
            return countZeroClicks;
        }

    }

    static class Dial {
        private final static int MIN = 0;
        private final static int MAX = 99;
        private final static int START_POSITION = 50;
        private int position;

        Dial() {
            this(START_POSITION);
        }

        Dial(int position) {
            this.position = position;
        }

        RotationInfo rotate(int steps, Direction direction) {
            if (steps <= 0) {
                throw new IllegalArgumentException("Steps must be non-negative or zero");
            }
            if (direction == Direction.LEFT) {
                return rotateLeft(steps);
            } else {
                return rotateRight(steps);
            }
        }

        private RotationInfo rotateLeft(int steps) {
            int size = MAX - MIN + 1;
            int countZeroClicks;
            countZeroClicks = calculateCountZeroClicksForLeftRotation(steps, size);
            position = Math.floorMod(position - steps, size);
            return new RotationInfo(position, countZeroClicks);
        }

        private RotationInfo rotateRight(int steps) {
            int size = MAX - MIN + 1;
            int countZeroClicks = calculateCountZeroClicksForRightRotation(steps, size);
            position = Math.floorMod(position + steps, size);
            return new RotationInfo(position, countZeroClicks);
        }

        private int calculateCountZeroClicksForLeftRotation(int steps, int size) {
            int countZeroClicks;
            if (steps < position) {
                countZeroClicks = 0;
            } else if (position == 0) {
                countZeroClicks = Math.floorDiv(steps, size);
            } else {
                countZeroClicks = Math.floorDiv(steps - position, size) + 1;
            }
            return countZeroClicks;
        }

        private int calculateCountZeroClicksForRightRotation(int steps, int size) {
            return Math.floorDiv(position + steps, size);
        }

        record RotationInfo(int position, int countZeroClicks) {
        }

        enum Direction {
            LEFT("L"),
            RIGHT("R");

            private final String letter;

            Direction(String letter) {
                this.letter = letter;
            }

            static Direction fromChar(char c) {
                for (Direction d : values()) {
                    if (d.letter.charAt(0) == c) return d;
                }
                throw new IllegalArgumentException("Invalid direction: " + c);
            }
        }
    }

}
