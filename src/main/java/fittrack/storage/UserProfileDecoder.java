package fittrack.storage;

import fittrack.UserProfile;
import fittrack.data.Calories;
import fittrack.data.Gender;
import fittrack.data.Height;
import fittrack.data.Weight;
import fittrack.parser.IllegalValueException;
import fittrack.storage.Storage.StorageOperationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @@author J0shuaLeong
public class UserProfileDecoder {
    private static final Pattern HEIGHT_PATTERN = Pattern.compile(
            "Height:\\s+(?<height>\\S+)cm"
    );
    private static final Pattern WEIGHT_PATTERN = Pattern.compile(
            "Weight:\\s+(?<weight>\\S+)kg"
    );
    private static final Pattern GENDER_PATTERN = Pattern.compile(
            "Gender:\\s+(?<gender>\\S+)"
    );
    private static final Pattern CALORIES_PATTERN = Pattern.compile(
            "Daily calorie limit: (?<calLimit>\\S+)kcal"
    );
    private static final StorageOperationException CONTENT_CORRUPTION_EXCEPTION = new StorageOperationException(
            "File containing profile has invalid format. Creating new profile file..."
    );

    /**
     * Decodes {@code encodedUserProfile} into a {@code UserProfile} containing the decoded data.
     *
     * @throws StorageOperationException if the {@code encodedUserProfile} is in an invalid format.
     */
    public static UserProfile decodeUserProfile(List<String> encodedUserProfile, Path profilePath)
            throws StorageOperationException, IOException {
        if (encodedUserProfile.size() < 4) {
            handleCorruptedProfileFile(profilePath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }

        String[] decodedUserProfile = new String[5];
        for (int i = 0; i < encodedUserProfile.size(); i++) {
            decodedUserProfile[i] = encodedUserProfile.get(i);
        }

        final Matcher heightMatcher = HEIGHT_PATTERN.matcher(decodedUserProfile[0]);
        final Matcher weightMatcher = WEIGHT_PATTERN.matcher(decodedUserProfile[1]);
        final Matcher genderMatcher = GENDER_PATTERN.matcher(decodedUserProfile[2]);
        final Matcher caloriesMatcher = CALORIES_PATTERN.matcher(decodedUserProfile[3]);

        if (!heightMatcher.matches() || !weightMatcher.matches()
                || !caloriesMatcher.matches() || !genderMatcher.matches()) {
            handleCorruptedProfileFile(profilePath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }

        final String heightData = heightMatcher.group("height");
        final String weightData = weightMatcher.group("weight");
        final String dailyCalorieLimitData = caloriesMatcher.group("calLimit");
        final String genderData = genderMatcher.group("gender");

        try {
            Height height = Height.parseHeight(heightData);
            Weight weight = Weight.parseWeight(weightData);
            Calories dailyCalorieLimit = Calories.parseCalories(dailyCalorieLimitData);
            Gender gender = Gender.parseGender(genderData);
            return new UserProfile(height, weight, dailyCalorieLimit, gender);
        } catch (IllegalValueException e) {
            handleCorruptedProfileFile(profilePath);
            throw CONTENT_CORRUPTION_EXCEPTION;
        }

    }

    public static void handleCorruptedProfileFile(Path profilePath) throws IOException {
        String newFileContent = "";
        Files.write(profilePath, newFileContent.getBytes());
    }
}
// @@author
