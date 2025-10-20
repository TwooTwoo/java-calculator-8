package calculator.model;

import calculator.util.StringSplitter;

public class StringCalculator {

    public boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public int calculate(String input) {
        if (isEmpty(input)) {
            return 0;
        }

        String[] numbers = StringSplitter.split(input);

        return getSum(numbers);
    }

    public static int getSum(String[] numbers) {
        validateNumbers(numbers);

        int sum = 0;
        for (String s : numbers) {
            int num = parseNumber(s);
            sum += num;
        }

        return sum;
    }

    private static void validateNumbers(String[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("숫자가 입력되지 않았습니다.");
        }

        for (String s : numbers) {
            if (s == null || s.trim().isEmpty()) {
                throw new IllegalArgumentException("빈 숫자 항목이 포함되어 있습니다.");
            }
        }
    }

    private static int parseNumber(String s) {
        try {
            int num = Integer.parseInt(s.trim());
            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 숫자 형식이 포함되어 있습니다: " + s);
        }
    }

}
