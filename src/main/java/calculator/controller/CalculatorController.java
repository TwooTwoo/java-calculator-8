package calculator.controller;

import calculator.view.CalculatorView;
import calculator.service.StringCalculator;

public class CalculatorController {
    private final CalculatorView view = new CalculatorView();
    private final StringCalculator calculator = new StringCalculator();

    public void run() {
        view.printMessage("덧셈할 문자열을 입력해 주세요.");
        String input = view.getInput();

        if (calculator.isEmpty(input)) {
            view.printResult(0);
            return;
        }

        int result = calculator.calculate(input);
        view.printResult(result);
    }

}
