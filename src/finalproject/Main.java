/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javax.swing.*;

/**
 *
 * @author andre
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int bookMenuPopup, userMenuPopup, exitMenuPopup = -1; // setting up option panes for loops
        do // main menu
        {
            Object[] mainMenuText = {"Book Menu", "User Menu", "Exit"}; //custom button text
            int mainMenuPopup = JOptionPane.showOptionDialog(null, "Welcome to the library!\nPlease choose an option:", "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, mainMenuText, null);
            switch (mainMenuPopup) {
                case JOptionPane.YES_OPTION: // book menu
                    do {
                        JTextField bookMenuChoice = new JTextField(); //userinput field for popup
                        Object[] bookMenuText = {"1. Add a book\n2. Remove a book\n3. Check out a book\n4. Return a book\n5. View all books\n6. View checked out books\n7. Search for a book\n8. Update a book\n\nPlease choose one of the above options:", bookMenuChoice};
                        Object[] bookMenuOptionText = {"OK", "Back"}; //custom button text
                        bookMenuPopup = JOptionPane.showOptionDialog(null, bookMenuText, "Book Menu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, bookMenuOptionText, null);
                        if (bookMenuPopup == JOptionPane.YES_OPTION) {
                            switch (bookMenuChoice.getText()) {
                                case "1":
                                    BookHandler._addBookToLibrary();
                                    break;
                                case "2":
                                    BookHandler._removeBookFromLibrary();
                                    break;
                                case "3":
                                    BookHandler._checkOutBook();
                                    break;
                                case "4":
                                    BookHandler._returnBook();
                                    break;
                                case "5":
                                    BookHandler._printAllBooks();
                                    break;
                                case "6":
                                    BookHandler._printAllBorrowedBooks();
                                    break;
                                case "7":
                                    BookHandler._doesBookExist();
                                    break;
                                case "8":
                                    BookHandler._updateBookInLibrary();
                                    break;
                                default:
                                    errorMessage("Invalid input! Please try again.");
                                    break;
                            }
                        }
                    } while (bookMenuPopup != JOptionPane.NO_OPTION);
                    break;
                case JOptionPane.NO_OPTION: // user menu
                    do {
                        JTextField userMenuChoice = new JTextField(); //user input field for popup
                        Object[] userMenuText = {"1. Create user\n2. Edit user\n3. View all users\n4. Search for active user\n5. Archive user\n6. Reactivate archived user\n7. View all archived users\n\nPlease choose one of the above options:", userMenuChoice};
                        Object[] userMenuOptionText = {"OK", "Back"}; //custom button text
                        userMenuPopup = JOptionPane.showOptionDialog(null, userMenuText, "User Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, userMenuOptionText, null);
                        if (userMenuPopup == JOptionPane.YES_OPTION) {
                            switch (userMenuChoice.getText()) {
                                case "1":
                                    userMethods.createNewUser();
                                    break;
                                case "2":
                                    userMethods.editUserData();
                                    break;
                                case "3":
                                    userMethods.showAllUsers();
                                    break;
                                case "4":
                                    userMethods.showUserData();
                                    break;
                                case "5":
                                    userMethods.archiveCurrentUser();
                                    break;
                                case "6":
                                    userMethods.reactivateUser();
                                    break;
                                case "7":
                                    userMethods.showArchivedInfo();
                                    break;
                                default:
                                    errorMessage("Invalid input! Please try again.");
                                    break;
                            }
                        }
                    } while (userMenuPopup != JOptionPane.NO_OPTION);
                    break;
                default: // exit button, confirms if user wants to exit to avoid accidents
                    exitMenuPopup = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit the library?\nAll data will be erased!", "Main Menu", JOptionPane.YES_NO_OPTION);
                    break;
            }
        } while (exitMenuPopup != JOptionPane.YES_OPTION);
    }

    public static void userMessage(String message) { //method to create a basic popup used by other classes
        JOptionPane.showMessageDialog(null, message);
    }

    public static void errorMessage(String message) { //method to format error messages from other classes
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static String userString(String message) { //method to ask the user for a string
        JTextField userInput = new JTextField();
        Object[] inputOptionText = {message, userInput};
        JOptionPane.showConfirmDialog(null, inputOptionText, "Input", JOptionPane.PLAIN_MESSAGE);
        return (userInput.getText());
    }

    public static int userInt(String message) { //popup to ask for int
        String input = userString(message);
        int output = Integer.parseInt(input);
        return output;
    }

    public static double userDouble(String message) { //popup to ask for double
        String input = userString(message);
        double output = Double.parseDouble(input);
        return output;
    }

}
