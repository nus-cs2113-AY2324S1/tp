package seedu.duke.storagefile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import seedu.duke.Duke;
import seedu.duke.data.Date;
import seedu.duke.data.DateTime;
import seedu.duke.meal.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A util used for managing the data of the program, using the io stream to
 * save and read the data at a local address.
 */
public class DataManager {
    private static String absolutePath;
    private static String home = System.getProperty("user.home");

    /**
     * Set the relative path to make clear which exact address the data file is
     * going to save and read.Using relative path may make the program easier to
     * modify when you need to change the absolute saving path.
     * 
     * @param relativePath The address containing the name of the data file under
     *                     the data folder, ignoring the address of the data folder.
     * @throws IOException
     */
    public static void setRelativePath(String relativePath) throws IOException {
        String dataFolderPath = home + "\\AppData\\LocalLow\\FITNUS\\";
        // Logger.customPrint(dataFolderPath);
        Files.createDirectories(Paths.get(dataFolderPath));

        absolutePath = dataFolderPath + relativePath;
        // Logger.customPrint(absolutePath);
    }

    /**
     * Read the data from the path that has been previously set.
     * 
     * @throws IOException
     */
    public static String readData() throws IOException {
        File file = new File(absolutePath);

        // No existing file
        if (file.createNewFile()) {
            return "";
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String result = "", line;
        while ((line = reader.readLine()) != null) {
            result += line + "\n";
        }
        reader.close();
        return result.trim();
    }

    /**
     * Save the data to a file at the path that has been previously set.
     * 
     * @param content The serialized data json that is going to be saved.
     * @throws IOException
     */
    public static void saveData(String content) throws Exception {
        File file = new File(absolutePath);
        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
        Duke.ui.showToUser("Your data has been saved at the path:\n  " + absolutePath);
    }

    /**
     * Convert a json String to a CustomType that is set by the user.
     * 
     * @param content A valid json String indicates a CustomType instance.
     */
    public static <CustomType> CustomType convertFromJson(String json) {
        Type type = new TypeToken<CustomType>() {
        }.getType();

        Gson gson = new Gson();

        return gson.fromJson(json, type);
    }

    /**
     * Convert a json String to an ArrayList<Meal>.
     * 
     * @param content A valid json String indicates an ArrayList<Meal>.
     */
    public static ArrayList<Meal> convertFromJsonToMealList(String json) {
        Type type = new TypeToken<ArrayList<Meal>>() {
        }.getType();

        Gson gson = new GsonBuilder()
                // .registerTypeAdapter(Meal.class, new MealAdapter())
                // .registerTypeAdapter(DateTime.class, new DateTimeAdapter())
                .registerTypeAdapter(Date.class, new DateAdapter())
                .create();

        return gson.fromJson(json, type);
    }

    /**
     * Serialize any object to json.
     * 
     * @param object Any variable that you want to serialize.
     */
    public static String convertToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * Used for deserializing Meal with a custom rule.
     */
    private static class MealAdapter implements JsonDeserializer<Meal> {
        @Override
        public Meal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            return context.deserialize(jsonObject, Meal.class);
        }
    }

    /**
     * Used for deserializing DateTime with a custom rule.
     */
    private static class DateTimeAdapter implements JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            JsonObject jsonObject = json.getAsJsonObject();

            String rawData = jsonObject.get("rawData").getAsString();
            try {
                return new DateTime(rawData);
            } catch (Exception exception) {
                Duke.ui.showToUser(exception.toString());
            }
            return null;
        }
    }

    /**
     * Used for deserializing Date with a custom rule.
     */
    private static class DateAdapter implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            JsonObject jsonObject = json.getAsJsonObject();

            String rawData = jsonObject.get("standardString").getAsString();
            try {
                return new Date(rawData, false);
            } catch (Exception exception) {
                Duke.ui.showToUser(exception.toString());
            }
            return null;
        }
    }
}