package fittrack;

/**
 * Represents the main part of FitTrack.
 */
public class FitTrack {
    private final UserProfile userProfile;
    private final MealList meals;
    private final WorkList works;
    private final Ui ui;
    private boolean isRunning;

    /**
     * Main entry-point for the FitTrack application.
     */
    public static void main(String[] args) {
        new FitTrack().run();
    }

    private FitTrack() {
        ui = new Ui();

        userProfile = new UserProfile();
        meals = new MealList();
        works = new WorkList();

        isRunning = true;
    }

    private void run() {
        while (isRunning) {
            isRunning = false;
        }
    }
}
