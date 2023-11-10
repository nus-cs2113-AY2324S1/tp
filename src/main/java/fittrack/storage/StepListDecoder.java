package fittrack.storage;

import fittrack.StepList;
import fittrack.data.Date;
import fittrack.data.Step;
import fittrack.parser.IllegalValueException;
import fittrack.storage.Storage.StorageOperationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepListDecoder {

    private static final Pattern STEP_PATTERN = Pattern.compile(
            "(?<steps>\\d+)\\s*\\|\\s*(?<date>\\d{4}-\\d{2}-\\d{2})"
    );

    /**
     * Decodes {@code encodedStepList} into a {@code StepList} containing the decoded data.
     *
     * @throws IllegalValueException if any of the fields in any encoded person string is invalid.
     * @throws StorageOperationException if the {@code encodedStepList} is in an invalid format.
     */
    public static StepList decodeStepList(List<String> encodedStepList, Path stepListPath)
            throws IllegalValueException, StorageOperationException, IOException {
        StepList mealList = new StepList();
        for (String encodedMeal : encodedStepList) {
            mealList.addToList(decodeStepsFromString(encodedMeal, stepListPath));
        }
        return mealList;
    }

    /**
     * Decodes {@code encodedMeal} into a {@code Meal}.
     *
     * @throws StorageOperationException if {@code encodedPerson} is in an invalid format.
     */
    public static Step decodeStepsFromString(String encodedMeal, Path stepListPath)
            throws StorageOperationException, IOException {
        final Matcher matcher = STEP_PATTERN.matcher(encodedMeal);
        if (!matcher.matches()) {
            handleCorruptedFile(stepListPath);
            throw new StorageOperationException("File containing steps has invalid format. " +
                    "Creating new step list file.");
        }

        final String steps = matcher.group("steps");
        final String date = matcher.group("date");

        int caloriesInInt = Integer.parseInt(steps);

        return new Step(caloriesInInt, new Date(date));
    }

    public static void handleCorruptedFile(Path filePath) throws IOException {
        String newFileContent = "";
        Files.write(filePath, newFileContent.getBytes());
    }
}
