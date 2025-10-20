package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class CalculatorView {

    public String getInput() {
        return Console.readLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printResult(int result) {
        System.out.println("결과 : " + result);
    }

}
