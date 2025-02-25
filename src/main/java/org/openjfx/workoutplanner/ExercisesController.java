package org.openjfx.workoutplanner;

import com.google.gson.JsonArray;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


class Exercise {
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


    private final String name;
    private final String gifUrl;
    private final JsonArray instructions;
    private final JsonArray targetMuscles;
    private final JsonArray bodyParts;
    private final JsonArray equipments;
}



public class ExercisesController {
    @FXML
    private void initialize() {
        try {
            JsonArray exercises = HandleJson.readExerciseJson();
            if (exercises != null) {
                int n_exercises = 100;
                for (int i = 0; i < n_exercises; i++) {
                    Exercise exercise = new Exercise(i);
                    ui_exercisesContainer.getChildren().add(createExerciseCard(exercise));
                }
            }
        } catch (Exception e) {
            Label errorLabel = new Label("Error loading exercises: " + e.getMessage());
            ui_exercisesContainer.getChildren().add(errorLabel);
        }
    }

    private VBox createExerciseCard(Exercise exercise) {
        VBox card = new VBox(10);
        card.getStyleClass().add("exercise-card");

        HBox header = new HBox(10);
        header.getStyleClass().add("exercise-header");

        Label nameLabel = new Label(exercise.getName().toUpperCase());
        nameLabel.getStyleClass().add("exercise-title");

        header.getChildren().addAll(nameLabel);

        VBox details = new VBox(5);
        details.getStyleClass().add("exercise-details");

        HBox musclesBox = new HBox(20);
        VBox targetMusclesBox = new VBox(5);
        Label targetMusclesTitle = new Label("Target Muscles:");
        targetMusclesTitle.getStyleClass().add("section-title");
        targetMusclesBox.getChildren().add(targetMusclesTitle);
        exercise.getTargetMuscles().forEach(muscle ->
                targetMusclesBox.getChildren().add(new Label("• " + muscle.getAsString().toUpperCase())));

        VBox equipmentBox = new VBox(5);
        Label equipmentTitle = new Label("Equipment:");
        equipmentTitle.getStyleClass().add("section-title");
        equipmentBox.getChildren().add(equipmentTitle);
        exercise.getEquipments().forEach(equipment ->
                equipmentBox.getChildren().add(new Label("• " + equipment.getAsString().toUpperCase())));

        musclesBox.getChildren().addAll(targetMusclesBox, equipmentBox);

        TitledPane instructionsPane = new TitledPane();
        instructionsPane.setText("Instructions");
        VBox instructionsBox = new VBox(5);
        exercise.getInstructions().forEach(instruction ->
                instructionsBox.getChildren().add(new Label(instruction.getAsString().toUpperCase())));
        instructionsPane.setContent(instructionsBox);
        instructionsPane.setExpanded(false);

        details.getChildren().addAll(musclesBox, instructionsPane);

        card.getChildren().addAll(header, details);
        return card;
    }

    @FXML
    private VBox ui_exercisesContainer;
}
