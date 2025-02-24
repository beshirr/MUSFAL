package org.openjfx.workoutplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class CalculatorsController {
    public TextField inputAge;
    public ComboBox activityComboBox;
    @FXML
    String identifier;
    public TextField inputWeight;
    public TextField inputHeight;
    public TextField inputHipCirc;
    public TextField inputWaistCirc;
    public TextField inputNeckCirc;
    public Button bmi_calculateButton;
    public Button bfp_calculateButton;
    public Label ui_resultLabel;
    public Label ui_resultClassification;
    public ToggleGroup genderGroup;
    String userGender;
    String userActivityLevel;

    @FXML
    private void initialize() {
        if ("bfp".equals(identifier) || "bmr".equals(identifier)) {
            genderGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    RadioButton selectedRadioButton = (RadioButton) newValue;
                    userGender = selectedRadioButton.getText();
                }
            });
        }
        if ("bmr".equals(identifier)) {
            activityComboBox.getItems().addAll("LIGHT", "MODERATE", "VERY", "EXTREME");
            activityComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    userActivityLevel = newValue.toString();
                }
            });
        }
    }

    public void ui_calculateBFP(ActionEvent actionEvent) {
        double result = Calculators.calculateBFP(userGender, Double.parseDouble(inputWeight.getText()),
                Double.parseDouble(inputHeight.getText()), Double.parseDouble(inputWaistCirc.getText()),
                Double.parseDouble(inputNeckCirc.getText()), Double.parseDouble(inputHipCirc.getText()));
        ui_resultLabel.setText(String.format("%.2f",result));
    }

    public void ui_calculateBMI(ActionEvent actionEvent) {
        double result = Calculators.calculateBMI(Double.parseDouble(inputWeight.getText()),
                Double.parseDouble(inputHeight.getText()));
        ui_resultLabel.setText(String.format("%.2f",result));

        String classification = Calculators.bmiClassification(result);
        ui_resultClassification.setText(classification);
    }

    public void ui_calculateBMR(ActionEvent actionEvent) {
        double activityLevel = Calculators.evaluateActivityLevel(Calculators.ActivityLevel.valueOf(userActivityLevel));
        double result = Calculators.calculateBMR(userGender, Integer.parseInt(inputAge.getText()), Double.parseDouble(inputWeight.getText()),
                Double.parseDouble(inputHeight.getText()), activityLevel);
        ui_resultLabel.setText(String.format("%.2f", result));
    }
}

class Calculators {
    enum ActivityLevel {
        LIGHT,
        MODERATE,
        VERY,
        EXTREME,
    }
    /**
     * BMI-Calculations
     */
    public static double calculateBMI(double weight, double height) {
        return (weight / ((height * height) / 100)) * 100;
    }

    public static String bmiClassification(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        }
        else if (bmi < 25) {
            return "Normal";
        }
        else if (bmi < 30) {
            return "Slightly Overweight";
        }
        else {
            return "Overweight";
        }
    }

    /**
     * BFP-Calculations
     * */
    public static double calculateBFP(String gender, double weight, double height, double waist, double neck, double hip) {
        if (gender.equals("Male")) {
            return 86.01 * Math.log10(waist - neck) - 70.041 * Math.log10(height) + 36.76;
        }
        return 163.205 * Math.log10(waist + hip - neck) - 97.684 * Math.log10(height) + 78.387;
    }

    public static double evaluateActivityLevel(ActivityLevel activityLevel) {
        return switch (activityLevel) {
            case LIGHT -> 1.375;
            case MODERATE -> 1.55;
            case VERY -> 1.725;
            case EXTREME -> 1.9;
        };
    }

    public static double calculateBMR(String gender, int age, double weight, double height, double activityLevel) {
        if (gender.equals("Male")) {
            return (10 * weight + 6.25 * height - 5 * age + 5) * activityLevel;
        }
        return (10 * weight + 6.25 * height - 5 * age - 161) * activityLevel;
    }
}
