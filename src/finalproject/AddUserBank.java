/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

/**
This program contains methods that will be implemented in a larger library system
 */
import java.util.*;
public class AddUserBank {

    public static void addUser(int userID,int entry,String[][] users){
        // Scanner function
        Scanner input = new Scanner(System.in);
        //Initialize Variables
        String name1,name2,userEmail, ID = Integer.toString(userID);
        boolean loop = true;
        
        //Collect Information
        while (loop == true){
            System.out.print("Please enter the user's last name: ");
            name1 = input.next();
            System.out.print("Please enter the user's first name: ");
            name2 = input.next();
            System.out.print("Please enter the user's email address: ");
            userEmail = input.next();
        
            //Verify User Information
            System.out.println("Is the following information correct? Y(Yes) or N(No)\n User ID: " + ID + "\n User Name: " + name1 +", " + name2 +"\n User Email Address: " + userEmail);
            if (input.next().equalsIgnoreCase("Y")){
                loop = false;
            }
            
            //Add information to "Users" array
            users[entry][0]=ID;
            users[entry][1]=name1;
            users[entry][2]=name2;
            users[entry][3]=userEmail;
            
        }

        
        
    }

    
    public static void editUser(String userID, String[][] users){
        //Initialze scanner and variables
        int rows = users.length, userNum = 0;
        boolean update = false;
        Scanner input = new Scanner(System.in);
        
        //search for specified user ID
        for (int i = 0;i <= rows; i++){
            if (users[i][0]!= null){
                if (users[i][0].equals(userID)){
                userNum = i;
                update = true;
                break;}
            }
        }
        
        // Edit specified user's information
        if (update == true){
            System.out.println ("Current information:\n Name: " + users[userNum][1] + ", " + users[userNum][2] + "\n Email: " + users[userNum][3]);
            System.out.println("Please enter new/updated information:");
            System.out.print(" Last name: ");
            users[userNum][1] = input.next();
            System.out.print(" First name: ");
            users[userNum][2] = input.next();
            System.out.print(" Email Address: ");
            users[userNum][3] = input.next();
        }            
        else{
            System.out.println("User does not exist!");}

    }
    
    public static void removeUser(String userID, int entry, String[][] users, String[][] archive){
        //Initialize variables
        int rows = users.length, userNum = 0;
        boolean move = false;
        
        //search for specified user ID
        for (int i = 0;i < rows; i++){
            if (users[i][0]!=null){
                if (users[i][0].equals(userID)){
                    userNum = i;
                    move = true;
                    break;}
            }
            
        }
        //move user to archive
        if (move == true){
            System.out.println(users[userNum][1] +", " + users[userNum][2] + " has been archived");
            for (int x = 0; x<4;x++){
                archive[entry][x] = users[userNum][x];
                users[userNum][x]= null;
            }      
        }
        else{
            System.out.println("User does not exist!");}
    }
    
   public static void returnUser(String userID, String[][] users, String[][] archive){
       //initialize variables
        int rows = users.length, userNum = 0;
        boolean move = false;
        
        //search for specified user ID
        for (int i = 0;i < rows; i++){
            if (archive[i][0]!=null){
                if (archive[i][0].equals(userID)){
                    userNum = i;
                    move = true;
                    break;}
            }
            
        }
        //move user to archive
        if (move == true){
            System.out.println(archive[userNum][1] +", " + archive[userNum][2] + " has been re-activated");
            for (int x = 0; x<4;x++){
                users[userNum][x] = archive[userNum][x];
                archive[userNum][x]= null;
            }      
        }
        else{
            System.out.println("User does not exist!");}
       
       
   }
   
   public static void displayStatus(String[][] array, int entries){
        for (int row = 0; row <entries; row++){
           for (int col = 0; col< 4; col++){
               if (array[row][col] != null){
                System.out.print(array[row][col]+ " ");
               }
            }
            if (array[row][0] != null){
                System.out.print("\n");
            }
        }
   }
    
    public static void main(String[] args) {
        //initilaize arrays and variables
        int userEntries = 0, userID = 10000, archiveEntries=0;
        
        String[][] users = new String[10000][6];
        String[][] archive = new String[10000][4];
        
        //Test addUser
        addUser(userID++,userEntries++,users);
        addUser(userID++,userEntries++,users);
        //addUser(userID++,userEntries++,users);
        
        //Display data in users array
        System.out.println("Active Accounts: ");
        displayStatus(users,userEntries);
        
        //Test editUser method
        editUser("10001",users);
        
        System.out.println("Active Accounts: ");
        displayStatus(users,userEntries);
        
        //Test removeUser method
        removeUser("10000",archiveEntries++,users,archive);
        removeUser("10001",archiveEntries++,users,archive);
        
        //Display accounts
        System.out.println("\nActive Accounts: ");
        displayStatus(users,userEntries);
        System.out.println("\nArchived Accounts: ");
        displayStatus(archive,archiveEntries);
        
        returnUser("10000",users,archive);
        
        //display accounts
        System.out.println("\nActive Accounts: ");
        displayStatus(users,userEntries);
        System.out.println("\nArchived Accounts: ");
        displayStatus(archive,archiveEntries);
    }
    
    
}
