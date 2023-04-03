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

    private static void testMenu() {
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

    public static void createNewUser() {

        boolean loop = true;
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField email = new JTextField();

        //Create a unique random User ID
        String userID = createUserID.randomID();
        isDuplicateID(userID);

        //Enter and verify user information
        while (loop == true) {
            Object[] addUserText
                    = {
                        "ID: ", userID,
                        "First Name:", firstName,
                        "Last Name:", lastName,
                        "Email:", email
                    };
            JOptionPane.showConfirmDialog(null, addUserText, "Please fill in the following fields:", JOptionPane.PLAIN_MESSAGE);
            Object[] confirmUserText
                    = {
                        "ID: " + userID,
                        "FIRST NAME: " + firstName.getText(),
                        "LAST NAME: " + lastName.getText(),
                        "EMAIL: " + email.getText()
                    };
            int confirmUserOption = JOptionPane.showConfirmDialog(null, confirmUserText, "Are the following fields correct?", JOptionPane.YES_NO_OPTION);

            //Verify User Information
            if (confirmUserOption == JOptionPane.YES_OPTION) {
                loop = false;
            }
        }

        user newUser = new user(userID, firstName.getText(), lastName.getText(), email.getText(), false);
        currentUsers.add(newUser);

        //Test. Can be commented out when complete
        //showUserInfo();
    }

    public static void editUserData() {
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField email = new JTextField();
        boolean userFound = false;
        String desiredID = Main.userString("Enter the ID of the desired user: ");

        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;

                Object[] editUserText
                        = {
                            "New First Name:", firstName,
                            "New Last Name:", lastName,
                            "New Email:", email
                        };
                JOptionPane.showConfirmDialog(null, editUserText, "Please fill in the following fields:", JOptionPane.PLAIN_MESSAGE);

                //Update User Information
                users.firstName = firstName.getText();
                users.lastName = lastName.getText();
                users.email = email.getText();
            }
        }
        if (userFound == false) {
            Main.errorMessage("User doesn't exist!");
        }
        //Test. Can be commented out when complete
        //showUserInfo();
    }

    public static void showUserData() {
        boolean userFound = false;

        String desiredID = Main.userString("Enter the ID of the desired user: ");

        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;

                Main.userMessage("User Information: " + "\nUser ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email + "\nBooks Borrowed (ISBNs): " + BookHandler._checkedOutBooksByUser(desiredID));
            }
        }
        if (userFound != true) {
            Main.errorMessage("User doesn't exist");
        }
    }

    public static void archiveCurrentUser() {
        boolean userFound = false;
        user userIndex = null;
        String desiredID = Main.userString("Enter the ID of the desired user: ");

        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;
                userIndex = users;
            }
        }

        if (userIndex.hasBorrowed == true) {
            Main.errorMessage("User can not be archived while having borrowed a book");
        } else {
            archivedUser removedUser = new archivedUser(userIndex.userID, userIndex.firstName, userIndex.lastName, userIndex.email);
            archivedUsers.add(removedUser);

            currentUsers.remove(userIndex);
        }
        if (userFound == false) {
            Main.errorMessage("User doesn't exist");
            testMenu();
        }
        /*System.out.println("Current Users: ");
        showUserInfo();
        System.out.println("Archived Users: ");
        showArchivedInfo(); */
    }

    public static void reactivateUser() {
        boolean userFound = false;
        archivedUser userIndex = null;
        String desiredID = Main.userString("Enter the ID of the desired user: ");

        for (archivedUser users : archivedUsers) {
            if (users.userID.equals(desiredID)) {
                userFound = true;
                userIndex = users;
            }
        }
        if (userFound == false) {
            Main.errorMessage("User doesn't exist");
            return;
        }
        user reactivatedUser = new user(userIndex.userID, userIndex.firstName, userIndex.lastName, userIndex.email, false);
        currentUsers.add(reactivatedUser);

        archivedUsers.remove(userIndex);

        /*System.out.println("Current Users: ");
        showUserInfo();
        System.out.println("Archived Users: ");
        showArchivedInfo();*/
    }

    public static void showUserInfo() {
        //Displays current activates user IDs for testing purposes
        for (user users : currentUsers) {
            Main.userMessage("User ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email + "\nBooks Borrowed (ISBNs): " + BookHandler._checkedOutBooksByUser(users.userID));
        }
    }

    public static void showArchivedInfo() {
        //Displays current activates user IDs for testing purposes
        for (archivedUser users : archivedUsers) {
            Main.userMessage("User ID: " + users.userID + "\nUser Name: " + users.lastName + ", " + users.firstName + "\nUser Email: " + users.email);
        }
    }

    public static boolean doesUserExist(String desiredID) {
        for (user users : currentUsers) {
            if (users.userID.equals(desiredID)) {
                return true;
            }
        }
        return false;
    }

    private static void isDuplicateID(String userID) {
        for (user users : currentUsers) {
            if (users.userID.equals(userID)) {
                userID = createUserID.randomID();
            }
        }
    }

}
