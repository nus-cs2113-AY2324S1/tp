package quizhub.storage;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;

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

    public boolean dataExists() {
        return !questions.isEmpty();
    }

    public void clearData() {
        questions.clear();
    }

    @Override
    public void updateData(QuestionList questions) {
        // Clear existing data and save all questions in the question list
        clearData();
        for (Question question : questions.getAllQns()) {
            saveData(questionToDataString(question));
        }
    }

    // Helper method to convert a Question to a data string (format it as it would be stored)
    private String questionToDataString(Question question) {
        String isDoneString = "undone";
        if (question.questionIsDone()) {
            isDoneString = "done";
        }
        switch (question.getQuestionType()) {
        case SHORTANSWER:
            return "S | " + isDoneString + " | " + question.getQuestionDescription();
        default:
            return ""; // Handle other question types as needed
        }
    }
}
