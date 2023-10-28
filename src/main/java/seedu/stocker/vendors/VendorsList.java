package seedu.stocker.vendors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendorsList {

    public static ArrayList<Vendor> vendorArrayList;

    public VendorsList(){
        vendorArrayList = new ArrayList<>();
    }

    public static void addNewVendor(Vendor vendor){
        vendorArrayList.add(vendor);
    }

    public static List<Vendor> getVendorEntries() {
        return vendorArrayList.stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
