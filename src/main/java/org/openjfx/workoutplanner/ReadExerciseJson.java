package org.openjfx.workoutplanner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;


public class ReadExerciseJson {
    public static JsonArray readExerciseJson() {
        try {
            FileReader reader = new FileReader("D:/FCAI/Level 2/Second semester/SWE/Assignment-1/Task 1/workoutPlanner/src/main/resources/org/openjfx/workoutplanner/exercises.json");

            Gson gson = new Gson();
            JsonObject data = gson.fromJson(reader, JsonObject.class);
            JsonArray exercises = data.get("data").getAsJsonObject().get("exercises").getAsJsonArray();
            reader.close();
            return exercises;
        }
        catch (IOException e) {
            System.out.println("Error reading exercises.json");;
        }
        return null;
    }
}
