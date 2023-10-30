package seedu.stocker.storage;


import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.exceptions.InvalidDrugFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents an object to handle writing to txt files and appending to them.
 */
public class Storage {

    private Inventory inventory;

    public Storage(Inventory inventory) {
        this.inventory = inventory;
    }
    /**
     * Writes to the first line of a txt file
     * can be used to clear a txt file as well.
     *
     * @param filePath relative path of file to write to
     * @param textToAdd what to write to the file
     * @throws IOException if invalid input is entered
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends given text to next line of txt file.
     *
     * @param filePath relative path of file to write to
     * @param textToAppend what to write to the file
     * @throws IOException if invalid input is given
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter(filePath,true));
        bf.write(textToAppend);
        bf.newLine();
        bf.close();
    }

    /**
     * Loads drugs from txt file into inventory system.
     *
     * @param filePath Relative path to file containing list of drugs to be loaded.
     * @throws IOException if file is not found
     */
    public void loadFileContents(String filePath) throws IOException, InvalidDrugFormatException {
        File holder = new File("./drugs.txt");
        if (!holder.exists()) {
            holder.createNewFile();
        }
        File f = new File(filePath);
        Scanner reader = new Scanner(f);

        Pattern pattern = Pattern.compile(
                "Name: (.*), Expiry date: (.*), Serial Number: (.*), Quantity: (.*)"
        );
        while (reader.hasNextLine()) {
            Matcher matcher = pattern.matcher(reader.nextLine());
            if (matcher.matches() && matcher.groupCount() == 4) {
                String name = matcher.group(1);
                String expiryDate = matcher.group(2);
                String serialNumber = matcher.group(3); // Extract serial number
                Long quantity = Long.parseLong(matcher.group(4));

                Drug drug = new Drug(name, expiryDate);
                inventory.addNewDrug(name, drug, serialNumber, quantity);
            } else {
                throw new InvalidDrugFormatException("");
            }
        }
    }


}
