/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author samue
 */
import java.util.*;
import javax.swing.*;

public class userMethods {

    public static Scanner input = new Scanner(System.in);
    private static ArrayList<user> currentUsers = new ArrayList<user>();
    private static ArrayList<archivedUser> archivedUsers = new ArrayList<archivedUser>();

    public static void main() {
        testMenu();
    }

    private static void testMenu() { //used for testing only
        int userInput;
        do {
            System.out.println("Please pick an available option: ");
            System.out.println("1. Create User");
            System.out.println("2. Edit Current User");
            System.out.println("3. Search for Active User");
            System.out.println("4. Archive User");
            System.out.println("5. Reactivate Archived User");
            System.out.println("6. Exit System");

            userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    createNewUser();
                    break;
                case 2:
                    editUserData();
                    break;
                case 3:
                    showUserData();
                    break;
                case 4:
                    archiveCurrentUser();
                    break;
                case 5:
                    reactivateUser();
                    break;
                case 6:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("* Improper Input *");
                    System.exit(0);
                    break;
            }
        } while (userInput != 6);
    }

    public static String randomID() { //generates a random user id
        String userID = "";

        for (int i = 0; i < 8; i++) {
            Random digit = new Random();
            int randomInt = digit.nextInt(10);

            String num = Integer.toString(randomInt);
            userID = userID + num;
        }

        return userID;
    }

    public static void createNewUser() { //creates a new user and adds them to the main array

        boolean loop = true;
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField email = new JTextField();

        //Create a unique random User ID
        String userID = randomID();
        isDuplicateID(userID);

        //Enter and verify user information
        while (loop == true) {
            Object[] addUserText  = {"ID: " + userID, "First Name:", firstName, "Last Name:", lastName, "Email:", email};
            JOptionPane.showConfirmDialog(null, addUserText, "Please fill in the following fields:", JOptionPane.PLAIN_MESSAGE);
            Object[] confirmUserText = {"ID: " + userID, "FIRST NAME: " + firstName.getText(), "LAST NAME: " + lastName.getText(), "EMAIL: " + email.getText()};
            int confirmUserOption = JOptionPane.showConfirmDialog(null, confirmUserText, "Are the following fields correct?", JOptionPane.YES_NO_OPTION);

            //Verify User Information
            if (confirmUserOption == JOptionPane.YES_OPTION) {
                loop = false;
            }
        }

        user newUser = new user(userID, firstName.getText(), lastName.getText(), email.getText(), false);
        currentUsers.add(newUser);
    }

    public static void editUserData() {  //allows editing an existing user in the active array
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField email = new JTextField();
        boolean userFound = false;
        String desiredID = findUser();

        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;

                Object[] editUserText = {"New First Name:", firstName, "New Last Name:", lastName, "New Email:", email};
                JOptionPane.showConfirmDialog(null, editUserText, "Please fill in the following fields:", JOptionPane.PLAIN_MESSAGE);

                //Update User Information
                users.firstName = firstName.getText();
                users.lastName = lastName.getText();
                users.email = email.getText();
            }
        }
        if (userFound == false) {
            Main.errorMessage("User could not be found! Please try again.");
        }
    }

    public static void showUserData() { //searches for a specific user and displays their data
        boolean userFound = false;

        String desiredID = findUser();

        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;

                Main.userMessage("User Information: " + "\nUser ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email + "\nBooks Borrowed (ISBNs): " + BookHandler._checkedOutBooksByUser(desiredID));
            }
        }
        if (userFound != true) {
            Main.errorMessage("User could not be found! Please try again.");
        }
    }

    public static void archiveCurrentUser() { //moves a user from the active array to the archived array
        boolean userFound = false;
        user userIndex = null;
        String desiredID = findUser();

        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;
                userIndex = users;
            }
        }
        if (userFound == false) {
            Main.errorMessage("User could not be found! Please try again.");
            return;
        }
        if (userIndex.hasBorrowed == true) {
            Main.errorMessage("User can not be archived whilst having borrowed a book! Please try again.");
        } else {
            archivedUser removedUser = new archivedUser(userIndex.userID, userIndex.firstName, userIndex.lastName, userIndex.email);
            archivedUsers.add(removedUser);

            currentUsers.remove(userIndex);
            Main.userMessage("The user has been successfully archived.");
        }
    }

    public static void reactivateUser() { //moves an archived user back into the active users array
        boolean userFound = false;
        archivedUser userIndex = null;
        String desiredID = findArchivedUser();

        for (archivedUser users : archivedUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;
                userIndex = users;
            }
        }
        if (userFound == false) {
            Main.errorMessage("User could not be found! Please try again.");
            return;
        }
        user reactivatedUser = new user(userIndex.userID, userIndex.firstName, userIndex.lastName, userIndex.email, false);
        currentUsers.add(reactivatedUser);

        archivedUsers.remove(userIndex);
        Main.userMessage("The user has been successfully reactivated.");
    }

    public static void showUserInfo() {//Displays current active users
        for (user users : currentUsers) {
            Main.userMessage("User ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email + "\nBooks Borrowed (ISBNs): " + BookHandler._checkedOutBooksByUser(users.userID));
        }
        if (currentUsers.isEmpty()) {
            Main.userMessage("There are currently no active users.");
        }
    }

    public static void showArchivedInfo() { //Displays current archived users
        for (archivedUser users : archivedUsers) {
            Main.userMessage("User ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email);
        }
        if (archivedUsers.isEmpty()) {
            Main.userMessage("There are currently no archived users.");
        }
    }

    public static boolean doesUserExist(String desiredID) { //checks if a user exists and returns a boolean
        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                return true;
            }
        }
        return false;
    }

    public static String findUser() { //allows searching for a user by last name to avoid having to memorize IDs
        String lastName = Main.userString("What is the last name of the user you are looking for?");
        for (user users : currentUsers) {
            if (users.lastName.equalsIgnoreCase(lastName)) {
                int confirmUserOption = JOptionPane.showConfirmDialog(null, "User ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email + "\nBooks Borrowed (ISBNs): " + BookHandler._checkedOutBooksByUser(users.userID), "Is this the correct user?", JOptionPane.YES_NO_OPTION);
                if (confirmUserOption == JOptionPane.YES_OPTION) {
                    return users.userID;
                }
            }
        }
        return null;
    }
    
    public static String findArchivedUser() { //findUser but for archived users
        String lastName = Main.userString("What is the last name of the user you are looking for?");
        for (archivedUser users : archivedUsers) {
            if (users.lastName.equalsIgnoreCase(lastName)) {
                int confirmUserOption = JOptionPane.showConfirmDialog(null, "User ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email, "Is this the correct user?", JOptionPane.YES_NO_OPTION);
                if (confirmUserOption == JOptionPane.YES_OPTION) {
                    return users.userID;
                }
            }
        }
        return null;
    }

    private static void isDuplicateID(String userID) { //checks if a generated ID is a duplicate and regenerates
        for (user users : currentUsers) {
            if (users.userID.equals(userID)) {
                userID = randomID();
            }
        }
    }

}
