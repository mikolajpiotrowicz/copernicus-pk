import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooksRepository {
    private BookManager currentBooks;
    private BookManager availableBooks;
    private Application application;

    BooksRepository(Application container) {
        currentBooks = new BookManager();
        availableBooks = new BookManager();
        application = container;
    }

    public void rentBook(String id) {
        currentBooks.addBook(availableBooks.getBook(id));
        availableBooks.removeBook(id);
        application.frame.repaint();
    }

    public void returnBook(String id) {
        availableBooks.addBook(currentBooks.getBook(id));
        currentBooks.removeBook(id);
        application.frame.repaint();
    }
    public void fetchAvailableBooks() throws IOException {
        setAvailableBooks(application.http.fetchAvailableBooks());
    }
    public void fetchCurrentBooks() throws IOException {
        setCurrentBooks(application.http.fetchCurrentBooks());
    }
    public void setCurrentBooks(ArrayList<Book> books) {
       currentBooks.setBooks(books);
    }

    public void setAvailableBooks(ArrayList<Book> books) {
        availableBooks.setBooks(books);
    }

    public List<Book> getCurrentBooks() {
        return currentBooks.getBooks();
    }

    public ArrayList<Book> getAvailableBooks() {
        return availableBooks.getBooks();
    }
    public String[] getAvailableBookDisplays() {
        ArrayList<String> books = new ArrayList<String>();
        if(availableBooks.getBooks() == null) {
            return null;
        }
        availableBooks.getBooks().forEach(el -> {
            if(el != null) {
                books.add(el.getDisplayName());

            }
        });
        return books.toArray(new String[0]);
    }
    public String[] getCurrentBookDisplays() {
        ArrayList<String> books = new ArrayList<String>();
        currentBooks.getBooks().forEach(el -> {
            if(el == null) {
                return;
            }
            books.add(el.getDisplayName());
        });

        return books.toArray(new String[0]);
    }
}
