package org.openjfx.workoutplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import org.openjfx.workoutplanner.SceneController;

public class Musfal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneController.setStage(stage);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exercises.fxml")));
        stage.setTitle("MUSFAL");
        stage.setScene(new Scene(root, 800, 600));
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
        stage.getIcons().add(icon);
        stage.show();
        stage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch();
    }
}