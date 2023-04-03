/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author samue
 */
public class archivedUser {
    public String userID;
    public String firstName;
    public String lastName;
    public String email;
    
    //constructor
    public archivedUser(){
        this.userID = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
    }
    
    //object updater via archiveUser method
    public archivedUser(String userID, String firstName, String lastName, String email){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
