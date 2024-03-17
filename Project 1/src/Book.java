public class Book implements IBook{
	private String title;
	private String author;
	private String ISBN13;
	
	public Book() {
		this.title = null;
		this.author = null;
		this.ISBN13 = null;
	}
	
	public Book(String title, String author, String ISBN13) {
		this.title = title;
		this.author = author;
		this.ISBN13 = ISBN13;
	}
	
	
	/**
     * Returns the title of the book.
     * @return title of the book
     */
    public String getTitle() {
		return title;
	}

    /**
     * Returns a string that contains the authors of the book
     * as a single string with different authors separated by /.
     * @return author names as single string
     */
    public String getAuthors() {
    	return author;
    }

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     * @return ISBN number of book
     */
    public String getISBN13() {
    	return ISBN13;
    }
}
