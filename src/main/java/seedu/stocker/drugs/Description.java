package seedu.stocker.drugs;

import java.util.HashMap;
import java.util.Map;

public class Description {
    private static Map<String, String> descriptions = new HashMap<>();

    public static void addDescription(String drugName, String description) {
        descriptions.put(drugName, description);
    }

    public static String getDescription(String drugName) {
        return descriptions.get(drugName);
    }

    public static Map<String, String> getAllDescriptions() {
        return descriptions;
    }

}
