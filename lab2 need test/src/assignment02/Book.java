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
  public boolean equals(Object other) {

//    if (this == other) { //check if other is a reference to the same object
//      return true;
//    }
//    if(other == null || this.getClass() != other.getClass()) { //check other is not null and is of the same class as this
//      return false;
//    }
    if (!(other instanceof Book)) {
      return false;
    }
    Book book = (Book) other; // cast other to Book and then compare
    return this.isbn == book.isbn && this.author.equals(book.author) && this.title.equals(book.title);
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
