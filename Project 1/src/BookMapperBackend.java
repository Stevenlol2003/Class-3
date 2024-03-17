import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Instances of this interface implement the search and filter functionality of
 * the Book Mapper app using hash maps.
 */
public class BookMapperBackend extends IterableHashtableMap<String, IBook> implements IBookMapperBackend {

	// author filter
	protected String authorFilter = null;

	// the database which contains all the book stored
	protected HashtableMap<String, IBook> database;

	boolean isPlaceholder;

	// constructor for placeholder
	public BookMapperBackend(boolean isPlaceholder) {
		this.database = new HashtableMap<String, IBook>();
		this.isPlaceholder = true;
	}

	// constructor for integrated test
	public BookMapperBackend() {
		this.database = new IterableHashtableMap<String, IBook>();
		this.isPlaceholder = false;
	}
	
	/**
	 * Adds a new book to the backend's database and is stored in a hash table
	 * internally.
	 * 
	 * @param book the book to add
	 */
	@Override
	public void addBook(IBook book) {

		if (book != null) {
			database.put(book.getISBN13(), book);
		}
	}

	/**
	 * Returns the number of books stored in the backend's database.
	 * 
	 * @return the number of books
	 */
	@Override
	public int getNumberOfBooks() {
		
		return database.size();
		
	}

	/**
	 * This method can be used to set a filter for the author names contained in the
	 * search results. A book is only returned as a result for a search by title, it
	 * is also contains the string filterBy in the names of its authors.
	 * 
	 * @param filterBy the string that the book's author names must contain
	 */
	@Override
	public void setAuthorFilter(String filterBy) {

		if (filterBy != null) {
			authorFilter = filterBy;
		}

	}

	/**
	 * Returns the string used as the author filter, null if no author filter is
	 * currently set.
	 * 
	 * @return the string used as the author filter, or null if none is set
	 */
	@Override
	public String getAuthorFilter() {
		if (authorFilter == null) {
			return null;
		} else {
			return authorFilter;
		}
	}

	/**
	 * Resets the author filter to null (no filter).
	 */
	@Override
	public void resetAuthorFilter() {
		authorFilter = null;
	}
	
	public void printprint() {
		
		// list to return the book list
		List<IBook> searchedList = new ArrayList<>();
		
		// Get the iterator
		Iterator<IBook> iterableDataset = ((IterableHashtableMap<String, IBook>) database).iterator();
		
		if (authorFilter != null) { // filter by author first

			List<IBook> searchedList1 = new ArrayList<>();
			IBook current = iterableDataset.next(); // current book we look at
			int j = 0;
			while (current != null) {
				// case insensitive
				j ++;
				System.out.println(j + current.getAuthors());
			}

	}
	}

	/**
	 * Search through all the books in the title base and return books whose title
	 * contains the string word (and that satisfies the author filter, if an author
	 * filter is set).
	 * 
	 * @param word word that must be contained in a book's title in result set
	 * @return list of books found
	 */
	@Override
	public List<IBook> searchByTitleWord(String word) {
		
		// list to return the book list
		List<IBook> searchedList = new ArrayList<>();
		
		// Get the iterator
		Iterator<IBook> iterableDataset;

		// test for placeholder
		if (this.isPlaceholder) {
			// the list which was searched by author name and book name
			IBook book1 = new Book1();
			IBook book2 = new Book1();
			List<IBook> list = new ArrayList<IBook>();
			list.add(book1);
			list.add(book2);
			
			// Get the iterator
			iterableDataset = list.iterator();
		}else{// test for AD method
			// Get the iterator
			iterableDataset = ((IterableHashtableMap<String, IBook>) database).iterator();
		}
		
		
		if (authorFilter != null) { // filter by author first

			List<IBook> searchedList1 = new ArrayList<>();
			IBook current = iterableDataset.next(); // current book we look at
			//int j = 0;
			
			while (iterableDataset.hasNext()) {
				// case insensitive
				//j ++;
				//System.out.println(j + current.getAuthors());
				if (current.getAuthors().toLowerCase().contains(authorFilter.toLowerCase())) {
					searchedList1.add(current);

				}

				try {
					current = iterableDataset.next();
				} catch (Exception e) {
					break;
				}

			}
			
			

			// The list was filtered by author and now filter it by title
			for (int i = 0; i < searchedList1.size(); i++) {
				// case insensitive
				if (searchedList1.get(i).getTitle().toLowerCase().contains(word.toLowerCase())) {
					searchedList.add(searchedList1.get(i));
				}
				else {
					//if(word.equals("")) {
					//	return searchedList1;
						
					//}
				}
			}

		} else { // no author filter
			IBook current = iterableDataset.next();
	

			// The list was filtered by author and now filter it by title
			while (iterableDataset.hasNext()) {
				// case insensitive
			
				if (word.equals("")) {
					searchedList.add(current);
				}
				else if (current.getTitle().toLowerCase().contains(word.toLowerCase())) {
					searchedList.add(current);

				}
				try {
					current = iterableDataset.next();
				} catch (Exception e) {

				}


			}

		
		}
		return searchedList;

	}

	/**
	 * Return the book uniquely identified by the ISBN, or null if ISBN is not
	 * present in the dataset.
	 * 
	 * @param ISBN the book's ISBN number
	 * @return the book identified by the ISBN, or null if ISBN not in database
	 */
	@Override
	public IBook getByISBN(String ISBN) {
		if (database.containsKey(ISBN)) {
			return (IBook) database.get(ISBN);
		}

		return null;
	}

}
