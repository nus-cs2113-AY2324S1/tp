package seedu.stocker.storage;


import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.Inventory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Long.parseLong;

/**
 * Represents an object to handle writing to txt files and appending to them.
 */
public class Storage {
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
    public void loadFileContents(String filePath) throws IOException {
        File holder = new File("./drugs.txt");
        if (!holder.exists()) {
            holder.createNewFile();
        }
        File f = new File(filePath);
        Scanner reader = new Scanner(f);


        while(reader.hasNext()){
            String data = reader.nextLine();

            String dataInArray = data;
            String[] spliced = dataInArray.split("Name:|Expiry Date:|Quantity:|,");

            String drugName = spliced[1].trim();
            String expiryDate =spliced[3].trim();
            String quantity = spliced[5].trim();

            Drug drug = new Drug(drugName,expiryDate,parseLong(quantity));


            Inventory.allDrugs.add(drug);




        }
    }


}
