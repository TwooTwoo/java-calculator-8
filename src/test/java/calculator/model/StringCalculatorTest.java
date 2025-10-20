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
    void 기본_구분자로_분리된_문자열_계산() {
        assertEquals(6, calculator.calculate("1,2:3"));
    }

    @Test
    void 커스텀_구분자_세미콜론으로_분리된_문자열_계산() {
        assertEquals(6, calculator.calculate("//;\\n1;2;3"));
    }

    @Test
    void 커스텀_구분자와_기본_구분자_혼용시_모두_인식된다() {
        assertEquals(6, calculator.calculate("//;\\n1;2,3"));
    }

    @Test
    void 커스텀_구분자로_정규식_특수문자를_사용해도_정상작동한다() {
        assertEquals(6, calculator.calculate("//.\\n1.2.3"));
        assertEquals(6, calculator.calculate("//|\\n1|2|3"));
    }

    @Test
    void 연속된_커스텀_구분자가_있으면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("//;\\n1;;2;3"));
        assertTrue(e.getMessage().contains("빈칸이 포함되어 있습니다."));
    }

    @Test
    void 음수가_포함되면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("1,-2,3"));
        assertTrue(e.getMessage().contains("음수는 허용되지 않습니다"));
    }

    @Test
    void 잘못된_숫자형식이_있으면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("1,a,3"));
        assertTrue(e.getMessage().contains("잘못된 숫자 형식이 포함되어 있습니다"));
    }

    @Test
    void 빈항목이_있으면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("1,,2"));
        assertTrue(e.getMessage().contains("빈칸이 포함되어 있습니다"));
    }

    @Test
    void 숫자_주변에_공백이_있어도_정상_계산된다() {
        assertEquals(6, calculator.calculate(" 1 , 2 : 3 "));
        assertEquals(6, calculator.calculate("//;\\n 1 ; 2 ; 3 "));
    }

    @Test
    void 숫자에_0이_포함되어도_정상적으로_합산된다() {
        assertEquals(3, calculator.calculate("0,1,2"));
    }

    @Test
    void split_결과가_빈배열이면_예외발생() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("//;\n"));
    }

}
