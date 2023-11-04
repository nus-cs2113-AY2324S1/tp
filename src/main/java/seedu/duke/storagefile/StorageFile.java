package seedu.duke.storagefile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import seedu.duke.exerciselog.Log;

public class StorageFile {
    private static File dir;
    private static File textFile;
    private static FileWriter writeFile;

    public StorageFile(String dirName, String textFileName) {
        dir = new File(dirName);
        textFile = new File(textFileName);
    }

    public static StorageFile initializeStorage(String dirName, String textFilePath) {
        return new StorageFile(dirName, textFilePath);
    }

    public void checkForTextFile(Log exerciseLog) throws IOException {
        if (dir.exists() && textFile.exists()) {
            try {
                Scanner s = new Scanner(textFile);
                while (s.hasNextLine()) {
                    textToExercise(s.nextLine().split(","), exerciseLog);
                }
                s.close();
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
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

    public void writeExerciseToFile(int month, int day, String[] exerciseName, int caloriesBurned)
            throws IOException {
        String writeToFile = "";
        writeToFile += Integer.toString(month) + ",";
        writeToFile += Integer.toString(day) + ",";
        writeToFile += String.join("_", exerciseName);
        writeToFile += "," + Integer.toString(caloriesBurned);
        writeFile.write(writeToFile + "\n");
        writeFile.flush();
    }

    public void removeExerciseFromFile(int month, int day, String[] exerciseName, int caloriesBurned)
            throws IOException {
        Scanner readFile = new Scanner(textFile);
        File tempFile = new File("./data/temp.txt");
        FileWriter tempWriter = new FileWriter(tempFile.toPath().toString());

        String removeLine = "";
        removeLine += Integer.toString(month) + ",";
        removeLine += Integer.toString(day) + ",";
        removeLine += String.join("_", exerciseName);
        removeLine += "," + Integer.toString(caloriesBurned);

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

    public void updateExerciseInFile(int month, int day, String[] oldExerciseName, int oldCaloriesBurned,
                                     String[] newExerciseName, int newCaloriesBurned) throws IOException {
        Scanner readFile = new Scanner(textFile);
        File tempFile = new File("./data/temp.txt");
        FileWriter tempWriter = new FileWriter(tempFile.toPath().toString());

        String oldLine = "";
        oldLine += Integer.toString(month) + ",";
        oldLine += Integer.toString(day) + ",";
        oldLine += String.join("_", oldExerciseName);
        oldLine += "," + Integer.toString(oldCaloriesBurned);

        String newLine = "";
        newLine += Integer.toString(month) + ",";
        newLine += Integer.toString(day) + ",";
        newLine += String.join("_", newExerciseName);
        newLine += "," + Integer.toString(newCaloriesBurned);

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
