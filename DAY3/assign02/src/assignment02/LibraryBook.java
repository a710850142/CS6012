package assignment02;

import java.util.GregorianCalendar;

// LibraryBook extends the Book class with additional features to handle check-outs.
public class LibraryBook<Type> extends Book {

    private Type holder; // The variable 'holder' can be of any type and is used to track who has the book.
    private GregorianCalendar dueDate; // 'dueDate' stores the date by which the book is due back in the library.

    // Constructor for LibraryBook, calls the superclass constructor and initializes holder and dueDate to null.
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title); // Call the constructor of the superclass, Book.
        this.holder = null; // Initially, the book is not checked out to anyone.
        this.dueDate = null; // Initially, there is no due date.
    }

    // Getter method for the holder.
    public Type getHolder() {
        return holder; // Returns the holder of the book.
    }

    // Getter method for the dueDate.
    public GregorianCalendar getDueDate() {
        return dueDate; // Returns the due date of the book.
    }

    // Method to check out the book.
    public void checkOut(Type holder, GregorianCalendar dueDate) {
        this.holder = holder; // Sets the holder to the given holder.
        this.dueDate = dueDate; // Sets the due date to the given due date.
    }

    // Method to check in the book.
    public void checkIn() {
        this.holder = null; // Resets the holder to null, indicating no one currently has the book.
        this.dueDate = null; // Resets the due date to null, as the book is no longer checked out.
    }
}
