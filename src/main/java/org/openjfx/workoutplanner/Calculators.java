package org.openjfx.workoutplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;


public class Calculators {
    /*
    * Ui elements */
    public Label bmiHeaderText;
    public Label result;
    public Label resultClassification;
    public TextField inputWeight;
    public TextField inputHeight;
    public Button bmiSubmit;
    public ToggleGroup genderGroup;
    public TextField inputWaistCirc;
    public TextField inputNeckCirc;
    public TextField inputHipCirc;
    public Button bfpSubmit;

    /*
    * backend logic variables */
    private double height;
    private double weight;
    private double bmi;
    private String userGender;
    private double waist;
    private double neck;
    private double hip;
    private double fats;

//
//    @FXML
//    private void initialize() {
//        genderGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                RadioButton selectedRadioButton = (RadioButton) newValue;
//                userGender = selectedRadioButton.getText();
//            }
//            userGender = maleRadio.isSelected() ? "Male" : "Female";
//        });
//    }

    @FXML
    private void handleWeightInput(ActionEvent actionEvent) {
        this.weight = Double.parseDouble(inputWeight.getText());
    }

    @FXML
    private void handleHeightInput(ActionEvent actionEvent) {
        this.height = Double.parseDouble(inputHeight.getText());
    }

    @FXML
    private void handleWaistInput(ActionEvent actionEvent) {
        this.waist = Double.parseDouble(inputWaistCirc.getText());
    }

    @FXML
    private void handleHipInput(ActionEvent actionEvent) {
        this.hip = Double.parseDouble(inputHipCirc.getText());
    }

    @FXML
    private void handleNeckInput(ActionEvent actionEvent) {
        this.neck = Double.parseDouble(inputNeckCirc.getText());
    }

    public void calculateBmi(ActionEvent actionEvent) {
        bmi = (weight / ((height * height) / 100)) * 100;
        result.setText(String.format("%.2f", bmi));
        bmiClassification();
    }

    public void calculateBFP(ActionEvent actionEvent) {
        if (Objects.equals(userGender, "Male")) {
            fats = 86.01 * Math.log10(waist - neck) - 70.041 * Math.log10(height) + 36.76;
        }
        else {
            fats = 163.205 * Math.log10(waist + hip - neck) - 97.684 * Math.log10(height) + 78.387;
        }

        result.setText(String.format("%.2f", fats));
        bfpClassification();
    }

    private void bmiClassification() {
        if (bmi < 18.5) {
            resultClassification.setText("Underweight");
        }
        else if (bmi < 25) {
            resultClassification.setText("Normal");
        }
        else if (bmi < 30) {
            resultClassification.setText("Slightly Overweight");
        }
        else {
            resultClassification.setText("Overweight");
        }
    }

    private void bfpClassification() {
        result.setText("Classifications");
    }
}
