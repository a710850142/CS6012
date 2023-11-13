package assignment02;
import java.lang.reflect.GenericArrayType;
import java.util.GregorianCalendar;

public class LibraryBook<Type> extends Book{
    private Type holder; // Use the generic Type instead of String
    private GregorianCalendar dueDate;
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title); //super must be the first line in the constructor
        this.holder = null; // initialize holder
        this.dueDate = null; // initialize dueDate
    }

    public Type getHolder(){return this.holder;}

    public GregorianCalendar getDueDate(){return this.dueDate;}

    public void checkIn(){
        this.holder = null;
        this.dueDate = null;
    }

    public void checkOut(Type holder, GregorianCalendar dueDate){
        this.holder = holder;
        this.dueDate = dueDate;
    }
}
