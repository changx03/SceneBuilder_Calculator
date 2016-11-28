package myCalculator;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label output;

    private double previous = 0;
    private double current = 0;
    private String operator;

    @FXML
    protected void processNumpad(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if(output.getText().equals("0"))
            output.setText(value);
        else
            output.setText(output.getText() + value);
        current = Double.parseDouble(output.getText());
    }

    @FXML
    protected void processOperator(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();

        switch (value) {
            case "AC":
                previous = current = 0;
                break;
            case "±":
                current = 0 - current;
                break;
            case "%":   // percent button 90 + 10% = 90 + 9 = 99
                current = previous * current / 100.0;
                break;
            case "=":
                // do the calculation
                previous = CalculatorLogic.calculate(previous, current, operator);
                if(previous == Double.MAX_VALUE) {
                    previous = current = 0;
                }
                break;
            case ".":
                if(!output.getText().contains("."))
                    output.setText(output.getText() + value);
                return; // special case escape from the method
            default:
                previous = current;
                current = 0;
                operator = value;
                break;
        }

        double printVal = (!value.equals("=")) ? current : previous;
        if(printVal % 1 == 0) {   // Current value is an integer. No decimal value
            long longNum = Math.round(printVal);
            output.setText(String.valueOf(longNum));
        }
        else
            output.setText(String.valueOf(printVal));
    }
}
