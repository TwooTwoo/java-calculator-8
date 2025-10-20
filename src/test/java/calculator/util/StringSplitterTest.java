package calculator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringSplitterTest {

    @Test
    void 기본_구분자로_분리() {
        String[] result = StringSplitter.split("1,2:3");
        assertArrayEquals(new String[]{"1", "2", "3"}, result);
    }

    @Test
    void 한개짜리_커스텀_구분자로_분리() {
        String[] result = StringSplitter.split("//;\\n1;2;3");
        assertArrayEquals(new String[]{"1", "2", "3"}, result);
    }

    @Test
    void 두개짜리_커스텀_구분자로_분리() {
        String[] result = StringSplitter.split("//;;\\n1;;2;;3");
        assertArrayEquals(new String[]{"1", "2", "3"}, result);
    }

    @Test
    void 세개짜리_커스텀_구분자로_분리() {
        String[] result = StringSplitter.split("//***\\n1***2***3");
        assertArrayEquals(new String[]{"1", "2", "3"}, result);
    }

    @Test
    void 커스텀_구분자_형식_잘못되면_예외() {
        assertThrows(IllegalArgumentException.class,
                () -> StringSplitter.split("//;1;2;3"));
    }

    @Test
    void 커스텀_구분자_줄바꿈_누락시_예외() {
        assertThrows(IllegalArgumentException.class,
                () -> StringSplitter.split("//;\1;2;3"));
    }

    @Test
    void 커스텀_구분자_슬래시_초과입력시_예외() {
        assertThrows(IllegalArgumentException.class,
                () -> StringSplitter.split("///;\1;2;3"));
    }

    @Test
    void 커스텀_구분자_여부_검사() {
        assertTrue(StringSplitter.hasCustomDelimiter("//;\\n1;2"));
        assertFalse(StringSplitter.hasCustomDelimiter("1,2,3"));
    }

}
