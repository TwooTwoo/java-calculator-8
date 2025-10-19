package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = getInput();

        if (validateIsEmpty(input)) {
            return;
        }

        String[] splittedNumbers = null;
        if (isCustomDelimiterUsed(input)) {
            splittedNumbers = splitWithCustomDelimiter(input);
        } else {
            splittedNumbers = splitWithDefaultDelimiter(input);
        }

        System.out.println("결과: " + getSum(splittedNumbers));
    }

    public static boolean validateIsEmpty(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("결과 : 0");
            return true;
        }

        return false;
    }

    public static String getInput() {
        return Console.readLine();
    }

    public static boolean isCustomDelimiterUsed(String input) {
        return input.matches("//(.*?)\\\\n(.*)");
    }

    public static String[] splitWithCustomDelimiter(String input) {
        String customDelimiter = makeCustomDelimeter(input);

        return splitNumberPart(input, customDelimiter);
    }

    public static String makeCustomDelimeter(String input) {
        return "(,|:|" + Pattern.quote(distractCustomDelimiter(input)) + ")";
    }

    public static String distractCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile("//(.*?)\\\\n(.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("커스텀 구분자를 찾을 수 없습니다");
    }

    // 정규식으로 추출한 숫자 부분을 구분자로 split하는 메서드
    public static String[] splitNumberPart(String input, String delimiter) {
        return distractRegexPart2(input).split(delimiter);
    }

    public static String distractRegexPart2(String input) {
        Pattern pattern = Pattern.compile("//(.*?)\\\\n(.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(2);
        }
        throw new IllegalArgumentException("숫자 부분을 찾을 수 없습니다");
    }

    public static String[] splitWithDefaultDelimiter(String input) {
        String defaultDelimiter = "[,|:]";

        return input.split(defaultDelimiter);
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
