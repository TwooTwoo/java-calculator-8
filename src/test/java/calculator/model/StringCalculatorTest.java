package calculator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    private final StringCalculator calculator = new StringCalculator();

    @Test
    void 빈문자열_입력시_결과는_0이다() {
        assertEquals(0, calculator.calculate(""));
    }

    @Test
    void 기본_구분자_콤마와_콜론으로_분리된_문자열_계산() {
        assertEquals(6, calculator.calculate("1,2:3"));
    }

    @Test
    void 커스텀_구분자_세미콜론으로_분리된_문자열_계산() {
        assertEquals(6, calculator.calculate("//;\\n1;2;3"));
    }

    @Test
    void 음수가_포함되면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("1,-2,3"));
        assertTrue(e.getMessage().contains("음수"));
    }

    @Test
    void 잘못된_숫자형식이_있으면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("1,a,3"));
        assertTrue(e.getMessage().contains("잘못된 숫자 형식"));
    }

    @Test
    void 빈항목이_있으면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("1,,2"));
        assertTrue(e.getMessage().contains("빈 숫자 항목"));
    }

}
