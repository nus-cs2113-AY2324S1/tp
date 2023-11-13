package quizhub.ui;

import quizhub.questionlist.QuestionList;
import quizhub.storage.MockStorage;

import java.util.LinkedList;
import java.util.Queue;

public class MockUi extends Ui {
    private final Queue<String> userInputQueue = new LinkedList<>();
    private String lastDisplayedMessage;
    public MockUi(QuestionList tasks, MockStorage mockStorage) {
        super(mockStorage, tasks);
    }

    public void setUserInput(String input) {
        userInputQueue.add(input);
    }

    public String getUserInput() {
        if (userInputQueue.isEmpty()) {
            throw new RuntimeException("No more user inputs provided.");
        }
        return userInputQueue.poll();
    }

    @Override
    public void displayMessage(String message) {
        lastDisplayedMessage = message;
    }

    public String getLastDisplayedMessage() {
        return lastDisplayedMessage;
    }
}
