package myCalculator;

/**
 * Created by gungr on 29/11/2016.
 */
public class CalculatorLogic {

    public static double calculate(double pre, double cur, String op) {
        double result = 0;
        switch (op) {
            case "+":
                result = pre + cur;
                break;
            case "-":
                result = pre - cur;
                break;
            case "×":
                result = pre * cur;
                break;
            case "/":
                if(cur == 0)
                    result = Double.MAX_VALUE;
                else
                    result = pre / cur;
                break;
        }
        return result;
    }
}
