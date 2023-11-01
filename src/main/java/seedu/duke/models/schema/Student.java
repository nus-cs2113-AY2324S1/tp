package seedu.duke.models.schema;

import java.util.ArrayList;

public class Student {

    private String name;
    private Major major;

    private String year;

    private ArrayList<String> currentMods;

    public Student(String name, Major major) {
        this.name = name;
        this.major = major;
        this.year = "";
    }

    public Student() {
        this.name = null;
        this.major = null;
    }

    public String getName() {
        return name;
    }

    public Major getMajor() throws NullPointerException {
        return major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String updateMajor(String userInput) {
        String[] words = userInput.split(" ");
        if (words.length < 2) {
            return "currentMajor";
        }
        try {
            setMajor(Major.valueOf(words[1].toUpperCase()));
            return "newMajor";
        } catch (IllegalArgumentException e) {
            return "invalidMajor";
        }
    }

    /**
     * Sets the first major without the major command
     * @author Isaiah Cerven
     * @param userInput must be validated in parser as CS or CEG
     */
    public void setFirstMajor(String userInput){
        try {
            setMajor(Major.valueOf(userInput.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String printMajor() {
        String majorString = "";
        if (major == null) {
            System.out.println("Major is not set.");
        } else {
            switch (major) {
            case CS:
                majorString = "Computer Science";
                break;
            case CEG:
                majorString = "Computer Engineering";
                break;
            default:
                majorString = "Unknown Major";
            }
            return majorString;
        }
        return majorString;
    }

    /**
     * Returns all the known information about a certain student object
     * @author Isaiah Cerven
     */
    public void printStudentInformation(){
        String majorString = printMajor();
        System.out.println("Name: " + name);
        System.out.println("Major: " + majorString);
        System.out.println("Year " + year);
    }


}
