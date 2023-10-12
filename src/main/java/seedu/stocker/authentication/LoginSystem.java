package seedu.stocker.authentication;

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
    private final HashMap<String, String> users;


    public LoginSystem() throws IOException {
        users = new HashMap<>();
        loginStatus = false;
        this.in = new Scanner(System.in);

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
                System.out.println("Invalid Input, enter 1 or 2 only!");
                return authenticateUserChoice();
            }
        }
        return "error";
    }

    public void newUserCreator() throws IOException {


        System.out.println("Enter your username:");
        String username = in.nextLine();

        System.out.println("Enter your password:");
        String password = in.nextLine();

        if (users.containsKey(username)) {
            System.out.println("User already exists. Please make user with different name or choose 2");
            System.out.println();
            System.out.println("Key in the respective number 1 or 2 based on your needs \n"
                    + "1.Register user \n" + "2.Login ");

            String reselect = authenticateUserChoice();
            if (reselect.equals("1")) {
                newUserCreator();

            } else if (reselect.equals("2")) {
                loginExistingUser();
            }
        } else {
            users.put(username, password);
            System.out.println("Registration successful.");
            loginStatus = true;
        }
        writeNewUserToFile();

    }

    public void loginExistingUser() throws IOException {


        System.out.println("Enter your username:");
        String usernameInput = in.nextLine();
        System.out.println("Enter your password:");
        String passwordInput = in.nextLine();

        if (!users.containsKey(usernameInput)) {
            System.out.println("Invalid username or password. Please try again.");
            System.out.println();
            System.out.println("Key in the respective number 1 or 2 based on your needs \n"
                    + "1.Register user \n" + "2.Login ");

            String reselect = authenticateUserChoice();
            if (reselect.equals("1")) {
                newUserCreator();

            } else if (reselect.equals("2")) {
                loginExistingUser();
            }

        } else {
            if (users.get(usernameInput).equals(passwordInput)) {
                System.out.println("Login successful.");
                loginStatus = true;
            } else {
                System.out.println("Invalid username or password. Please try again");
                System.out.println();
                System.out.println("Key in the respective number 1 or 2 based on your needs \n"
                        + "1.Register user \n" + "2.Login ");

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
