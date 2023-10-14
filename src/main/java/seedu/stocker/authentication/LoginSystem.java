package seedu.stocker.authentication;

import seedu.stocker.ui.Ui;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginSystem {
    public boolean loginStatus;
    private final Scanner in;
    private Ui interactor;
    private final HashMap<String, String> users;


    public LoginSystem() throws IOException {
        users = new HashMap<>();
        loginStatus = false;
        this.in = new Scanner(System.in);
        interactor = new Ui();

        File holder = new File("./users.txt");
        if (!holder.exists()) {
            holder.createNewFile();
        }
    }

    public String authenticateUserChoice() {

        while (in.hasNextLine()) {
            String choice = in.nextLine();


            if (choice.equals("1")) {
                return "1";
            } else if (choice.equals("2")) {
                return "2";
            } else {
                interactor.showInvalidChoiceMessage();
                return authenticateUserChoice();
            }
        }
        return "error";
    }

    public void newUserCreator() throws IOException {


        interactor.showUsernameMessage();
        String username = in.nextLine();

        interactor.showPasswordMessage();
        String password = in.nextLine();

        if (users.containsKey(username)) {
            interactor.showUserAlreadyExistMessage();
            System.out.println();
            interactor.showEnterChoiceAgainMessage();

            String reselect = authenticateUserChoice();
            if (reselect.equals("1")) {
                newUserCreator();

            } else if (reselect.equals("2")) {
                loginExistingUser();
            }
        } else {
            users.put(username, password);
            interactor.showSuccessfulRegistrationMessage();
            loginStatus = true;
        }
        writeNewUserToFile();

    }

    public void loginExistingUser() throws IOException {


        interactor.showUsernameMessage();
        String usernameInput = in.nextLine();

        interactor.showPasswordMessage();
        String passwordInput = in.nextLine();

        if (!users.containsKey(usernameInput)) {
            interactor.showInvalidUsernameOrPasswordMessage();
            System.out.println();
            interactor.showEnterChoiceAgainMessage();

            String reselect = authenticateUserChoice();
            if (reselect.equals("1")) {
                newUserCreator();

            } else if (reselect.equals("2")) {
                loginExistingUser();
            }

        } else {
            if (users.get(usernameInput).equals(passwordInput)) {
                interactor.showSuccessfulLoginMessage();
                loginStatus = true;
            } else {
                interactor.showInvalidUsernameOrPasswordMessage();
                System.out.println();
                interactor.showEnterChoiceAgainMessage();

                String reselect = authenticateUserChoice();
                if (reselect.equals("1")) {
                    newUserCreator();

                } else if (reselect.equals("2")) {
                    loginExistingUser();
                }
            }
        }


    }

    public void writeNewUserToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("./users.txt", true));

        for (Map.Entry<String, String> entry :
                users.entrySet()) {
            // put key and value separated by a colon
            writer.write(entry.getKey() + ":"
                    + entry.getValue());
            // new line
            writer.newLine();
        }

        writer.flush();
        writer.close();

    }

    public void loadExistingUsers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./users.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                users.put(key, value);
            }
        }
    }

    public int run() throws IOException {
        loadExistingUsers();
        String choice = authenticateUserChoice();
        if (choice.equals("1")) {
            newUserCreator();

        } else if (choice.equals("2")) {
            loginExistingUser();
        }
        return 0;
    }


}
