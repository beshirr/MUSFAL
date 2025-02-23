package org.openjfx.workoutplanner;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.openjfx.workoutplanner.ReadExerciseJson;

import java.io.IOException;
import java.util.Objects;


public class Exercises {
    public Label eLabel;

    @FXML
    private void setLabelText() {
        eLabel.setText(Objects.requireNonNullElse(ReadExerciseJson.readExerciseJson().get(0).getAsJsonObject().get("name").getAsString(), "No Exercise Found"));

    }
}
