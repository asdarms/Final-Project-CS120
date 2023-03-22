package finalproject;
import javax.swing.*;

public class Main {

    public static void main(String[] args) 
    {
        //AddUserBank.main(args);
        int mainMenuPopup, bookMenuPopup, userMenuPopup;
        do // main menu
        {
            Object[] mainMenuText =
            {
                "Book Menu",
                "User Menu",
                "Exit"
            };
            mainMenuPopup = JOptionPane.showOptionDialog(null, "Please choose an option:", "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, mainMenuText, null);
            switch (mainMenuPopup) 
            {
                case JOptionPane.YES_OPTION: // book menu
                    do
                    {
                        JTextField bookMenuChoice = new JTextField();
                        Object[] bookMenuText =
                        {
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
                        bookMenuPopup = JOptionPane.showConfirmDialog(null,bookMenuText, "Book Menu", JOptionPane.OK_CANCEL_OPTION);
                        if (bookMenuPopup == JOptionPane.OK_OPTION)
                            switch (bookMenuChoice.getText()) 
                            {
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
                    while (bookMenuPopup != JOptionPane.CANCEL_OPTION);
                    break;
                case JOptionPane.NO_OPTION: // user menu
                    do
                    {
                        JTextField userMenuChoice = new JTextField();
                        Object[] userMenuText =
                        {
                            """
                            1. Add a user
                            2. Edit a user
                            3. Archive a user
                            4. Reactivate a user
                            5. View all users

                            Please choose one of the above options:""", userMenuChoice
                        };
                        userMenuPopup = JOptionPane.showConfirmDialog(null,userMenuText, "User Menu", JOptionPane.OK_CANCEL_OPTION);
                        if (userMenuPopup == JOptionPane.OK_OPTION)
                            switch (userMenuChoice.getText()) 
                            {
                                case "1":
                                    JTextField firstName = new JTextField();
                                    JTextField lastName = new JTextField();
                                    JTextField email = new JTextField();
                                    String userID = createUserID.randomID();
                                    Object[] addUserText =
                                    {
                                        "ID: ", userID,
                                        "First Name:", firstName,
                                        "Last Name:", lastName,
                                        "Email:", email
                                    };
                                    JOptionPane.showConfirmDialog(null,addUserText, "Please fill in the following fields:", JOptionPane.PLAIN_MESSAGE);
                                    Object[] confirmUserText =
                                    {
                                        "ID: " + userID,
                                        "FIRST NAME: " + firstName.getText(),
                                        "LAST NAME: " + lastName.getText(),
                                        "EMAIL: " + email.getText()
                                    };
                                    JOptionPane.showConfirmDialog(null,confirmUserText, "Are the following fields correct?", JOptionPane.YES_NO_OPTION);

                                    break;
                                case "2":
                                    JOptionPane.showMessageDialog(null, "edit user");
                                    break;
                                case "3":
                                    JOptionPane.showMessageDialog(null,"search user");
                                    break;
                                case "4":
                                    JOptionPane.showMessageDialog(null,"archive user");
                                    break;
                                case "5":
                                    JOptionPane.showMessageDialog(null,"reactivate user");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                    }
                    while (userMenuPopup != JOptionPane.CANCEL_OPTION);
                    break;
                default: // invalid input
                    JOptionPane.showMessageDialog(null,"Thank you for using the library!");
                    break;
            }
        }
        while (mainMenuPopup != JOptionPane.CANCEL_OPTION);
    }
    public static void userMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }
    public static void errorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static int userInt(String message)
    {
        String input = JOptionPane.showInputDialog(null, message);
        int output = Integer.parseInt(input);
        return output;
    }
    public static double userDouble(String message)
    {
        String input = JOptionPane.showInputDialog(null, message);
        double output = Double.parseDouble(input);
        return output;
    }
    public static String userString(String message)
    {
        String input = JOptionPane.showInputDialog(null, message);
        return input;
    }
}