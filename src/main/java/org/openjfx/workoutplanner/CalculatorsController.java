package org.openjfx.workoutplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


class Calculators {
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

     /**
      * Daily Calorie Calculator*/
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


    /**
     * One rep Maximum weight*/
    public static double calculateORM(double weightLifted, int reps) {
        return weightLifted * (1 + ((double) reps / 30));
    }

    enum ActivityLevel {
        LIGHT,
        MODERATE,
        VERY,
        EXTREME,
    }
}


public class CalculatorsController {
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
    @FXML
    private void ui_calculateBFP(ActionEvent actionEvent) {
        try {
            double[] input = {Double.parseDouble(inputHeight.getText()), Double.parseDouble(inputWeight.getText()),
                    Double.parseDouble(inputWaistCirc.getText()),  Double.parseDouble(inputHipCirc.getText()),
            Double.parseDouble(inputNeckCirc.getText())};
            validateInput(input);

            double result = Calculators.calculateBFP(userGender, Double.parseDouble(inputWeight.getText()),
                    Double.parseDouble(inputHeight.getText()), Double.parseDouble(inputWaistCirc.getText()),
                    Double.parseDouble(inputNeckCirc.getText()), Double.parseDouble(inputHipCirc.getText()));
            ui_resultLabel.setText(String.format("%.2f",result));
        } catch (Exception e) {
            ui_resultLabel.setText("INVALID INPUT");
        }
    }
    @FXML
    private void ui_calculateBMI(ActionEvent actionEvent) {
        try {
            double[] input = {Double.parseDouble(inputHeight.getText()), Double.parseDouble(inputWeight.getText())};
            validateInput(input);
            double result = Calculators.calculateBMI(Double.parseDouble(inputWeight.getText()),
                    Double.parseDouble(inputHeight.getText()));
            ui_resultLabel.setText(String.format("%.2f",result));

            String classification = Calculators.bmiClassification(result);
            ui_resultClassification.setText(classification);
        } catch (Exception e) {
            ui_resultLabel.setText("INVALID INPUT");
        }
    }
    @FXML
    private void ui_calculateBMR(ActionEvent actionEvent) {
        try {
            double[] input = {Double.parseDouble(inputHeight.getText()), Double.parseDouble(inputWeight.getText()),
                    Double.parseDouble(inputAge.getText())};
            validateInput(input);
            double activityLevel = Calculators.evaluateActivityLevel(Calculators.ActivityLevel.valueOf(userActivityLevel));
            double result = Calculators.calculateBMR(userGender, Integer.parseInt(inputAge.getText()), Double.parseDouble(inputWeight.getText()),
                    Double.parseDouble(inputHeight.getText()), activityLevel);
            ui_resultLabel.setText(String.format("%.2f", result));
        } catch (Exception e) {
            ui_resultLabel.setText("INVALID INPUT");
        }
    }
    @FXML
    private void ui_calculateORM(ActionEvent actionEvent) {
        try {
            double[] input = {Double.parseDouble(inputWeightLifted.getText()), Integer.parseInt(inputReps.getText())};
            validateInput(input);
            double result = Calculators.calculateORM(Double.parseDouble(inputWeightLifted.getText()), Integer.parseInt(inputReps.getText()));
            ui_resultLabel.setText(String.format("%.2f", result));
        }
        catch (Exception e) {
            ui_resultLabel.setText("INVALID INPUT");
        }
    }

    private void validateInput(double[] input) {
        for (double value : input) {
            if (value <= 0) throw new RuntimeException();
        }
    }

    @FXML String identifier;
    @FXML private TextField inputAge;
    @FXML private TextField inputWeightLifted;
    @FXML private TextField inputReps;
    @FXML private TextField inputWeight;
    @FXML private TextField inputHeight;
    @FXML private TextField inputHipCirc;
    @FXML private TextField inputWaistCirc;
    @FXML private TextField inputNeckCirc;
    @FXML private Button calculateButton;
    @FXML private Label ui_resultLabel;
    @FXML private Label ui_resultClassification;
    @FXML private ComboBox activityComboBox;
    @FXML private ToggleGroup genderGroup;

    private String userGender;
    private String userActivityLevel;
}
