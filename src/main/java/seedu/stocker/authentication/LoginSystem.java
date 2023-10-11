package seedu.stocker.authentication;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginSystem {

    private HashMap<String, String> users;

    public LoginSystem() throws IOException {
        users = new HashMap<>();

        File holder = new File("./users.txt");
        if (holder.exists() == false) {
            holder.createNewFile();
            System.out.println("File created: " + holder.getName());
        } else {
            System.out.println(holder.getAbsolutePath());
            System.out.println("File already exists.");
        }
    }

    public String showWelcomeMessage() {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome! Key in the respective number based on your needs \n"
                + "1.Register user \n" + "2.Login ");
        String input = in.nextLine();

        if (input.equals("1")) {
            return "1";
        } else if (input.equals("2")) {
            return "2";
        }
        while (!input.equals("1") | !input.equals("2")) {
            System.out.println("Invalid Input, enter 1 or 2 only!");
            input = in.nextLine();
            if (input.equals("1")) {
                return "1";
            } else if (input.equals("2")) {
                return "2";
            }
        }
        in.close();
        return "An error occurred";
    }

    public void newUserCreator() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your username:");
        String username = input.nextLine();

        System.out.println("Enter your password:");
        String password = input.nextLine();

        if (users.containsKey(username)) {
            System.out.println("User already exists.");
        } else {
            users.put(username, password);
            System.out.println("Registration successful.");
        }
        input.close();
        writeNewUserToFile();

    }

    public void loginExistingUser() {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter your username:");
        String usernameInput = input.nextLine();
        System.out.println("Enter your password:");
        String passwordInput = input.nextLine();
        if (!users.containsKey(usernameInput)) {
            System.out.println("Invalid username or password.");
        } else {
            if (users.get(usernameInput).equals(passwordInput)) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid username or password.");
            }
        }

        input.close();

    }

    public void writeNewUserToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("./users.txt"));

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

    public int run() throws IOException {
        String choice = showWelcomeMessage();
        if (choice.equals("1")) {
            newUserCreator();

        } else if (choice.equals("2")) {
            loginExistingUser();
        }
        return 0;
    }


}
