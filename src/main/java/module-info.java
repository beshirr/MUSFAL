module org.openjfx.workoutplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    opens org.openjfx.workoutplanner to javafx.fxml;
    exports org.openjfx.workoutplanner;
}