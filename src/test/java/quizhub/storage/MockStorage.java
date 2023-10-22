package quizhub.storage;

import java.util.ArrayList;
import java.util.List;

public class MockStorage extends Storage {
    private List<String> questions = new ArrayList<>();

    public MockStorage(String filepath) {
        super(filepath);
    }

    public void saveData(String dataToAdd) {
        questions.add(dataToAdd);
    }

    public String loadData() {
        // In-memory storage, retrieve data from the list
        if (questions.isEmpty()) {
            return "";
        }
        // Concatenate the data with line breaks
        StringBuilder result = new StringBuilder();
        for (String line : questions) {
            result.append(line).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
