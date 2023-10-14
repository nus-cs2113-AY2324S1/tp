package seedu.stocker.drugs;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public ArrayList<Drug> allDrugs;

    public Inventory() {
        this.allDrugs = new ArrayList<>();
    }

    public void addDrug(Drug drug) {
        this.allDrugs.add(drug);
    }

    public List<Drug> getAllDrugs() {
        return this.allDrugs;
    }
}
