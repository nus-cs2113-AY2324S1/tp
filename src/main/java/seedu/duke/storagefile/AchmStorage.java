package seedu.duke.storagefile;

import seedu.duke.Duke;
import seedu.duke.goal.GoalList;
import seedu.duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AchmStorage extends GoalStorage {

    public AchmStorage(String dirName, String textFileName) {
        super(dirName, textFileName);
    }

    public static AchmStorage initializeGoalStorage(String dirName, String textFilePath) {
        return new AchmStorage(dirName, textFilePath);
    }

    /**
     * Note that this function exhibits the same structure as the method in parent class
     * except that the nested method textToGoalObject is overridden
     * @throws IOException if failed to write into file
     */
    @Override
    public void restoreGoalRecord() throws IOException {
        if (dir.exists() && textFile.exists()) {
            try {
                Scanner s = new Scanner(textFile);
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    if (!line.trim().isEmpty()) {
                        textToGoalObject(line);
                    }
                }
                s.close();
            } catch (FileNotFoundException fnf) {
                System.out.println("Saved file cannot be found! FItNus will start with empty goal records.");
                System.out.println(TextUi.buildingFileMsg());
                Duke.goalList = new GoalList();
            } catch (Exception e) {
                System.out.println("Saved goal file is corrupted! FitNus will start with empty goal records.");
                System.out.println(TextUi.buildingFileMsg());
                Duke.goalList = new GoalList(); //start with an empty goal list
            }
        }

        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!textFile.exists()) {
            textFile.createNewFile();
            Duke.goalList = new GoalList(); //start with an empty goal list
        }

    }

    /**
     * This method convert the saved text command into the goal object
     * @param goalRecord refers to saved goal record
     * @throws Exception if failed to access file
     */
    @Override
    protected void textToGoalObject(String goalRecord) throws Exception {
        String restoredCommand = restoreOrigionalCommand(goalRecord);
        GoalList.addGoal(restoredCommand, Duke.achievedGoals, Duke.achmStorage);
    }
}
