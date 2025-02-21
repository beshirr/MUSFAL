package org.openjfx.workoutplanner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;


public class ReadExerciseJson {
    public static JsonArray readExerciseJson() {
        try {
            FileReader reader = new FileReader("exercises.json");

            Gson gson = new Gson();
            JsonObject data = gson.fromJson(reader, JsonObject.class);

            JsonArray exercises = data.getAsJsonArray("exercises");
            reader.close();
            return exercises;
        }
        catch (IOException e) {
            System.out.println("Error reading exercises.json");;
        }
        return null;
    }
}
