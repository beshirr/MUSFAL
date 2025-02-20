package org.openjfx.workoutplanner;

import javafx.event.ActionEvent;


import java.io.IOException;

public class TopMenuBarController {

    public void handleBMI(ActionEvent actionEvent) throws IOException {
        SceneController.switchScene("bmiCalculator.fxml", "BMI Calculator");
    }

    public void handleBFI(ActionEvent actionEvent) throws IOException {
        SceneController.switchScene("bmiCalculator.fxml",  "Body Fat Index Calculator");
    }

    public void handleCB(ActionEvent actionEvent) throws IOException {
        SceneController.switchScene("bmiCalculator.fxml", "Calories Burned Calculator");
    }

    public void handleDCC(ActionEvent actionEvent) throws IOException {
        SceneController.switchScene("bmiCalculator.fxml", "Daily Calories Calculator");
    }

    public void handleORMC(ActionEvent actionEvent) throws IOException {
        SceneController.switchScene("bmiCalculator.fxml", "One Rep Max Calculator");
    }
}
