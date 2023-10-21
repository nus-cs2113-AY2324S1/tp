package fittrack.storage;

import fittrack.MealList;
import fittrack.UserProfile;
import fittrack.WorkoutList;
import fittrack.data.Meal;
import fittrack.data.Workout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//TODO pass the profile data to be stored in fittrack.txt
public class Storage {

    private static final String FILE_DIRECTORY = "data";
    private final String profileFilePath = "./data/Profile.txt";
    private final String mealListFilePath = "./data/mealList.txt";
    private final String workoutListFilePath = "./data/workoutList.txt";
    private final File profileFile;
    private final File mealFile;
    private final File workoutFile;


    /**
     * Constructs storage. Creates new file fittrack.txt
     * in a directory called data if none exist.
     */
    public Storage() {
        this.profileFile = new File(profileFilePath);
        this.mealFile = new File(mealListFilePath);
        this.workoutFile = new File(workoutListFilePath);

        assert profileFile != null;
        assert mealFile != null;
        assert workoutFile != null;

        try {
            File f = new File(FILE_DIRECTORY);

            if (f.mkdir()) {
                System.out.println("Directory created: " + f.getName());
            } else {
                System.out.println("Directory already exists.");
            }

            if (!this.profileFile.exists()) {
                profileFile.createNewFile();
            }
            if (!this.mealFile.exists()) {
                mealFile.createNewFile();
            }
            if (!this.workoutFile.exists()) {
                workoutFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create directory and file.");
        }
    }

    /**
     * Saves user profile data into storage
     *
     * @throws IOException error
     */
    public void saveProfile(UserProfile userProfile)
            throws IOException {
        //TODO write data to file
        FileWriter file = new FileWriter(profileFilePath);
        file.write(userProfile.toString() + "\n");
        file.close();
    }

    /**
     * Saves meal list into storage
     *
     * @throws IOException error
     */
    public void saveMeals(MealList mealList) throws IOException {
        ArrayList<Meal> mealArr = mealList.getMealList();
        FileWriter file = new FileWriter(mealListFilePath);
        for (Meal m : mealArr) {
            file.write(m.formatToFile() + "\n");
        }
        file.close();
    }

    /**
     * Saves workout list into storage
     *
     * @throws IOException error
     */
    public void saveWorkouts(WorkoutList workoutList) throws IOException {
        ArrayList<Workout> workoutArr = workoutList.getWorkoutList();
        FileWriter file = new FileWriter(workoutListFilePath);
        for (Workout w : workoutArr) {
            file.write(w.formatToFile() + "\n");
        }
        file.close();
    }
}
