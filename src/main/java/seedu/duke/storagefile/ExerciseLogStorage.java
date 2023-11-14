package seedu.duke.storagefile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import seedu.duke.exerciselog.Log;

public class ExerciseLogStorage extends Storage {


    public ExerciseLogStorage(String dirName, String textFileName) {
        super(dirName, textFileName);
    }

    public static ExerciseLogStorage initializeStorage(String dirName, String textFilePath) {
        return new ExerciseLogStorage(dirName, textFilePath);
    }

    public void checkForLogTextFile(Log exerciseLog) throws IOException {
        if (dir.exists() && textFile.exists()) {
            Scanner s = new Scanner(textFile);
            while (s.hasNextLine()) {
                textToExercise(s.nextLine().split(","), exerciseLog);
            }
            s.close();
        }
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!textFile.exists()) {
            textFile.createNewFile();
        }
        writeFile = new FileWriter(textFile.toPath().toString(), true);
    }

    public static void textToExercise(String[] exerciseDetails, Log exerciseLog) {
        int month = Integer.parseInt(exerciseDetails[0]);
        int day = Integer.parseInt(exerciseDetails[1]);
        String exerciseName = String.join(" ", exerciseDetails[2].split("_"));
        int caloriesBurned = Integer.parseInt(exerciseDetails[3]);
        exerciseLog.addExercise(month, day, exerciseName, caloriesBurned);
    }

    public void writeToStorage(int month, int day, String[] exerciseName, int caloriesBurned)
            throws IOException {
        String writeToFile = "";
        writeToFile += month + ",";
        writeToFile += day + ",";
        writeToFile += String.join("_", exerciseName);
        writeToFile += "," + caloriesBurned;
        writeFile.write(writeToFile + "\n");
        writeFile.flush();
    }

    public void deleteFromStorage(int month, int day, String[] exerciseName, int caloriesBurned)
            throws IOException {
        Scanner readFile = new Scanner(textFile);
        File tempFile = new File("./data/temp.txt");
        FileWriter tempWriter = new FileWriter(tempFile.toPath().toString());

        String removeLine = "";
        removeLine += month + ",";
        removeLine += day + ",";
        removeLine += String.join("_", exerciseName);
        removeLine += "," + caloriesBurned;

        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if (!line.equals(removeLine)) {
                tempWriter.write(line + "\n");
            }
        }
        readFile.close();
        tempWriter.close();
        textFile.delete();
        tempFile.renameTo(textFile);
        textFile = new File("./data/ExerciseLog.txt");
        writeFile = new FileWriter(textFile.toPath().toString(), true);
    }

    public void updateInStorage(int month, int day, String[] oldExerciseName, int oldCaloriesBurned,
                                String[] newExerciseName, int newCaloriesBurned) throws IOException {
        Scanner readFile = new Scanner(textFile);
        File tempFile = new File("./data/temp.txt");
        FileWriter tempWriter = new FileWriter(tempFile.toPath().toString());

        String oldLine = "";
        oldLine += month + ",";
        oldLine += day + ",";
        oldLine += String.join("_", oldExerciseName);
        oldLine += "," + oldCaloriesBurned;

        String newLine = "";
        newLine += month + ",";
        newLine += day + ",";
        newLine += String.join("_", newExerciseName);
        newLine += "," + newCaloriesBurned;

        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if (!line.equals(oldLine)) {
                tempWriter.write(line + "\n");
            } else {
                tempWriter.write(newLine + "\n");
            }
        }
        readFile.close();
        tempWriter.close();
        textFile.delete();
        tempFile.renameTo(textFile);
        textFile = new File("./data/ExerciseLog.txt");
        writeFile = new FileWriter(textFile.toPath().toString(), true);
    }
}
