package seedu.stocker.storage;


import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.StockEntry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    public void loadFileContents(String filePath) throws IOException {
        File holder = new File("./drugs.txt");
        if (!holder.exists()) {
            holder.createNewFile();
        }
        File f = new File(filePath);
        Scanner reader = new Scanner(f);

        Pattern pattern = Pattern.compile(
                "Name: (.*), Expiry Date: (.*), Serial Number: (.*), Quantity: (.*), Selling Price: (.*)"
        );
        while (reader.hasNextLine()) {
            Matcher matcher = pattern.matcher(reader.nextLine());
            if (matcher.matches() && matcher.groupCount() == 5) {
                String name = matcher.group(1);
                String expiryDate = matcher.group(2);
                String serialNumber = matcher.group(3); // Extract serial number
                Long quantity = Long.parseLong(matcher.group(4));
                double sellingPrice = Double.parseDouble(matcher.group(5));

                Drug drug = new Drug(name, expiryDate, sellingPrice);
                inventory.addNewDrug(serialNumber, drug, quantity);
            } else {
                System.out.println("Malicious changes were made in drugs.txt, overwriting old drug file,"
                        + " please add new drugs and save again");
                FileWriter fw = new FileWriter("./drugs.txt", false);
                PrintWriter pw = new PrintWriter(fw, false);
                pw.flush();
                pw.close();
                fw.close();
                inventory.clearInventory();
                break;

            }
        }
    }

    public void loadSoldItems(String filePath, SalesList salesList) throws IOException {
        File file = new File("./soldItems.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner reader = new Scanner(file);
        Pattern pattern = Pattern.compile(
                "Name: (.*), Serial Number: (.*), Quantity: (.*), Selling Price: (.*)"
        );

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            Matcher matcher = pattern.matcher(line);

            if (matcher.matches() && matcher.groupCount() == 4) {
                String name = matcher.group(1);
                String serialNumber = matcher.group(2); // Extract serial number
                long quantity = Long.parseLong(matcher.group(3)); // Extract quantity
                double sellingPrice = Double.parseDouble(matcher.group(4)); // Extract selling price

                StockEntry stockEntry = inventory.get(serialNumber);
                Drug drug = stockEntry.getDrug();

                // Add the sold item to the sales list
                salesList.addSoldItem(drug, serialNumber, quantity, sellingPrice, inventory);
            } else {
                System.out.println("Malicious changes were made in soldItems.txt, overwriting old drug file,"
                        + " please add new drugs and save again");
                // Handle any malicious changes
                FileWriter fw = new FileWriter("./soldItems.txt", false);
                PrintWriter pw = new PrintWriter(fw, false);
                pw.flush();
                pw.close();
                fw.close();
                salesList.clearSales();
                break;
            }
        }
    }
}
