package finalproject;

import java.util.*;
import javax.swing.*;
import java.io.*;

public class FinalProject {

    public static void main(String[] args) 
    {
        int mainMenuChoice;
        Scanner input = new Scanner(System.in);
        do
        {
            System.out.print("1. BOOK MENU\n2. USER MENU\n3. EXIT\n\nPLEASE CHOOSE ONE OF THE ABOVE OPTIONS: ");
            mainMenuChoice = input.nextInt();
            switch (mainMenuChoice) 
            {
                case 1:
                    BookHandler.main(args);
                    break;
                case 2:
                    //user menu
                    break;
                case 3:
                    System.out.println("THANK YOU FOR USING THE LIBRARY!");
                    break;
                default:
                   System.out.println("INVALID INPUT! PLEASE TRY AGAIN.");
                    break;
            }
        }
        while (mainMenuChoice != 3);
    }
}
