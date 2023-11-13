package assignment02;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 *
 * Note that ISBNs are unique.
 *
 */
public class Book {

    private long isbn;

    private String author;

    private String title;

    public Book(long isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * @return the ISBN
     */
    public long getIsbn() {
        return this.isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Two books are considered equal if they have the same ISBN, author, and title.
     *
     * @param other
     *          -- the object begin compared with "this"
     * @return true if "other" is a Book and is equal to "this", false otherwise
     */
    @Override
    public boolean equals(Object other) {
        // Check if the other object is an instance of Book
        if (other instanceof Book) {
            // Cast the other object to a Book type
            Book otherBook = (Book) other;
            // Check if the ISBN numbers are the same for both books
            // And check if the author and title are also equal
            // The equals method is used for author and title to check for content equality rather than reference equality
            return this.isbn == otherBook.isbn &&
                    this.author.equals(otherBook.author) &&
                    this.title.equals(otherBook.title);
        }
        // If the other object is not an instance of Book, then return false
        // because they cannot be equivalent
        return false;
    }


    /**
     * Returns a string representation of the book.
     */
    public String toString() {
        return isbn + ", " + author + ", \"" + title + "\"";
    }

    @Override
    public int hashCode() {
        return (int) isbn + author.hashCode() + title.hashCode();
    }
}