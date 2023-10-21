package seedu.stocker.drugs;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public static ArrayList<Drug> allDrugs;

    public Inventory() {
        this.allDrugs = new ArrayList<>();
    }

    public static void addDrug(Drug drug) {
        allDrugs.add(drug);
    }

    public List<Drug> getAllDrugs() {
        return this.allDrugs;
    }
}
