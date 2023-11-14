package seedu.duke.meal;

public class CategoryParser {
    private static Category[] categories = Category.values();

    public static Category Parse(String categoryString) throws Exception {
        try {
            int index = Integer.parseInt(categoryString);
            return categories[index];
        } catch (Exception exception1) {
            try {
                return Category.valueOf(categoryString);
            } catch (Exception exception2) {
                throw new Exception(
                        "Unable to parse Category String into Category enum.\nPlease input a number ranging in 0~2 or a valid category type.");
            }
        }
    }
}
