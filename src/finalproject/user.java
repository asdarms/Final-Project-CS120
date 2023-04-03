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

public class user {

    public String userID;
    public String firstName;
    public String lastName;
    public String email;
    public Boolean hasBorrowed;
    public ArrayList<String> borrowedBooks;

    //constructor
    public user() {
        this.userID = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.hasBorrowed = false;
        this.borrowedBooks = new ArrayList<>();
    }

    //object updater via menu    
    public user(String userID, String firstName, String lastName, String email, Boolean hasBorrowed) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hasBorrowed = hasBorrowed;
        this.borrowedBooks = new ArrayList<>();
    }
}
