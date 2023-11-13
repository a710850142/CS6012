package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Comparator;

/**
 * Class representation of a library (a collection of library books).
 *
 */
public class Library<Type> {

    // The collection of books in the library, parameterized by Type
    private ArrayList<LibraryBook<Type>> library;

    // Constructor initializes the library collection
    public Library() {
        library = new ArrayList<LibraryBook<Type>>();
    }

    /**
     * Add the specified book to the library, assume no duplicates.
     *
     * @param isbn
     *          -- ISBN of the book to be added
     * @param author
     *          -- author of the book to be added
     * @param title
     *          -- title of the book to be added
     */
    public void add(long isbn, String author, String title) {
        library.add(new LibraryBook<Type>(isbn, author, title));
    }

    /**
     * Add the list of library books to the library, assume no duplicates.
     *
     * @param list
     *          -- list of library books to be added
     */
    public void addAll(ArrayList<LibraryBook<Type>> list) {
        library.addAll(list);
    }

    /**
     * Add books specified by the input file. One book per line with ISBN, author,
     * and title separated by tabs.
     *
     * If file does not exist or format is violated, do nothing.
     *
     * @param filename
     */
    public void addAll(String filename) {
        ArrayList<LibraryBook<Type>> toBeAdded = new ArrayList<LibraryBook<Type>>();

        try (Scanner fileIn = new Scanner(new File(filename))) {

            int lineNum = 1;

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();

                try (Scanner lineIn = new Scanner(line)) {
                    lineIn.useDelimiter("\\t");

                    if (!lineIn.hasNextLong()) {
                        throw new ParseException("ISBN", lineNum);
                    }
                    long isbn = lineIn.nextLong();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Author", lineNum);
                    }
                    String author = lineIn.next();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Title", lineNum);
                    }
                    String title = lineIn.next();
                    toBeAdded.add(new LibraryBook(isbn, author, title));
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + " Nothing added to the library.");
            return;
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
                    + ". Nothing added to the library.");
            return;
        }

        library.addAll(toBeAdded);
    }

    /**
     * Returns the holder of the library book with the specified ISBN.
     *
     * If no book with the specified ISBN is in the library, returns null.
     *
     * @param isbn
     *          -- ISBN of the book to be looked up
     */
    public Type lookup(long isbn) {
        // Iterate through all books in the library
        for (LibraryBook<Type> book : library) {
            // Check if the current book's ISBN matches the one we're looking for
            if (book.getIsbn() == isbn) {
                // Return the holder of the book
                return book.getHolder();
            }
        }
        // If no matching book is found, return null
        return null;
    }

    /**
     * Returns the list of library books checked out to the specified holder.
     *
     * If the specified holder has no books checked out, returns an empty list.
     *
     * @param holder
     *          -- holder whose checked out books are returned
     */
    public ArrayList<LibraryBook<Type>> lookup(Type holder) {
        // Create a new list to store books checked out to the holder
        ArrayList<LibraryBook<Type>> booksByHolder = new ArrayList<>();
        // Iterate through all books in the library
        for (LibraryBook<Type> book : library) {
            // Check if the current book's holder matches the one we're looking for
            if (holder.equals(book.getHolder())) {
                // Add the book to the list of books by this holder
                booksByHolder.add(book);
            }
        }
        // Return the list of books checked out by the holder
        return booksByHolder;
    }

    /**
     * Sets the holder and due date of the library book with the specified ISBN.
     *
     * If no book with the specified ISBN is in the library, returns false.
     *
     * If the book with the specified ISBN is already checked out, returns false.
     *
     * Otherwise, returns true.
     *
     * @param isbn
     *          -- ISBN of the library book to be checked out
     * @param holder
     *          -- new holder of the library book
     * @param month
     *          -- month of the new due date of the library book
     * @param day
     *          -- day of the new due date of the library book
     * @param year
     *          -- year of the new due date of the library book
     *
     */
    public boolean checkout(long isbn, Type holder, int month, int day, int year) {
        // Iterate through all books in the library
        for (LibraryBook<Type> book : library) {
            // Look for a book with the given ISBN that is not currently checked out
            if (book.getIsbn() == isbn && book.getHolder() == null) {
                // Check the book out to the holder with the provided due date
                book.checkOut(holder, new GregorianCalendar(year, month, day));
                // Return true indicating successful checkout
                return true;
            }
        }
        // If no such book is found or if it's already checked out, return false
        return false;
    }

    /**
     * Unsets the holder and due date of the library book.
     *
     * If no book with the specified ISBN is in the library, returns false.
     *
     * If the book with the specified ISBN is already checked in, returns false.
     *
     * Otherwise, returns true.
     *
     * @param isbn
     *          -- ISBN of the library book to be checked in
     */
    public boolean checkin(long isbn) {
        // Iterate through all books in the library
        for (LibraryBook book : library) {
            // Look for the book with the given ISBN that is currently checked out
            if (book.getIsbn() == isbn && book.getHolder() != null) {
                // Check the book in by clearing its holder and due date
                book.checkIn();
                // Return true indicating successful check-in
                return true;
            }
        }
        // If no such book is found or if it's not checked out, return false
        return false;
    }

    /**
     * Unsets the holder and due date for all library books checked out be the
     * specified holder.
     *
     * If no books with the specified holder are in the library, returns false;
     *
     * Otherwise, returns true.
     *
     * @param holder
     *          -- holder of the library books to be checked in
     */
    public boolean checkin(Type holder) {
        // Flag to indicate if we found and checked in any books
        boolean found = false;
        // Iterate through all books in the library
        for (LibraryBook<Type> book : library) {
            // Check if the book is checked out to the specified holder
            if (holder.equals(book.getHolder())) {
                // Check the book in
                book.checkIn();
                // Set the flag to true since we've found at least one book
                found = true;
            }
        }
        // Return true if any books were checked in, false otherwise
        return found;
    }

    /**
     * Returns the list of library books, sorted by ISBN (smallest ISBN
     first).
     */
    public ArrayList<LibraryBook<Type>> getInventoryList() {
        // Create a copy of the library inventory
        ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
        // Add all books to the copy
        libraryCopy.addAll(library);
        // Instantiate a comparator to sort by ISBN
        OrderByIsbn comparator = new OrderByIsbn();
        // Sort the copied list using the comparator
        sort(libraryCopy, comparator);
        // Return the sorted list
        return libraryCopy;
    }

    /**
     * Returns the list of library books, sorted by author
     */
    public ArrayList<LibraryBook<Type>> getOrderedByAuthor() {
        // Create a copy of the library inventory
        ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
        // Add all books to the copy
        libraryCopy.addAll(library);
        // Instantiate a comparator to sort by author (and title as a tie-breaker)
        OrderByAuthor comparator = new OrderByAuthor();
        // Sort the copied list using the comparator
        sort(libraryCopy, comparator);
        // Return the sorted list
        return libraryCopy;
    }

    /**
     * Returns the list of library books whose due date is older than the
     input
     * date. The list is sorted by date (oldest first).
     *
     * If no library books are overdue, returns an empty list.
     */
    public ArrayList<LibraryBook<Type>> getOverdueList(int month, int day, int year) {
        // Create a list to hold overdue books
        ArrayList<LibraryBook<Type>> overdueBooks = new ArrayList<LibraryBook<Type>>();
        // Create a calendar object for comparison with due dates
        GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
        // Iterate through the library inventory
        for (LibraryBook<Type> book : library) {
            // Check if a book is overdue by comparing its due date with the input date
            if (book.getDueDate() != null && book.getDueDate().compareTo(dueDate) < 0) {
                // Add the overdue book to the list
                overdueBooks.add(book);
            }
        }
        // Instantiate a comparator to sort by due date
        OrderByDueDate comparator = new OrderByDueDate();
        // Sort the list of overdue books using the comparator
        sort(overdueBooks, comparator);
        // Return the sorted list of overdue books
        return overdueBooks;
    }

    /**
     * Performs a SELECTION SORT on the input ArrayList.
     * 1. Find the smallest item in the list.
     * 2. Swap the smallest item with the first item in the list.
     * 3. Now let the list be the remaining unsorted portion
     * (second item to Nth item) and repeat steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
        // Iterate over the entire list
        for (int i = 0; i < list.size() - 1; i++) {
            // Assume the first unsorted element is the smallest
            int minIndex = i;
            // Iterate through the unsorted portion of the list
            for (int j = i + 1; j < list.size(); j++) {
                // Find the true smallest element according to the comparator
                if (c.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            // Swap the smallest unsorted element with the first unsorted element
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Comparator that defines an ordering among library books using the
     ISBN.
     */
    protected class OrderByIsbn implements
            Comparator<LibraryBook<Type>> {
        /**
         * Returns a negative value if lhs is smaller than rhs. Returns a positive
         * value if lhs is larger than rhs. Returns 0 if lhs 	and rhs are equal.
         */
        public int compare(LibraryBook<Type> lhs,
                           LibraryBook<Type> rhs) {
            // Cast to int for comparison to avoid overflow issues
            return (int) (lhs.getIsbn() - rhs.getIsbn());
        }
    }

    /**
     * Comparator that defines an ordering among library books using the
     author, and book title as a tie-breaker.
     */
    protected class OrderByAuthor implements Comparator<LibraryBook<Type>> {
        public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs) {
            // First compare by author
            int authorCompare = lhs.getAuthor().compareTo(rhs.getAuthor());
            // If authors are the same, then compare titles
            return (authorCompare != 0) ? authorCompare : lhs.getTitle().compareTo(rhs.getTitle());
        }
    }

    /**
     * Comparator that defines an ordering among library books using the
     due date.
     */
    protected class OrderByDueDate implements Comparator<LibraryBook<Type>> {
        public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs) {
            // Assuming that the due dates are not null, as null due dates should not be in the overdue list
            return lhs.getDueDate().compareTo(rhs.getDueDate());
        }
    }
}

