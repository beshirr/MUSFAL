package org.openjfx.workoutplanner;
import com.google.gson.JsonArray;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.openjfx.workoutplanner.ReadExerciseJson;

import java.io.IOException;
import java.util.Objects;


public class Exercises {
    public VBox exercisesContainer;

    @FXML
    private void initialize() {
        JsonArray exercises = ReadExerciseJson.readExerciseJson();
        for (int i = 0; i < exercises.size(); i++) {
            Label label = new Label("exercise " + (i + 1));
            label.setText(i+1 + "- " + exercises.get(i).getAsJsonObject().get("name").getAsString());
            exercisesContainer.getChildren().add(label);

            for (int j = 0; j < exercises.get(i).getAsJsonObject().get("instructions").getAsJsonArray().size(); j++) {
                Label instructions = new Label("Instructions");
                instructions.setText(exercises.get(i).getAsJsonObject().get("instructions").getAsJsonArray().get(j).getAsString());
                exercisesContainer.getChildren().add(instructions);
            }
            for (int j = 0; j < exercises.get(i).getAsJsonObject().get("targetMuscles").getAsJsonArray().size(); j++) {
                Label target = new Label("Target");
                target.setText(exercises.get(i).getAsJsonObject().get("targetMuscles").getAsJsonArray().get(j).getAsString());
                exercisesContainer.getChildren().add(target);
            }
        }
    }
}
