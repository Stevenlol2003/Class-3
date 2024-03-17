/**
 * Placeholder class for Book.
 */
public class Book1 implements IBook {

	/**
	 * Returns the title of the book.
	 * 
	 * @return title of the book
	 */
	@Override
	public String getTitle() {
		return "UWlife";
	}

	/**
	 * Returns a string that contains the authors of the book as a single string
	 * with different authors separated by /.
	 * 
	 * @return author names as single string
	 */
	@Override
	public String getAuthors() {
		return "Marin";
	}

	/**
	 * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
	 * 
	 * @return ISBN number of book
	 */
	@Override
	public String getISBN13() {
		return "1234567891234";
	}
}