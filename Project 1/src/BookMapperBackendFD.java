import java.util.ArrayList;
import java.util.List;

public class BookMapperBackendFD implements IBookMapperBackend{

	String filter = null;
	@Override
	public void addBook(IBook book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumberOfBooks() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public void setAuthorFilter(String filterBy) {
		filter = filterBy;
		return;
		
	}

	@Override
	public String getAuthorFilter() {
		// TODO Auto-generated method stub
		return filter;
	}

	@Override
	public void resetAuthorFilter() {
		filter = null;
		
	}

	@Override
	public List<IBook> searchByTitleWord(String word) {
		// TODO Auto-generated method stub
		BookFD b = new BookFD();
		BookFD c = new BookFD();
		List<IBook> l = new ArrayList<IBook>();
		
		l.add(b);
		l.add(c);
		return l;
	}

	@Override
	public IBook getByISBN(String ISBN) {
		BookFD b = new BookFD();
		return b;
	}


}
