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

/**
 * Represents a login system used for authentication of users.
 * User information is saved within a hashtable and uploaded
 * into txt file for future reference.
 */
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

    /**
     * Returns user choice of whether they wish to register a new user
     * or if they would want to login using an existing user.
     *
     * @return choice of registering or logging into the system
     */
    public String authenticateUserChoice() {

        while (in.hasNextLine()) {
            String choice = in.nextLine();

            if (choice.equals("register")) {
                return "register";
            } else if (choice.equals("login")) {
                return "login";
            } else {
                interactor.showInvalidChoiceMessage();
                return authenticateUserChoice();
            }
        }
        return "error";
    }

    /**
     * Creates a new user with input username and password from user.
     * Username and password are saved into a txt file for future
     * reference.
     *
     * @throws IOException if inappropriate output is entered.
     */
    public void newUserCreator() throws IOException {

        interactor.showUsernameMessage();
        String username = in.nextLine();

        while (username.equals("")) {
            interactor.showBlankNameMessage();
            username = in.nextLine();
        }

        interactor.showPasswordMessage();
        String password = in.nextLine();

        while (password.equals("")) {
            interactor.showBlankPasswordMessage();
            password = in.nextLine();
        }
        assert(username.equals("") == false);
        assert (password.equals("") == false);

        if (users.containsKey(username)) {
            interactor.showUserAlreadyExistMessage();
            System.out.println();
            interactor.showEnterChoiceAgainMessage();

            String reselect = authenticateUserChoice();
            if (reselect.equals("register")) {

                newUserCreator();

            } else if (reselect.equals("login")) {
                loginExistingUser();
            }
        } else {
            users.put(username, password);
            interactor.showSuccessfulRegistrationMessage();
            loginStatus = true;
        }
        writeNewUserToFile();

    }

    /**
     * Login existing user by asking for username and password input
     * from user.
     *
     * @throws IOException if inappropriate input is entered.
     */
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
            if (reselect.equals("register")) {
                newUserCreator();

            } else if (reselect.equals("login")) {
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
                if (reselect.equals("register")) {
                    newUserCreator();

                } else if (reselect.equals("login")) {
                    loginExistingUser();
                }
            }
        }


    }

    /**
     * Writes new user creation into a txt file to save for future reference
     *
     * @throws IOException
     */
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

    /**
     * Loads existing users from txt file into hash table
     * for login system to use for authentication when
     * user tries to login.
     *
     * @throws IOException if fail to read from txt file
     */
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

    /**
     * Runs login system by loading user information into hash table
     * and get input for user to check for authentication.
     *
     * @throws IOException if unable to read from txt file to
     *                     load users
     */
    public void run() throws IOException {
        loadExistingUsers();
        String choice = authenticateUserChoice();
        if (choice.equals("register")) {
            newUserCreator();

        } else if (choice.equals("login")) {
            loginExistingUser();
        }

    }

}
