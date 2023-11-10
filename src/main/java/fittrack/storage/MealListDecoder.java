package fittrack.storage;

import fittrack.MealList;
import fittrack.data.Calories;
import fittrack.data.Meal;
import fittrack.data.Date;
import fittrack.storage.Storage.StorageOperationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @@author J0shuaLeong
public class MealListDecoder {

    private static final Pattern MEAL_PATTERN = Pattern.compile(
            "(?<name>\\S+)\\s*\\|\\s*(?<calories>\\S+)kcal\\s*\\|\\s*(?<date>\\S+)"
    );

    /**
     * Decodes {@code encodedMealList} into a {@code MealList} containing the decoded data.
     *
     * @throws IllegalStorageValueException if any of the fields in any encoded person string is invalid.
     * @throws StorageOperationException if the {@code encodedMealList} is in an invalid format.
     */
    public static MealList decodeMealList(List<String> encodedMealList, Path mealListPath)
            throws IllegalStorageValueException, StorageOperationException, IOException {
        MealList mealList = new MealList();
        for (String encodedMeal : encodedMealList) {
            mealList.addToList(decodeMealsFromString(encodedMeal, mealListPath));
        }
        return mealList;
    }

    /**
     * Decodes {@code encodedMeal} into a {@code Meal}.
     *
     * @throws StorageOperationException if {@code encodedPerson} is in an invalid format.
     */
    public static Meal decodeMealsFromString(String encodedMeal, Path mealListPath)
            throws StorageOperationException, IOException {
        final Matcher matcher = MEAL_PATTERN.matcher(encodedMeal);
        if (!matcher.matches()) {
            handleCorruptedFile(mealListPath);
            throw new StorageOperationException("File containing meals has invalid format. " +
                    "Creating new meal list file...");
        }

        final String name = matcher.group("name");
        final String calories = matcher.group("calories");
        final String date = matcher.group("date");

        double caloriesInDouble = Double.parseDouble(calories);

        return new Meal(name, new Calories(caloriesInDouble), new Date(date));
    }

    public static void handleCorruptedFile(Path filePath) throws IOException {
        String newFileContent = "";
        Files.write(filePath, newFileContent.getBytes());
    }
}
// @@author
