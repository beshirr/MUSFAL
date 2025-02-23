package org.openjfx.workoutplanner;
import com.google.gson.JsonArray;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class ExercisesController {
    public VBox ui_exercisesContainer;

    @FXML
    private void initialize() {
        int n_exercises = 100;
        for (int i = 0; i < n_exercises; i++) {
            Exercise exercise = new Exercise(i);
            Label ui_exerciseName = new Label(exercise.getName());
            ui_exercisesContainer.getChildren().add(ui_exerciseName);

            for (int j = 0; j < exercise.getInstructions().size(); j++) {
                Label ui_instructions = new Label(exercise.getInstructions().get(j).getAsString());
                ui_exercisesContainer.getChildren().add(ui_instructions);
            }
            for (int j = 0; j < exercise.getTargetMuscles().size(); j++) {
                Label ui_targetMuscles = new Label(exercise.getTargetMuscles().get(j).getAsString());
                ui_exercisesContainer.getChildren().add(ui_targetMuscles);
            }
            for (int j = 0; j < exercise.getBodyParts().size(); j++) {
                Label ui_bodyParts = new Label(exercise.getBodyParts().get(j).getAsString());
                ui_exercisesContainer.getChildren().add(ui_bodyParts);
            }
            for (int j = 0; j < exercise.getEquipments().size(); j++) {
                Label ui_equipments = new Label(exercise.getEquipments().get(j).getAsString());
                ui_exercisesContainer.getChildren().add(ui_equipments);
            }
        }
    }
}


class Exercise {
    private final String name;
    private final String gifUrl;
    private final JsonArray instructions;
    private final JsonArray targetMuscles;
    private final JsonArray bodyParts;
    private final JsonArray equipments;

    public Exercise(int i) {
        JsonArray exercises = HandleJson.readExerciseJson();
        assert exercises != null;
        this.name = exercises.get(i).getAsJsonObject().get("name").getAsString();
        this.gifUrl =  exercises.get(i).getAsJsonObject().get("gifUrl").getAsString();
        this.instructions = exercises.get(i).getAsJsonObject().get("instructions").getAsJsonArray();
        this.bodyParts = exercises.get(i).getAsJsonObject().get("bodyParts").getAsJsonArray();
        this.equipments = exercises.get(i).getAsJsonObject().get("equipments").getAsJsonArray();
        this.targetMuscles = exercises.get(i).getAsJsonObject().get("targetMuscles").getAsJsonArray();
    }

    public String getName() {
        return name;
    }
    public String getGifUrl() {
        return gifUrl;
    }
    public JsonArray getInstructions() {
        return instructions;
    }
    public JsonArray getTargetMuscles() {
        return targetMuscles;
    }
    public JsonArray getBodyParts() {
        return bodyParts;
    }
    public JsonArray getEquipments() {
        return equipments;
    }
}