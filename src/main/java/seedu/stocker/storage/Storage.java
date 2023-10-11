package seedu.stocker.storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend,String textToAppend2) throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter(filePath));
        bf.write(textToAppend);
        bf.write(textToAppend2);
        bf.newLine();
        bf.close();
    }


}
