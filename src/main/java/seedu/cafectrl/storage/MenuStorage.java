package seedu.cafectrl.storage;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MenuStorage extends Storage {
    private ArrayList<Dish> dishArrayList;
    private Ui ui;

    public MenuStorage(String filePath, Ui ui) {
        super(filePath, ui);
        this.dishArrayList = new ArrayList<>();
    }

    @Override
    public ArrayList<Dish> loadData() throws FileNotFoundException {
        ArrayList<String> textLines = readTextFile(filePath);
        return extractData(textLines);
    }

    @Override
    public ArrayList<Dish> extractData(ArrayList<String> textLines) {
        for (String task : textLines) {
            String[] splitTaskString = task.split(" \\| ");
            String dishName = splitTaskString[0];
            float dishPrice = Float.parseFloat(splitTaskString[1]);
            String dishIngredient = splitTaskString[2];

            try {
                //todo: remove testing code
                Dish dish = new Dish(dishName, dishPrice);
                dishArrayList.add(dish);
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            }
        }

        return dishArrayList;
    }

    @Override
    public void storeData() throws IOException {
        String tasksFilePathString = openTextFile(filePath);

        if (dishArrayList.isEmpty()) {
            writeToFile(tasksFilePathString, ""); //overwrite text file to store empty text
        }
        //todo: remove testing code
        dishArrayList.add(new Dish("test", (float) 1.2));
        dishArrayList.add(new Dish("test", (float) 1.2));

        //input arraylist data into text file
        for (int i = 0; i < dishArrayList.size(); i++) {
            String taskDataRow = "chicken rice | 5.00 | rice 50g";

            if (i == 0) {
                writeToFile(tasksFilePathString, taskDataRow);
            } else {
                appendToFile(tasksFilePathString, taskDataRow);
            }
        }
    }


}
