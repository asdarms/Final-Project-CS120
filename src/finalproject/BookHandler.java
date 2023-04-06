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
    // was only used for testing
    private static void _directory() {
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
            default:
                _userPrompt("* Improper Input Detected. \nGoodbye!");
                System.exit(0);
                break;
        }
    }

    // adds book to  class level list
    public static void _addBookToLibrary() {

        int isbn = _checkISBN("What's the ISBN of the book you would like to add?\nIt can only be up to 3 digits.");
        if (isbn == -1) {
            return;
        }
        if (!_isDuplicateISBN(isbn)) {
            return; //returns if error fails
        }
        double price = _checkPrice("What's the price of the book you would like to add?");
        if (price == -1.0) {
            return; //returns if check fails
        }

        String title = Main.userString("What's the title of the book you would like to add?");

        Book newBook = new Book(title, isbn, price, false);

        _booksAvailable.add(newBook);
    }

    // deletes Book obj from arraylist if isbn matches
    public static void _removeBookFromLibrary() {
        int isbn = _checkISBN("What's the ISBN of the book you would like to remove?");
        if (isbn == -1) {
            return; //returns if check fails
        }

        for (int i = 0; i < _booksAvailable.size(); i++) {
            if (_booksAvailable.get(i).isbn == isbn) {
                if (_booksAvailable.get(i).isBorrowed) {
                    Main.errorMessage("Book must be returned first! Please try again.");
                    return;
                }
                _booksAvailable.remove(i);
            }
        }
        Main.userMessage("Book successfully deleted.");
    }

    public static void _checkOutBook() { //checks out a book to a user
        int isbn = _checkISBN("What's the ISBN of the book you would like to check out?");
        if (isbn == -1) {
            return;
        }

        boolean flag = false;
        int bookIdx = 0;

        for (int i = 0; i < _booksAvailable.size(); i++) { //check if book exists
            if (_booksAvailable.get(i).isbn == isbn) {
                flag = true;
                bookIdx = i;
            }
        }

        if (flag == false) {
            Main.errorMessage("That book could not be found! Please try again.");
            return;
        }

        if (_booksAvailable.get(bookIdx).isBorrowed) {
            Main.errorMessage("That book is already checked out! Please try again.");
            return;
        }

        String userId = userMethods.findUser(); //finds the user
        if (!userMethods.doesUserExist(userId)) {
            Main.errorMessage("That user could not be found! Please try again.");
            return;
        }

        Book oldBook = _booksAvailable.get(bookIdx);
        oldBook.isBorrowed = true;
        Book newBook = oldBook; // this is broken out to improve readability

        _booksAvailable.set(bookIdx, newBook);

        BorrowedBook newCheckOut = new BorrowedBook(userId, isbn);
        _booksChecked.add(newCheckOut);
    }

    public static void _returnBook() { //checks out a book and adds it to the borrowedBooks array
        if (!_booksChecked.isEmpty() && !_booksAvailable.isEmpty()) {
            int isbn = _checkISBN("What's the ISBN of the book you would like to return?");
            if (isbn == -1) {
                return;
            }

            boolean availableFlag = false;
            boolean bookChckdFlag = false;
            int bookAvailableIdx = -1;
            int bookCheckedIdx = -1;

            for (int i = 0; i < _booksAvailable.size(); i++) {
                if (_booksAvailable.get(i).isbn == isbn) {
                    availableFlag = true;
                    bookAvailableIdx = i;
                }
            }

            if (availableFlag == false || bookAvailableIdx < 0) {
                Main.errorMessage("That book could not be found! Please try again.");
                return;
            }

            Book oldBook = _booksAvailable.get(bookAvailableIdx);
            oldBook.isBorrowed = false;
            Book newBook = oldBook; // this is broken out to improve readability

            for (int i = 0; i < _booksChecked.size(); i++) {
                if (_booksChecked.get(i).isbn == isbn) {
                    bookChckdFlag = true;
                    bookCheckedIdx = i;
                }
            }

            if (bookChckdFlag == false || bookCheckedIdx < 0) {
                Main.errorMessage("That book is not currently checked out! Please try again.");
                return;
            }
            // removes book from checked out arrayList
            _booksChecked.remove(bookCheckedIdx);

            // updates book availability
            _booksAvailable.set(bookAvailableIdx, newBook);
            Main.userMessage("Your book, " + _booksAvailable.get(bookAvailableIdx).title + ", has been returned sucessfully.");
        } else {
            Main.userMessage("There are no books currently checked out.");
        }
    }

    // prints all Books
    public static void _printAllBooks() {
        for (Book book : _booksAvailable) {
            Main.userMessage("Title: " + book.title + "\nISBN: " + String.valueOf(book.isbn) + "\nPrice: " + String.valueOf(_formatCost(book.price)));
        }
        if (_booksAvailable.isEmpty()) {
            Main.userMessage("There are currently no books in the library.");
        }
    }

    // prints all Checked out Books
    public static void _printAllBorrowedBooks() {
        for (BorrowedBook entity : _booksChecked) {
            Main.userMessage("ISBN: " + String.valueOf(entity.isbn) + "\r\nStudent ID: " + String.valueOf(entity.userId) + "\r\n");
        }
        if (_booksChecked.isEmpty()) {
            Main.userMessage("There are no books currently checked out.");
        }
    }

    // searches by isbn and prints out a book object
    public static void _doesBookExist() {
        int isbn = _checkISBN("What's the ISBN of the book you are searching for?");
        if (isbn == -1) {
            return;
        }

        for (Book book : _booksAvailable) {
            if (book.isbn == isbn) {
                Main.userMessage("The book was found!\n" + "Title: " + book.title + "\nISBN: " + String.valueOf(book.isbn) + "\nPrice: " + String.valueOf(_formatCost(book.price)));
                return;
            }
        }
        Main.errorMessage("That book could not be found! Please try again.");
    }

    // updates book obj if isbn is found to match
    public static void _updateBookInLibrary() {
        int isbn = _checkISBN("What's the ISBN of the book you are wanting to update?");
        if (isbn == -1) {
            return;
        }

        for (int i = 0; i < _booksAvailable.size(); i++) {
            if (_booksAvailable.get(i).isbn == isbn) {
                Main.userMessage("The book was found!\nIt currently has the following values.\n" + "Title: " + _booksAvailable.get(i).title + "\nISBN: " + String.valueOf(_booksAvailable.get(i).isbn) + "\nPrice: " + String.valueOf(_formatCost(_booksAvailable.get(i).price)));
                double price = _checkPrice("What's the new price of the book?");
                if (price == -1.0) {
                    return;
                }

                String title = Main.userString("What's the new title of the book?");
                Book newBook = new Book(title, _booksAvailable.get(i).isbn, price, _booksAvailable.get(i).isBorrowed);
                _booksAvailable.set(i, newBook);
                return;
            }
        }
        Main.errorMessage("That book could not be found! Please try again.");
    }

    public static String _checkedOutBooksByUser(String userId) { //returns the titles of all books checked out by a given user
        String output = "";
        for (int i = 0; i < _booksChecked.size(); i++) {
            if (_booksChecked.get(i).userId.equals(userId)) {
                output += _booksChecked.get(i).isbn + ", ";
            }
        }
        if (output.length() > 0) {
            return output.substring(0, (output.length() - 2));
        } else {
            return "none";
        }
    }

    // Helper Funcs
    // Used because I hate typing the same sys cmd print repeatedly in code
    // More importantly, this allows future devs to change how the user interaction occurs, and the change then occurs at a singular place & commit
    private static void _userPrompt(String message) { //made redundant by userMessage method in main
        System.out.println(message);
    }

    // Formats double to $xyz.xy
    private static String _formatCost(double cost) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(cost);
    }

    // Checks if dble is negative
    private static Double _checkPrice(String message) {
        //set to -1.0 here as default to be caught if value isnt set
        double userInput = -1.0;
        try {
            userInput = Main.userDouble(message);
            if (userInput <= 0) {
                throw new InputMismatchException();
            }
        } catch (Exception e) {
            Main.errorMessage("Invalid input! Please try again.");
            return -1.0;
        }
        return userInput;
    }

    private static boolean _isDuplicateISBN(int ISBN) {
        try {
            for (Book book : _booksAvailable) {
                if (book.isbn == ISBN) {
                    throw new InputMismatchException();
                }
            }
        } catch (java.util.InputMismatchException e) {
            Main.errorMessage("The ISBN you entered was a duplicate value! Please try again.");
            return false;
        }
        return true;
    }

    // Checks if int is negative, above 3 chars in length
    private static int _checkISBN(String message) {
        int userInput = 0000;
        try {
            userInput = Main.userInt(message);
            if (String.valueOf(userInput).length() > 3 || userInput <= 0) {
                throw new InputMismatchException();
            }
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            Main.errorMessage("Invalid input! Please try again.");
            return -1;
        }
        return userInput;
    }
}
