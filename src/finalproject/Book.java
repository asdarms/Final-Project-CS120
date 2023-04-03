/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author jangovhett
 */
class Book {
    public String title;
    public int isbn;
    public double price;
    public boolean isBorrowed;
    
    //default constructor
    public Book(){
        this.title = "";
        this.isbn = 0;
        this.price = 0.0;
        this.isBorrowed = false;
    }
    //overloads to handle book objects with vals
    public Book(String title, int isbn, double price, boolean isBorrowed){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isBorrowed = isBorrowed;
    }
}
