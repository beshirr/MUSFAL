package org.openjfx.workoutplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class bmiCalculator {
    public Label bmiHeaderText;
    public TextField bmiWeight;
    public TextField bmiHeight;
    public Label bmiResult;
    public Button bmiSubmit;
    public Label resultCondition;

    private double height;
    private double weight;

    @FXML
    private void handleBmiWeightInput(ActionEvent actionEvent) {
        this.weight = Double.parseDouble(bmiWeight.getText());
    }

    @FXML
    private void handleBmiHeightInput(ActionEvent actionEvent) {
        this.height = Double.parseDouble(bmiHeight.getText());
    }

    private void condition() {
        if (Double.parseDouble(String.valueOf(bmiResult)) < 18.5) {
            resultCondition.setText("Underweight");
        }
        else if (Double.parseDouble(String.valueOf(bmiResult)) < 25) {
            resultCondition.setText("Normal");
        }
        else if (Double.parseDouble(String.valueOf(bmiResult)) < 30) {
            resultCondition.setText("Slightly Overweight");
        }
        else {
            resultCondition.setText("Overweight");
        }
    }

    public void calculateBmi(ActionEvent actionEvent) {
        bmiResult.setText(String.format("%.2f", (weight / ((height * height) / 100)) * 100));
        condition();
    }
}
