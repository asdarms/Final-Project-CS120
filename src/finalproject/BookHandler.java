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

        int isbn = _checkISBN("Whats the ISBN of the book you would like to add?");
        if (isbn == -1) {
            return;
        }

        if (!_isDuplicateISBN(isbn)) {
            return;
        }

        double price = _checkPrice("Whats the price of the book you would like to add?");
        if (price == -1.0) {
            return;
        }

        String title = Main.userString("Whats the title of the book you would like to add?");
        //String title = _input.next();
        //title += _input.nextLine();

        Book newBook = new Book(title, isbn, price, false);

        _booksAvailable.add(newBook);
    }

    // deletes Book obj from arraylist if isbn matches
    public static void _removeBookFromLibrary() {
        int isbn = _checkISBN("Whats the ISBN of the book you would like to remove?");
        if (isbn == -1) {
            return;
        }

        // NOTE: add logic to prevent deletion before book is returned
        for (int i = 0; i < _booksAvailable.size(); i++) {
            if (_booksAvailable.get(i).isbn == isbn) {
                _booksAvailable.remove(i);
            }
        }
    }

    public static void _checkOutBook() {
        int isbn = _checkISBN("What's the ISBN of the book you would like to check out?");
        if (isbn == -1) {
            return;
        }

        boolean flag = false;
        int bookIdx = 0;

        for (int i = 0; i < _booksAvailable.size(); i++) {
            if (_booksAvailable.get(i).isbn == isbn) {
                flag = true;
                bookIdx = i;
            }
        }

        if (flag == false) {
            Main.errorMessage("The ISBN was not found, returning to main menu");
            return;
        }

        String userId = Main.userString("What's the User ID?");
        // NOTE: Update this after merge
        //check if exists
        if (!userMethods.doesUserExist(userId)) {
            Main.errorMessage("That user does not exist!");
            return;
        }

        Book oldBook = _booksAvailable.get(bookIdx);
        oldBook.isBorrowed = true;
        Book newBook = oldBook; // this is broken out to improve readability

        _booksAvailable.set(bookIdx, newBook);

        BorrowedBook newCheckOut = new BorrowedBook(userId, isbn);
        _booksChecked.add(newCheckOut);
    }

    public static void _returnBook() {
        if (_booksChecked.size() != 0 && _booksAvailable.size() != 0) {
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
                Main.errorMessage("The ISBN was not found within our library, returning to main menu.");
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
                Main.errorMessage("The ISBN was not found to have been checked out, returning to main menu.");
                return;
            }
            // removes book from checked out arrayList
            _booksChecked.remove(bookCheckedIdx);

            // updates book availability
            _booksAvailable.set(bookAvailableIdx, newBook);
            Main.userMessage("Your book, " + _booksAvailable.get(bookAvailableIdx).title + ", has been returned sucessfully.");
        } else {
            Main.errorMessage("There are no books currently checked out.");
        }
    }

    // prints all Books
    public static void _printAllBooks() {
        for (Book book : _booksAvailable) {
            Main.userMessage("Title: " + book.title + "\r\nISBN: " + String.valueOf(book.isbn) + "\r\nPrice: " + String.valueOf(_formatCost(book.price)) + "\r\n");
        }
    }

    // prints all Checked out Books
    public static void _printAllBorrowedBooks() {
        for (BorrowedBook entity : _booksChecked) {
            Main.userMessage("ISBN: " + String.valueOf(entity.isbn) + "\r\nStudent ID: " + String.valueOf(entity.userId) + "\r\n");
        }
    }

    // searches by isbn and prints out a book object
    public static void _doesBookExist() {
        int isbn = _checkISBN("Whats the ISBN of the book you are searching for?");
        if (isbn == -1) {
            return;
        }

        for (Book book : _booksAvailable) {
            if (book.isbn == isbn) {
                Main.userMessage("The book was found!\r\n"
                        + "Title: " + book.title + "\r\nISBN: "
                        + String.valueOf(book.isbn) + "\r\nPrice: "
                        + String.valueOf(_formatCost(book.price))
                        + "\r\n");
            } else {
                Main.errorMessage("The book was not found!");
            }
        }
    }

    // updates book obj if isbn is found to match
    public static void _updateBookInLibrary() {
        int isbn = _checkISBN("Whats the ISBN of the book you are wanting to update?");
        if (isbn == -1) {
            return;
        }

        for (int i = 0; i < _booksAvailable.size(); i++) {
            if (_booksAvailable.get(i).isbn == isbn) {
                Main.userMessage("The book was found!\r\nIt currently has the following values.\r\n"
                        + "Title: " + _booksAvailable.get(i).title + "\r\nISBN: "
                        + String.valueOf(_booksAvailable.get(i).isbn) + "\r\nPrice: "
                        + String.valueOf(_formatCost(_booksAvailable.get(i).price))
                        + "\r\n");
                double price = _checkPrice("Whats the new price of the book?");
                if (price == -1.0) {
                    return;
                }

                String title = Main.userString("Whats the new title of the book?");
                //String title = _input.next();
                //title += _input.nextLine();

                Book newBook = new Book(title, _booksAvailable.get(i).isbn, price, _booksAvailable.get(i).isBorrowed);
                _booksAvailable.set(i, newBook);
            }
        }
    }

    public static String _checkedOutBooksByUser(String userId) {
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
    // Andrew here. I moved this one to the main class and renamed it.
    private static void _userPrompt(String message) {
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
        } catch (java.util.InputMismatchException e) {
            Main.errorMessage("You entered your value as an unrecognized value.\nMake sure it is in a correct format and try again.\nExamples: 97 or 36.8, not -29 or 0.");
            return -1.0;
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            Main.errorMessage("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
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
            Main.errorMessage("The ISBN you entered was a duplicate value.");
            return false;
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            Main.errorMessage("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
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
        } catch (java.util.InputMismatchException e) {
            Main.errorMessage("You entered too many characters and your value is now an unrecognized value.\nMake sure it is in a correct format and try again.\nExamples: 123 or 1, not -29 or 1234");
            return -1;
        } catch (Exception e) {
            // I chose to not fail quietly here for all other exceptions to give the user some sort of failure notification
            Main.errorMessage("An unknown error has occured.\nTry again and if the error persists, contact a System Admin.");
            return -1;
        }
        return userInput;
    }
}
