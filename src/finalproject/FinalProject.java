package finalproject;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class FinalProject {

    public static void main(String[] args) 
    {
        boolean mainMenuExit = false;
        do
        {
            JTextField mainMenuChoice = new JTextField();
            Object[] mainMenuText =
            {
                """
                1. BOOK MENU
                2. USER MENU
                3. EXIT

                PLEASE CHOOSE ONE OF THE ABOVE OPTIONS: """, mainMenuChoice
            };
            JOptionPane.showConfirmDialog(null,mainMenuText, "MAIN MENU", JOptionPane.PLAIN_MESSAGE);
            switch (mainMenuChoice.getText()) 
            {
                case "1":
                    boolean bookMenuExit = false;
                    do
                    {
                        JTextField bookMenuChoice = new JTextField();
                        Object[] bookMenuText =
                        {
                            """
                            1. CHECK FOR OVERDUE BOOKS
                            2. BORROW BOOK
                            3. CHECK FOR LOST BOOKS
                            4. SEARCH FOR BOOK
                            5. ADD BOOK
                            6. UPDATE BOOK INFO
                            7. DELETE BOOK
                            8. BACK

                            PLEASE CHOOSE ONE OF THE ABOVE OPTIONS:""", bookMenuChoice
                        };
                        JOptionPane.showConfirmDialog(null,bookMenuText, "BOOK MENU", JOptionPane.PLAIN_MESSAGE);
                        switch (bookMenuChoice.getText()) 
                        {
                            case "1":
                                JOptionPane.showMessageDialog(null, "overdue book");
                                break;
                            case "2":
                                JOptionPane.showMessageDialog(null, "borrow book");
                                break;
                            case "3":
                                JOptionPane.showMessageDialog(null,"lost book");
                                break;
                            case "4":
                                JOptionPane.showMessageDialog(null,"search book");
                                break;
                            case "5":
                                JTextField title = new JTextField();
                                JTextField ISBN = new JTextField();
                                JTextField price = new JTextField();
                                JTextField isBorrowed = new JTextField();
                                Object[] addBookText =
                                {
                                    "TITLE:", title,
                                    "ISBN NUMBER:", ISBN,
                                    "PRICE:", price,
                                    "IS IT BORROWED (Y/N):", isBorrowed
                                };
                                JOptionPane.showConfirmDialog(null,addBookText, "PLEASE FILL IN THE FOLLOWING FIELDS", JOptionPane.PLAIN_MESSAGE);
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null,"update book");
                                break;
                            case "7":
                                JOptionPane.showMessageDialog(null,"delete book");
                                break;
                            case "8":
                                bookMenuExit = true;
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "INVALID INPUT! PLEASE TRY AGAIN.");
                                break;
                        }
                    }
                    while (!bookMenuExit);
                    break;
                case "2":
                    boolean userMenuExit = false;
                    do
                    {
                        JTextField userMenuChoice = new JTextField();
                        Object[] userMenuText =
                        {
                            """
                            1. CREATE USER
                            2. EDIT USER INFO
                            3. SEARCH FOR USER
                            4. ARCHIVE USER
                            5. REACTIVATE USER
                            6. BACK

                            PLEASE CHOOSE ONE OF THE ABOVE OPTIONS: """, userMenuChoice
                        };
                        JOptionPane.showConfirmDialog(null,userMenuText, "USER MENU", JOptionPane.PLAIN_MESSAGE);
                        switch (userMenuChoice.getText()) 
                        {
                            case "1":
                                JTextField firstName = new JTextField();
                                JTextField lastName = new JTextField();
                                JTextField ID = new JTextField();
                                JTextField email = new JTextField();
                                Object[] addUserText =
                                {
                                    "FIRST NAME:", firstName,
                                    "LAST NAME:", lastName,
                                    "ID:", ID,
                                    "EMAIL:", email
                                };
                                JOptionPane.showConfirmDialog(null,addUserText, "PLEASE FILL IN THE FOLLOWING FIELDS", JOptionPane.PLAIN_MESSAGE);
                                Object[] confirmUserText =
                                {
                                    "FIRST NAME: ", firstName.getText(),
                                    "LAST NAME: ",lastName.getText(),
                                    "ID: ", ID.getText(),
                                    "EMAIL", email.getText()
                                };
                                JOptionPane.showConfirmDialog(null,confirmUserText, "ARE THE FOLLOWING FIELDS CORREXT", JOptionPane.PLAIN_MESSAGE);
                                
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
                            case "6":
                                userMenuExit = true;
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "INVALID INPUT! PLEASE TRY AGAIN.");
                                break;
                        }
                    }
                    while (!userMenuExit);
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null,"THANK YOU FOR USING THE LIBRARY!");
                    mainMenuExit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "INVALID INPUT! PLEASE TRY AGAIN.");
                    break;
            }
        }
        while (!mainMenuExit);
    }
}
