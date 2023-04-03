/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jangovhett
 */
public class BookHandler {

    /**
     * @param args the command line arguments
     */
    private static Scanner _input = new Scanner(System.in);
    private static ArrayList<Book> _booksAvailable = new ArrayList<Book>();
    private static ArrayList<BorrowedBook> _booksChecked = new ArrayList<BorrowedBook>();
    
    // adds book to  class level list
    public static void _addBookToLibrary(){

        int isbn = _checkISBN("Whats the ISBN of the book you would like to add?");
        _isDuplicateISBN(isbn);

        double price = _checkPrice("Whats the price of the book you would like to add?");

        String title = Main.userString("Whats the title of the book you would like to add?");
        //String title = _input.next();
        //title += _input.nextLine();

        Book newBook = new Book(title,isbn,price,false);
        
        _booksAvailable.add(newBook);
    }
    
    // deletes Book obj from arraylist if isbn matches
    public static void _removeBookFromLibrary(){
        int isbn = _checkISBN("Whats the ISBN of the book you would like to remove?");
        
        // NOTE: add logic to prevent deletion before book is returned
        for (int i = 0; i < _booksAvailable.size();i++){
            if (_booksAvailable.get(i).isbn == isbn){
                _booksAvailable.remove(i);
            }
        }
    }
    
    public static void _checkOutBook(){
        int isbn = _checkISBN("What's the ISBN of the book you would like to check out?");
        boolean flag = false;
        int bookIdx = 0;
        
        for (int i = 0; i < _booksAvailable.size();i++){
            if (_booksAvailable.get(i).isbn == isbn){
                flag = true;
                bookIdx = i;
            }
        }
        
        if (flag == false){
            Main.errorMessage("The ISBN was not found, returning to main menu");
            return;
        }
        
        Main.userString("What's the User ID?");
        // NOTE: Update this after merge
        //check if exists
        String userId = "1234567890";
        
        Book oldBook = _booksAvailable.get(bookIdx);
        oldBook.isBorrowed = true;
        Book newBook = oldBook; // this is broken out to improve readability
        
        _booksAvailable.set(bookIdx, newBook);
        
        BorrowedBook newCheckOut = new BorrowedBook(userId, isbn);
        _booksChecked.add(newCheckOut);
    }
    
    public static void _returnBook(){
        int isbn = _checkISBN("What's the ISBN of the book you would like to return?");
        boolean flag = false;
        int bookIdx = 0;
        
        for (int i = 0; i < _booksAvailable.size();i++){
            if (_booksAvailable.get(i).isbn == isbn){
                flag = true;
                bookIdx = i;
            }
        }
        
        if (flag == false){
            Main.errorMessage("The ISBN was not found, returning to main menu");
            return;
        }
        
        Book oldBook = _booksAvailable.get(bookIdx);
        oldBook.isBorrowed = false;
        Book newBook = oldBook; // this is broken out to improve readability
        
        _booksAvailable.set(bookIdx, newBook);
        
        for (int i = 0; i < _booksChecked.size();i++){
            if (_booksChecked.get(i).isbn == isbn){
                bookIdx = i;
            }
        }
        
        _booksChecked.remove(bookIdx);
    }
    
    // prints all Books
    public static void _printAllBooks(){
        for(Book book : _booksAvailable){
                Main.userMessage("Title: "+book.title + "\r\nISBN: "+String.valueOf(book.isbn) +"\r\nPrice: "+ String.valueOf(_formatCost(book.price))+"\r\n");
            }
    }
    
    // prints all Checked out Books
    public static void _printAllBorrowedBooks(){
        for(BorrowedBook entity : _booksChecked){
                Main.userMessage("ISBN: "+String.valueOf(entity.isbn) +"\r\nStudent ID: "+ String.valueOf(entity.userId)+"\r\n");
            }
    }
    
    // searches by isbn and prints out a book object
    public static void _doesBookExist(){
        int isbn = _checkISBN("Whats the ISBN of the book you are searching for?");
        
        for(Book book : _booksAvailable){
            if (book.isbn == isbn){
                Main.userMessage("The book was found!\r\n"
                        +"Title: "+book.title + "\r\nISBN: "+
                        String.valueOf(book.isbn) +"\r\nPrice: "+
                        String.valueOf(_formatCost(book.price))+
                            "\r\n");
            }
           }
    }
    
    // updates book obj if isbn is found to match
    public static void _updateBookInLibrary(){
        int isbn = _checkISBN("Whats the ISBN of the book you are wanting to update?");
        
        for (int i = 0; i < _booksAvailable.size(); i++ ){
            if (_booksAvailable.get(i).isbn == isbn){
                Main.userMessage("The book was found!\r\nIt currently has the following values.\r\n"
                        +"Title: "+_booksAvailable.get(i).title + "\r\nISBN: "+
                        String.valueOf(_booksAvailable.get(i).isbn) +"\r\nPrice: "+
                        String.valueOf(_formatCost(_booksAvailable.get(i).price))+
                            "\r\n");
                double price = _checkPrice("Whats the new price of the book?");

                String title = Main.userString("Whats the new title of the book?");
                //String title = _input.next();
                //title += _input.nextLine();
        
                Book newBook = new Book(title,_booksAvailable.get(i).isbn,price,_booksAvailable.get(i).isBorrowed);
                _booksAvailable.set(i, newBook);
            }
        }
    }
    
    
    // Helper Funcs
    
    // Used because I hate typing the same sys cmd print repeatedly in code
    // More importantly, this allows future devs to change how the user interaction occurs, and the change then occurs at a singular place & commit
    // Andrew here. I moved this one to the main class and renamed it.
    
    
    // Formats double to $xyz.xy
    private static String _formatCost(double cost){
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(cost);
    }
    // Checks if dble is negative
    private static Double _checkPrice(String message){
        //set to -1.0 here as default to be caught if value isnt set
        double userInput = -1.0;
        try {
            userInput = Main.userDouble(message);
            if (userInput <= 0){
                throw new InputMismatchException();
            }
        } catch (java.util.InputMismatchException e) {
            Main.errorMessage("You entered your value as an unrecognized value.\nMake sure it is in a correct format and try again.\nExamples: 97 or 36.8, not -29 or 0.");
            System.exit(0);
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            Main.errorMessage("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
            System.exit(0);
        }
        return userInput;
    }
    
    private static void _isDuplicateISBN(int ISBN){
        try {
            for(Book book : _booksAvailable){
                if (book.isbn == ISBN){
                    throw new InputMismatchException();
                }
            }
        } catch (java.util.InputMismatchException e) {
            Main.errorMessage("The ISBN you entered was a duplicate value.");
            System.exit(0);
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            Main.errorMessage("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
            System.exit(0);
        }
    }
    // Checks if int is negative, above 3 chars in length
    private static int _checkISBN(String message){
        int userInput = 0000;
        try {
            userInput = Main.userInt(message);
            if (String.valueOf(userInput).length() > 3 || userInput <= 0){
                throw new InputMismatchException();
            }
        } catch (java.util.InputMismatchException e) {
            Main.errorMessage("You entered too many characters and your value is now an unrecognized value.\nMake sure it is in a correct format and try again.\nExamples: 123 or 1, not -29 or 1234");
            System.exit(0);
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            Main.errorMessage("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
            System.exit(0);
        }
        return userInput;
    }
}
