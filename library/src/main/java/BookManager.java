import java.util.ArrayList;
import java.util.Iterator;

public class BookManager {
    private ArrayList<Book> books;

    BookManager() {
        books = new ArrayList<Book>();
    }

    public ArrayList<Book> addBook(Book newBook) {
        System.out.println(books.size() + " : tyle jest przed dodaniem");
        books.add(newBook);
        System.out.println(books.size() + " : tyle jest po dodaniem");
        return books;
    }

    public boolean removeBook(String bookId) {
       return books.removeIf(book -> {
           System.out.println(book.getId() + " to jest z loopa, to jest przekazane z args " + bookId);

           return book.getId().equals(bookId);
       });
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> newBooks) {
        System.out.println("Setted");
        books = newBooks;
    }

    public Book getBook(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }

        return null;
    }
}
