package seedu.stocker.vendors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendorsList {

    protected ArrayList<Vendor> vendorArrayList;

    public VendorsList(){
        vendorArrayList = new ArrayList<>();
    }

    /**
     * Adds new vendor to vendor list
     *
     * @param vendor Vendor
     */
    public void addNewVendor(Vendor vendor){
        vendorArrayList.add(vendor);
    }

    /**
     * Gets details of all vendors
     * @return List<Vendor>
     */
    public List<Vendor> getVendorEntries() {
        return new ArrayList<>(this.vendorArrayList);
    }
}
