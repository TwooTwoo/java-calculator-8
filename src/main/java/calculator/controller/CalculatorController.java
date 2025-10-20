package calculator.controller;

import calculator.view.CalculatorView;
import calculator.model.StringCalculator;

public class CalculatorController {
    private final CalculatorView view = new CalculatorView();
    private final StringCalculator calculator = new StringCalculator();

    public void run() {
        view.printMessage("덧셈할 문자열을 입력해 주세요.");
        String input = view.getInput();

        int result = calculator.calculate(input);
        view.printResult(result);
    }

}
