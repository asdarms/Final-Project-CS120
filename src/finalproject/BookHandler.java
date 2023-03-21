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
    
    public static void main(String[] args) {
        _directory();
    }
    
    // home menu for funcs
    private static void _directory(){
        _userPrompt("[1] Would you like to add a book to the library's vault?");
        _userPrompt("[2] Would you like to remove a book from the library's vault?");
        _userPrompt("[3] Would you like to check out a book from the library's vault?");
        _userPrompt("[4] Would you like to return a book to the library's vault?");
        _userPrompt("[5] Would you like to print out a list of all books from the library's vault?");
        _userPrompt("[6] Would you like to print out a list of all checked out books?");
        _userPrompt("[7] Would you like to see if specific book exists within the library's vault?");
        _userPrompt("[8] Would you like to update some infromation of a book?");
        _userPrompt("[9] Would you like to exit the program?");
        
        int userInput = _input.nextInt();
        
        switch (userInput) {
            case 1:
                _addBookToLibrary();
                break;
            case 2:
                _removeBookFromLibrary();
                break;
            case 3:
                _checkOutBook();
                break;
            case 4:
                _returnBook();
                break;
            case 5:
                _printAllBooks();
                break;
            case 6:
                _printAllBorrowedBooks();
                break;
            case 7:
                _doesBookExist();
                break;
            case 8:
                _updateBookInLibrary();
                break;
            case 9:
                _userPrompt("Goodbye!");
                System.exit(0);
                break;
            default :
                _userPrompt("* Improper Input Detected. \nGoodbye!");
                System.exit(0);
                break;
        }
    }
    
    // adds book to  class level list
    private static void _addBookToLibrary(){

        _userPrompt("Whats the ISBN of the book you would like to add?");
        int isbn = _checkISBN();
        _isDuplicateISBN(isbn);

        _userPrompt("Whats the price of the book you would like to add?");
        double price = _checkPrice();

        _userPrompt("Whats the title of the book you would like to add?");
        String title = _input.next();
        title += _input.nextLine();

        Book newBook = new Book(title,isbn,price,false);
        
        _booksAvailable.add(newBook);
        
        _directory();
    }
    
    // deletes Book obj from arraylist if isbn matches
    private static void _removeBookFromLibrary(){
        _userPrompt("Whats the ISBN of the book you would like to remove?");
        int isbn = _checkISBN();
        
        // NOTE: add logic to prevent deletion before book is returned
        for (int i = 0; i < _booksAvailable.size();i++){
            if (_booksAvailable.get(i).isbn == isbn){
                _booksAvailable.remove(i);
            }
        }
        _directory();
    }
    
    private static void _checkOutBook(){
        _userPrompt("What's the ISBN of the book you would like to check out?");
        int isbn = _checkISBN();
        boolean flag = false;
        int bookIdx = 0;
        
        for (int i = 0; i < _booksAvailable.size();i++){
            if (_booksAvailable.get(i).isbn == isbn){
                flag = true;
                bookIdx = i;
            }
        }
        
        if (flag == false){
            _userPrompt("The ISBN was not found, returning to main menu");
            _directory();
        }
        
        _userPrompt("What's the User ID?");
        // NOTE: Update this after merge
        //check if exists
        String userId = "1234567890";
        
        Book oldBook = _booksAvailable.get(bookIdx);
        oldBook.isBorrowed = true;
        Book newBook = oldBook; // this is broken out to improve readability
        
        _booksAvailable.set(bookIdx, newBook);
        
        BorrowedBook newCheckOut = new BorrowedBook(userId, isbn);
        _booksChecked.add(newCheckOut);

        _directory();
    }
    
    private static void _returnBook(){
        _userPrompt("What's the ISBN of the book you would like to return?");
        int isbn = _checkISBN();
        boolean flag = false;
        int bookIdx = 0;
        
        for (int i = 0; i < _booksAvailable.size();i++){
            if (_booksAvailable.get(i).isbn == isbn){
                flag = true;
                bookIdx = i;
            }
        }
        
        if (flag == false){
            _userPrompt("The ISBN was not found, returning to main menu");
            _directory();
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

        _directory();
    }
    
    // prints all Books
    private static void _printAllBooks(){
        for(Book book : _booksAvailable){
                _userPrompt("Title: "+book.title + "\r\nISBN: "+String.valueOf(book.isbn) +"\r\nPrice: "+ String.valueOf(_formatCost(book.price))+"\r\n");
            }
        _directory();
    }
    
    // prints all Checked out Books
    private static void _printAllBorrowedBooks(){
        for(BorrowedBook entity : _booksChecked){
                _userPrompt("ISBN: "+String.valueOf(entity.isbn) +"\r\nStudent ID: "+ String.valueOf(entity.userId)+"\r\n");
            }
        _directory();
    }
    
    // searches by isbn and prints out a book object
    private static void _doesBookExist(){
        _userPrompt("Whats the ISBN of the book you are searching for?");
        int isbn = _checkISBN();
        
        for(Book book : _booksAvailable){
            if (book.isbn == isbn){
                _userPrompt("The book was found!\r\n");
                _userPrompt("Title: "+book.title + "\r\nISBN: "+
                        String.valueOf(book.isbn) +"\r\nPrice: "+
                        String.valueOf(_formatCost(book.price))+
                            "\r\n");
            }
           }
        
        _directory();
    }
    
    // updates book obj if isbn is found to match
    private static void _updateBookInLibrary(){
        _userPrompt("Whats the ISBN of the book you are wanting to update?");
        int isbn = _checkISBN();
        
        for (int i = 0; i < _booksAvailable.size(); i++ ){
            if (_booksAvailable.get(i).isbn == isbn){
                _userPrompt("The book was found!\r\nIt currently has the following values.");
                _userPrompt("Title: "+_booksAvailable.get(i).title + "\r\nISBN: "+
                        String.valueOf(_booksAvailable.get(i).isbn) +"\r\nPrice: "+
                        String.valueOf(_formatCost(_booksAvailable.get(i).price))+
                            "\r\n");
                _userPrompt("Whats the new price of the book?");
                double price = _checkPrice();

                _userPrompt("Whats the new title of the book?");
                String title = _input.next();
                title += _input.nextLine();
        
                Book newBook = new Book(title,_booksAvailable.get(i).isbn,price,_booksAvailable.get(i).isBorrowed);
                _booksAvailable.set(i, newBook);
        }
        }
        
        _directory();
    }
    
    
    // Helper Funcs
    
    // Used because I hate typing the same sys cmd print repeatedly in code
    // More importantly, this allows future devs to change how the user interaction occurs, and the change then occurs at a singular place & commit
    private static void _userPrompt(String message){
        System.out.println(message);
    }
    
    // Formats double to $xyz.xy
    private static String _formatCost(double cost){
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(cost);
    }
    // Checks if dble is negative
    private static Double _checkPrice(){
        //set to -1.0 here as default to be caught if value isnt set
        double userInput = -1.0;
        try {
            userInput = _input.nextDouble();
            if (userInput <= 0){
                throw new InputMismatchException();
            }
        } catch (java.util.InputMismatchException e) {
            _userPrompt("You entered your value as an unrecognized value.\nMake sure it is in a correct format and try again.\nExamples: 97 or 36.8, not -29 or 0.");
            System.exit(0);
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            _userPrompt("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
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
            _userPrompt("The ISBN you entered was a duplicate value.");
            System.exit(0);
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            _userPrompt("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
            System.exit(0);
        }
    }
    // Checks if int is negative, above 3 chars in length
    private static int _checkISBN(){
        int userInput = 0000;
        try {
            userInput = _input.nextInt();
            if (String.valueOf(userInput).length() > 3 || userInput <= 0){
                //_userPrompt(String.valueOf(userInput);
                _userPrompt(String.valueOf(userInput));
                throw new InputMismatchException();
            }
        } catch (java.util.InputMismatchException e) {
            _userPrompt("You entered too many charectors and your value is now an unrecognized value.\nMake sure it is in a correct format and try again.\nExamples: 123 or 1, not -29 or 1234");
            System.exit(0);
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            _userPrompt("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
            System.exit(0);
        }
        return userInput;
    }
}
