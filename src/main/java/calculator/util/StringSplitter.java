package calculator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitter {
    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final String CUSTOM_DELIMITER = "//(.*?)\\\\n(.*)";

    public static String[] split(String input) {
        if (hasCustomDelimiter(input)) {
            Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String delimiter = matcher.group(1);
                String numbers = matcher.group(2);
                return numbers.split("[,:]|" + Pattern.quote(delimiter));
            }
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }

        return input.split(DEFAULT_DELIMITER);
    }

    public static boolean hasCustomDelimiter(String input) {
        return (input != null) && input.startsWith("//");
    }

}
