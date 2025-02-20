module org.openjfx.workoutplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.openjfx.workoutplanner to javafx.fxml;
    exports org.openjfx.workoutplanner;
}