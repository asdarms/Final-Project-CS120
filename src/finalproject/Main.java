package finalproject;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int mainMenuPopup;
        do // main menu
        {
            Object[] mainMenuText
                    = {
                        "Book Menu",
                        "User Menu",
                        "Exit"
                    };
            mainMenuPopup = JOptionPane.showOptionDialog(null, "Please choose an option:", "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, mainMenuText, null);
            switch (mainMenuPopup) {
                case JOptionPane.YES_OPTION: // book menu
                    bookMenu();
                    break;
                case JOptionPane.NO_OPTION: // user menu
                    userMenu();
                    break;
                default: // cancel button
                    JOptionPane.showMessageDialog(null, "Thank you for using the library!");
                    break;
            }
        } while (mainMenuPopup != JOptionPane.CANCEL_OPTION);
    }

    public static void bookMenu() {
        int bookMenuPopup;
        do {
            JTextField bookMenuChoice = new JTextField();
            Object[] bookMenuText
                    = {
                        """
                1. Add a book
                2. Remove a book
                3. Check out a book
                4. Return a book
                5. View all books
                6. View checked out books
                7. Search for a a book
                8. Update a book

                Please choose one of the above options:""", bookMenuChoice
                    };
            bookMenuPopup = JOptionPane.showConfirmDialog(null, bookMenuText, "Book Menu", JOptionPane.OK_CANCEL_OPTION);
            if (bookMenuPopup == JOptionPane.OK_OPTION) {
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
                        JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        } while (bookMenuPopup != JOptionPane.CANCEL_OPTION);
    }

    public static void userMenu() {
        int userMenuPopup;
        do {
            JTextField userMenuChoice = new JTextField();
            Object[] userMenuText
                    = {
                        """
                1. Create User
                2. Edit User
                3. View All Users
                4. Search for Active User
                5. Archive User
                6. Reactivate Archived User

                Please choose one of the above options:""", userMenuChoice
                    };
            userMenuPopup = JOptionPane.showConfirmDialog(null, userMenuText, "User Menu", JOptionPane.OK_CANCEL_OPTION);
            if (userMenuPopup == JOptionPane.OK_OPTION) {
                switch (userMenuChoice.getText()) {
                    case "1":
                        userMethods.createNewUser();
                        break;
                    case "2":
                        userMethods.editUserData();
                        break;
                    case "3":
                        userMethods.showUserInfo();
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
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        } while (userMenuPopup != JOptionPane.CANCEL_OPTION);
    }

    public static void userMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static int userInt(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        int output = Integer.parseInt(input);
        return output;
    }

    public static double userDouble(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        double output = Double.parseDouble(input);
        return output;
    }

    public static String userString(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return input;
    }
}
