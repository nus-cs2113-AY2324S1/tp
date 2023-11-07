package fittrack.storage;

import fittrack.StepList;
import fittrack.data.Date;
import fittrack.data.Step;
import fittrack.parser.IllegalValueException;
import fittrack.storage.Storage.StorageOperationException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepListDecoder {

    private static final Pattern STEP_PATTERN = Pattern.compile(
            "(?<steps>\\S+)kcal\\s*\\|\\s*(?<date>\\S+)"
    );

    /**
     * Decodes {@code encodedStepList} into a {@code StepList} containing the decoded data.
     *
     * @throws IllegalValueException if any of the fields in any encoded person string is invalid.
     * @throws StorageOperationException if the {@code encodedStepList} is in an invalid format.
     */
    public static StepList decodeStepList(List<String> encodedStepList)
            throws IllegalValueException, StorageOperationException {
        StepList mealList = new StepList();
        for (String encodedMeal : encodedStepList) {
            mealList.addToList(decodeStepsFromString(encodedMeal));
        }
        return mealList;
    }

    /**
     * Decodes {@code encodedMeal} into a {@code Meal}.
     *
     * @throws StorageOperationException if {@code encodedPerson} is in an invalid format.
     */
    public static Step decodeStepsFromString(String encodedMeal)
            throws StorageOperationException {
        final Matcher matcher = STEP_PATTERN.matcher(encodedMeal);
        if (!matcher.matches()) {
            throw new StorageOperationException("File containing meals has invalid format. " +
                    "Please delete the file and run the program again");
        }

        final String steps = matcher.group("steps");
        final String date = matcher.group("date");

        int caloriesInInt = Integer.parseInt(steps);

        return new Step(caloriesInInt, new Date(date));
    }
}
