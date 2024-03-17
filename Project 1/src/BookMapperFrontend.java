import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class that contains the code for interacting with the user
 * @author xingzhencai
 *
 */
public class BookMapperFrontend implements IBookMapperFrontend{
	
	Scanner sc;
	IBookMapperBackend backend;
	IISBNValidator validator;
	
	
	/**
	 * The constructor. It takes the Scanner that will read user input as
     * a parameter as well as the backend and the ISBNnalidator.
     * 
	 * @param userInputScanner  The scanner of user input
	 * @param backend  the backend object for access of backend method
	 * @param validator    the validator object for access of the method to check ISBN validation
	 */
	public BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator) {
		//assignment
		this.sc = userInputScanner;
		this.backend = backend;
		this.validator = validator;
	}
	
	

	/**
	 * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
	 */
	@Override
	public void runCommandLoop() {
		//welcome message
		System.out.println("Welcome to the Book Mapper Application!\n"
								+ "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"
								+ " ");
		while(true) {
			displayMainMenu();
			String input = sc.next();
			if(input != null && input.strip().equals("1")) isbnLookup();
			else if(input != null && input.strip().equals("2")) titleSearch();
			else if(input != null && input.strip().equals("3")) {
				String filter = "none";
				if (backend.getAuthorFilter() != null) filter = backend.getAuthorFilter();
				
				System.out.println("You are in the Set Author Filter Menu:\n"
						+ "          Author name must currently contain: "+ filter);
				System.out.print("          Enter a new string for author names to contain (empty for any): ");
				sc.nextLine();
				String author = sc.nextLine();
				author = author.strip();
				if(author != null && author != "") backend.setAuthorFilter(author);
				else backend.resetAuthorFilter(); //set author filter
			}
			//terminate
			else if(input != null && input.strip().equals("4")) {
				System.out.print("Goodbye!");
				sc.close();
				return;
			}
		}
	}

	/**
	 * prints command options to System.out
	 */
	@Override
	public void displayMainMenu() {
		String menu = "You are in the Main Menu:\n"
				+ "          1) Lookup ISBN\n"
				+ "          2) Search by Title Word\n"
				+ "          3) Set Author Name Filter\n"
				+ "          4) Exit Application";
		System.out.println(menu);
	}

	
	@Override
	/**
	 * Prints out all the book info in the input list
	 */
	public void displayBooks(List<IBook> books) {
		if(books == null) {
			return;
		}
		for(int i=0; i<books.size(); i++) {
			if(books.get(i) == null) {
				continue;
			}
			System.out.println( (i+1) +". \"" + books.get(i).getTitle() + "\"" 
					+ " by " + books.get(i).getAuthors() 
					+ ", ISBN: " + books.get(i).getISBN13()+"\n"
					+" ");
		}
	}

	@Override
	/**
	 * searching book by ISBN and print the info of book
	 */
	public void isbnLookup()  {
		System.out.print("You are in the Lookup ISBN Menu:\n"
				+ "          Enter ISBN to look up:");
		String input = sc.next();
		input = input.strip();
		
		if (input.length()!= 13) {
			System.out.println("Invalid ISBN number");
			return;
		}
		if(!validator.validate(input)) {
			System.out.println("Invalid ISBN number");
			return;

		}

		List<IBook> list = new ArrayList<IBook>();
		list.add(backend.getByISBN(input));
		displayBooks(list);
	}

	@Override
	/**
	 * search books by key word and print out the books info
	 */
	public void titleSearch() {
		System.out.print("You are in the Search for Title Word Menu:\n"
				+ "          Enter a word to search for in book titles (empty for all books): ");
		sc.nextLine();
		String word = sc.nextLine();

		List<IBook> list = new ArrayList<IBook>();
		list = backend.searchByTitleWord(word.strip());
		if(backend.getAuthorFilter() == null) {
			System.out.println("Matches (any author) " + list.size()+" of " + backend.getNumberOfBooks());
		}else {
			System.out.println("Matches (author filter: "+backend.getAuthorFilter()+") "+list.size()+" of "+backend.getNumberOfBooks());
		}
		displayBooks(list);
		if(backend.getAuthorFilter() == null) {
			System.out.println("Matches (any author) " + list.size()+" of " + backend.getNumberOfBooks());
		}else {
			System.out.println("Matches (author filter: "+backend.getAuthorFilter()+") "+list.size()+" of "+backend.getNumberOfBooks());
		}
	}

}
