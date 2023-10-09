package seedu.stocker.drugs;

import java.util.ArrayList;

public class Inventory {

    public ArrayList<Drug> allDrugs;

    public Inventory() {
        allDrugs = new ArrayList<>();
    }

    public void addDrug(Drug drug) {
        this.allDrugs.add(drug);
    }
}
