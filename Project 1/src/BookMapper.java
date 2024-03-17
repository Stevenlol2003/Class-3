import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper {

    public static void main(String[] args) throws FileNotFoundException {
	IBookLoader bookLoader = new BookLoader();
        // load the books from the data file
        List<IBook> bookList = bookLoader.loadBooks("books.csv");
        
        
        int i = 1;
        //for (IBook book : bookList) System.out.println((i++) + book.getTitle());
        // instantiate the backend
        IBookMapperBackend backend = new BookMapperBackend();
        // add all the books to the backend
        
        
        int a=0;
        for (IBook book2 : bookList) {
        	backend.addBook(book2);
        	
        }
        
        // ((BookMapperBackend) backend).printprint();
        
        
        // System.out.println(backend.getNumberOfBooks());
        backend.toString();
        // instantiate the isbn validator
        IISBNValidator isbnValidator = new ISBNValidator();
        
        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        // start the input loop of the front end
        frontend.runCommandLoop();
        
    }
    
}
