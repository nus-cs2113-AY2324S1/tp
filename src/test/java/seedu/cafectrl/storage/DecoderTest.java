package seedu.cafectrl.storage;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author ziyi105
public class DecoderTest {
    @Test
    void decodePantryStockData_validData_pantryFilledWithStock() {
        Ui ui = new Ui();
        ArrayList<Ingredient> validPantryStockList = new ArrayList<>();
        validPantryStockList.add(new Ingredient("egg", 1, "g"));
        validPantryStockList.add(new Ingredient("milk", 1, "ml"));
        Pantry samplePantry = new Pantry(ui, validPantryStockList);

        ArrayList<String> fileDataList = new ArrayList<>();
        fileDataList.add("egg | 1 | g");
        fileDataList.add("milk | 1 | ml");
        Pantry testPantry = Decoder.decodePantryStockData(fileDataList);

        assertEquals(samplePantry.getPantryStock(), testPantry.getPantryStock());
    }

    @Test
    void decodePantryStockData_missingDivider_skipStock() {
        Ui ui = new Ui();
        ArrayList<Ingredient> validPantryStockList = new ArrayList<>();
        Pantry samplePantry = new Pantry(ui, validPantryStockList);

        ArrayList<String> fileDataList = new ArrayList<>();
        fileDataList.add("milk  1 | ml");
        Pantry testPantry = Decoder.decodePantryStockData(fileDataList);

        assertEquals(samplePantry.getPantryStock(), testPantry.getPantryStock());
    }

    @Test
    void decodePantryStockData_specialCharInIngredientName_skipStock() {
        Ui ui = new Ui();
        ArrayList<Ingredient> validPantryStockList = new ArrayList<>();
        Pantry samplePantry = new Pantry(ui, validPantryStockList);

        ArrayList<String> fileDataList = new ArrayList<>();
        fileDataList.add("milk| | 1 | ml");
        Pantry testPantry = Decoder.decodePantryStockData(fileDataList);

        assertEquals(samplePantry.getPantryStock(), testPantry.getPantryStock());
    }

    @Test
    void decodePantryStockData_invalidQtyType_skipStock() {
        Ui ui = new Ui();
        ArrayList<Ingredient> validPantryStockList = new ArrayList<>();
        Pantry samplePantry = new Pantry(ui, validPantryStockList);

        ArrayList<String> fileDataList = new ArrayList<>();
        fileDataList.add("milk | one | ml");
        Pantry testPantry = Decoder.decodePantryStockData(fileDataList);

        assertEquals(samplePantry.getPantryStock(), testPantry.getPantryStock());
    }

    @Test
    void decodePantryStockData_extraDividerAfterName_skipStock() {
        Ui ui = new Ui();
        ArrayList<Ingredient> validPantryStockList = new ArrayList<>();
        Pantry samplePantry = new Pantry(ui, validPantryStockList);

        ArrayList<String> fileDataList = new ArrayList<>();
        fileDataList.add("milk || 1 | ml");
        Pantry testPantry = Decoder.decodePantryStockData(fileDataList);

        assertEquals(samplePantry.getPantryStock(), testPantry.getPantryStock());
    }

    @Test
    void decodePantryStockData_invalidUnit_skipStock() {
        Ui ui = new Ui();
        ArrayList<Ingredient> validPantryStockList = new ArrayList<>();
        Pantry samplePantry = new Pantry(ui, validPantryStockList);

        ArrayList<String> fileDataList = new ArrayList<>();
        fileDataList.add("milk | 1 | glass");
        Pantry testPantry = Decoder.decodePantryStockData(fileDataList);

        assertEquals(samplePantry.getPantryStock(), testPantry.getPantryStock());
    }

    @Test
    void decodePantryStockData_repeatedIngredientName_skipStock() {
        Ui ui = new Ui();
        ArrayList<Ingredient> validPantryStockList = new ArrayList<>();
        Pantry samplePantry = new Pantry(ui, validPantryStockList);
        samplePantry.addIngredientToStock("milk", 1, "ml");

        ArrayList<String> fileDataList = new ArrayList<>();
        fileDataList.add("milk | 1 | ml");
        fileDataList.add("milk | 2 | ml");
        Pantry testPantry = Decoder.decodePantryStockData(fileDataList);

        assertEquals(samplePantry.getPantryStock(), testPantry.getPantryStock());
    }
}
