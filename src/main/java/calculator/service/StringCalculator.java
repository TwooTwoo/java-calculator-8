package calculator.service;

import calculator.util.StringSplitter;

public class StringCalculator {

    public boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public int calculate(String input) {
        if (isEmpty(input)) {
            return 0;
        }

        String[] splittedNumbers = StringSplitter.split(input);

        return getSum(splittedNumbers);
    }

    public static int getSum(String[] strings) {
        int sum = 0;
        for (String s : strings) {
            if (s.isEmpty()) continue;
            try {
                sum += Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("올바른 값을 입력해주세요");
            }
        }

        return sum;
    }
}
