/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author jangovhett
 */
class BorrowedBook {
    public String userId;
    public int isbn;
    
    //default constructor
    public BorrowedBook(){
        this.userId = "";
        this.isbn = 0;
    }
    
    //overloads to handle borrowed book objects with vals
    public BorrowedBook(String userId, int isbn){
        this.userId = userId;
        this.isbn = isbn;
    }
}
