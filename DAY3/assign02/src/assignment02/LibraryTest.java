package assignment02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void testEmpty() {
        Library<String> lib = new Library<>();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBook<String>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testNonEmpty() {

        Library<String> lib = new Library<>();
        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        assertNull(lib.lookup(9780330351690L)); //not checked out
        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        ArrayList<LibraryBook<String>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 1);
        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9780330351690L);
        assertTrue(res);
        res = lib.checkin("Jane Doe");
        assertFalse(res);
    }


    // Test method to check if checking in by ISBN and holder works as expected.
    @Test
    public void testCheckinByISBNAndHolder() {
        Library<String> lib = new Library<>(); // Create a new Library object.
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat"); // Add a book to the library.

        // Check out the book to "John Doe" and assert that it was successful.
        lib.checkout(9780374292799L, "John Doe", 1, 1, 2008);
        assertTrue(lib.checkin(9780374292799L)); // Check the book back in using its ISBN.
        assertNull(lib.lookup(9780374292799L)); // Assert that the book is no longer checked out.

        // Check the book out again to "John Doe" and assert that it was successful.
        lib.checkout(9780374292799L, "John Doe", 1, 1, 2008);
        assertTrue(lib.checkin("John Doe")); // Check all books checked out to "John Doe" back in.
        assertNull(lib.lookup(9780374292799L)); // Assert that the book is no longer checked out.
    }

    // Test method to verify the behavior of invalid checkouts and check-ins.
    @Test
    public void testInvalidCheckoutAndCheckin() {
        Library<String> lib = new Library<>(); // Create a new Library object.
        assertFalse(lib.checkout(9780374292799L, "John Doe", 1, 1, 2008)); // Attempt to check out a book that doesn't exist should fail.
        assertFalse(lib.checkin(9780374292799L)); // Attempt to check in a book that isn't in the library should fail.
    }

    // Test method to verify the functionality of a large library.
    @Test
    public void testLargeLibrary(){
        Library<String> lib = new Library<>(); // Create a new Library object.
        lib.addAll("Mushroom_Publishing.txt"); // Add a list of books from a file to the library.

        // Check out some books to different holders and assert the process was successful.
        lib.checkout(9781843190004L, "Hailey", 12, 25, 2023);
        lib.checkout(9781843190011L, "Cassie", 11, 21, 2023);

        // Verify the lookup method returns the correct holder for each ISBN.
        assertEquals("Hailey", lib.lookup(9781843190004L));
        assertEquals("Cassie", lib.lookup(9781843190011L));

        // Verify that books checked out to "Hailey" and "Cassie" are correctly returned by the lookup method.
        assertEquals(1, lib.lookup("Hailey").size());
        assertEquals(1, lib.lookup("Cassie").size());

        // Verify that checking in a book by ISBN works and the book is no longer checked out.
        assertTrue(lib.checkin(9781843190004L));
        assertNull(lib.lookup(9781843190004L));

        // Verify that checking in all books by a specific holder works and the holder has no more books checked out.
        assertTrue(lib.checkin("Cassie"));
        assertEquals(0, lib.lookup("Cassie").size());
    }


    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));

        var booksCheckedOut1 = lib.lookup(patron1);
        assertEquals(booksCheckedOut1.size(), 2);
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(),patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(),new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }

    @Test
    public void phoneNumberTest(){
        // test a library that uses phone numbers (PhoneNumber) to id patrons
        var lib = new Library<PhoneNumber>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        ArrayList<LibraryBook<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        assertEquals(booksCheckedOut2.size(), 2);
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut2.get(0).getHolder(),patron2);
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }
    @Test
    public void testSortingByISBN() {
        // This test ensures that the getInventoryList method returns books sorted by ISBN.
        Library<String> lib = new Library<>();
        // Adding books to the library in no particular order.
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");

        // Calling the method to get the inventory list sorted by ISBN.
        ArrayList<LibraryBook<String>> sortedBooks = lib.getInventoryList();

        // Asserting that the books are indeed sorted by their ISBN numbers.
        assertEquals(9780330351690L, sortedBooks.get(0).getIsbn());
        assertEquals(9780374292799L, sortedBooks.get(1).getIsbn());
        assertEquals(9780446580342L, sortedBooks.get(2).getIsbn());
    }

    @Test
    public void testSortingByAuthor() {
        // This test verifies that the getOrderedByAuthor method sorts books by their authors.
        Library<String> lib = new Library<>();
        // Adding books with various authors to the library.
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");

        // Retrieving the list of books sorted by author.
        ArrayList<LibraryBook<String>> sortedBooks = lib.getOrderedByAuthor();

        // Checking the order of the authors in the sorted list.
        assertEquals("David Baldacci", sortedBooks.get(0).getAuthor());
        assertEquals("Jon Krakauer", sortedBooks.get(1).getAuthor());
        assertEquals("Thomas L. Friedman", sortedBooks.get(2).getAuthor());
    }

    @Test
    public void testSortingByDueDate() {
        // This test checks that the getOverdueList method returns books sorted by due date.
        Library<String> lib = new Library<>();
        // Adding books and setting their due dates by checking them out.
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        lib.checkout(9780446580342L, "John Doe", 1, 1, 2009);
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.checkout(9780374292799L, "Jane Doe", 1, 1, 2008);
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.checkout(9780330351690L, "Jim Beam", 1, 1, 2007);

        // Getting the list of overdue books as of a specified date.
        ArrayList<LibraryBook<String>> overdueBooks = lib.getOverdueList(1, 1, 2010);

        // The books should be sorted by their due date, from the oldest to the newest.
        assertEquals("Into the Wild", overdueBooks.get(0).getTitle());
        assertEquals("The World is Flat", overdueBooks.get(1).getTitle());
        assertEquals("Simple Genius", overdueBooks.get(2).getTitle());
    }

    @Test
    public void testCheckoutAndCheckin() {
        // This test case verifies the functionality of checking out and checking in books.
        Library<String> lib = new Library<>();
        // Adding a book to the library.
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        // Checking out the book to "John Doe" and verifying the process was successful.
        assertTrue(lib.checkout(9780446580342L, "John Doe", 1, 1, 2009));
        // Verifying that the book is now associated with "John Doe".
        assertEquals("John Doe", lib.lookup(9780446580342L));

        // Checking the book back in using its ISBN and verifying the book is no longer checked out.
        assertTrue(lib.checkin(9780446580342L));
        assertNull(lib.lookup(9780446580342L));

        // Checking out the book again to a different holder, "Jane Doe".
        assertTrue(lib.checkout(9780446580342L, "Jane Doe", 1, 1, 2008));
        // Checking all books back in that are held by "Jane Doe".
        assertTrue(lib.checkin("Jane Doe"));
        // Verifying that "Jane Doe" no longer holds any books.
        assertNull(lib.lookup(9780446580342L));
    }

}
