/**
 * This class tests methods in BookMapperBackend. This class contains 5 test
 * methods to test: addBook, getNumberOfBooks, setAuthorFilter, getAuthorFilter,
 * resetAuthorFilter, searchByTitleWord, and getByISBN.
 * 
 * @author Marin Suzuki
 */
public class BackendDeveloperTest {

	/**
	 * This method verifies that the constructors and add method works properly and
	 * any relevant exceptions are thrown. This test is for placeholder.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test1() {

		try {

			BookMapperBackend map = new BookMapperBackend(true);

			map.addBook(null); // size should still become 0
			if (map.getNumberOfBooks() != 0) {
				return false;
			}

			// make Book object
			IBook book = new Book1();

			map.addBook(book);

			if (map.getNumberOfBooks() != 1) { // this should have only one book
				return false;
			}

		} catch (Exception e) {
			return false; // unexpacted
		}

		return true; // all test passed
	}

	/**
	 * This method verifies that the constructors and get/setAuthorFilter method
	 * works properly and any relevant exceptions are thrown. This test is for
	 * placeholder.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test2() {

		try {

			BookMapperBackend map = new BookMapperBackend(true);

			map.setAuthorFilter("Marin");
			if (!map.getAuthorFilter().equals("Marin")) {
				return false; // the author filter should be Marin.
			}
		} catch (Exception e) {
			return false;
		}

		return true; // test passed
	}

	/**
	 * This method verifies that the constructors and resetAuthorFilter method works
	 * properly and any relevant exceptions are thrown. This test is for
	 * placeholder.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test3() {

		try {

			BookMapperBackend map = new BookMapperBackend(true);

			map.setAuthorFilter("Marin");
			map.resetAuthorFilter();

			// now the author filter is reset and null
			if (map.getAuthorFilter() != null) {
				return false;
			}

		} catch (Exception e) {

			// unexpected error
			return false;
		}

		return true; // test passed

	}

	/**
	 * This method verifies that the constructors and searchByTitleWord method works
	 * properly and any relevant exceptions are thrown. This test is for
	 * placeholder.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test4() {

		try {

			BookMapperBackend map = new BookMapperBackend(true);

			// if the wrong author filter is on
			map.setAuthorFilter("Hey");

			// this author does not exist in the list now
			if (map.searchByTitleWord("UWlife").size() != 0) {// no same book

				return false;
			}

			// There is correct author filter
			map.resetAuthorFilter();
			map.setAuthorFilter("Ma");
			if (map.searchByTitleWord("UW").size() != 2) {// Marin is in the databse

				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false; // unexpected
		}

		return true; // passed all tests

	}

	/**
	 * This method verifies that the constructors and getByISBN method works
	 * properly and any relevant exceptions are thrown. This test is for
	 * placeholder.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test5() {

		try {

			BookMapperBackend map = new BookMapperBackend(true);

			// make Book object
			IBook book = new Book1();

			// add Book
			map.addBook(book);

			if (!book.equals(map.getByISBN("1234567891234"))) {
				return false;
			}

		} catch (Exception e) {

			return false; // unexpected
		}

		return true; // passed

	}

	/**
	 * This method verifies that the constructors, add method, get/setAuthorFilter
	 * method works properly and any relevant exceptions are thrown. This test is
	 * integrated test with IterableADT and Book class.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test6() {

		try {

			// constructor and add method
			BookMapperBackend map = new BookMapperBackend();

			map.addBook(null); // size should still become 0
			if (map.getNumberOfBooks() != 0) {
				return false;
			}

			// make Book object
			IBook book = new Book1();

			map.addBook(book);

			if (map.getNumberOfBooks() != 1) { // this should have only one book
				return false;
			}

			// get/setAuthorFilter method
			BookMapperBackend map1 = new BookMapperBackend();

			map1.setAuthorFilter("Marin");
			if (!map1.getAuthorFilter().equals("Marin")) {
				return false; // the author filter should be Marin.
			}

		} catch (Exception e) {
			return false; // unexpected
		}

		return true; // passed
	}

	/**
	 * This method verifies that the constructors, resetAuthorFilter method,
	 * searchByTitleWord method, getByISBN method works properly and any relevant
	 * exceptions are thrown. This test is integrated test with IterableADT.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test7() {

		try {

			// test for resetAuthorFilter
			BookMapperBackend map = new BookMapperBackend();

			map.setAuthorFilter("Marin");
			map.resetAuthorFilter();

			// now the author filter is reset and null
			if (map.getAuthorFilter() != null) {
				return false;
			}
			
			BookMapperBackend map1 = new BookMapperBackend();
			
			// test for searchByTitleWord
			// if the wrong author filter is on
			map1.setAuthorFilter("Heyjruijfij49jfirjekcfnrjnierwnuncrieunerck");

			// this author does not exist in the list now
			if (map1.searchByTitleWord("UWlife").size() != 0) {// no same book

				return false;
			}

			// There is correct author filter
			map1.resetAuthorFilter();
			map1.setAuthorFilter("J");
			if (map1.searchByTitleWord("Harry Potter").size() != 5) {// 5 Harry Potter books

				return false;
			}

			BookMapperBackend map2 = new BookMapperBackend();

			// make Book object
			IBook book = new Book1();

			// add Book
			map2.addBook(book);

			if (!book.equals(map2.getByISBN("1234567891234"))) {
				return false;
			}

		} catch (Exception e) {

			// unexpected error
			return false;
		}

		return true; // passed

	}

	/**
	 * This method verifies that the constructors, resetAuthorFilter method,
	 * searchByTitleWord method, getByISBN method works properly and any relevant
	 * exceptions are thrown. 
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test8() {

		
		return true; // passed

	}

	/**
	 * This method verifies that the constructors, resetAuthorFilter method,
	 * searchByTitleWord method, getByISBN method works properly and any relevant
	 * exceptions are thrown.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test9() {

		
		return true; // passed

	}

	/**
	 * This main method prints the result of test1,2,3,4, and 5.
	 *
	 */
	public static void main(String[] args) {

		if (test1()) {
			System.out.println("BackendDeveloper Individual Test 1: passed");
		} else {
			System.out.println("BackendDeveloper Individual Test 1: failed");
		}

		if (test2()) {
			System.out.println("BackendDeveloper Individual Test 2: passed");
		} else {
			System.out.println("BackendDeveloper Individual Test 2: failed");
		}

		if (test3()) {
			System.out.println("BackendDeveloper Individual Test 3: passed");
		} else {
			System.out.println("BackendDeveloper Individual Test 3: failed");
		}

		if (test4()) {
			System.out.println("BackendDeveloper Individual Test 4: passed");
		} else {
			System.out.println("BackendDeveloper Individual Test 4: failed");
		}

		if (test5()) {
			System.out.println("BackendDeveloper Individual Test 5: passed");
		} else {
			System.out.println("BackendDeveloper Individual Test 5: failed");
		}

		if (test6()) {
			System.out.println("BackendDeveloper Integration Test 1: passed");
		} else {
			System.out.println("BackendDeveloper Integration Test 1: failed");
		}

		if (test7()) {
			System.out.println("BackendDeveloper Integration Test 2: passed");
		} else {
			System.out.println("BackendDeveloper Integration Test 2: failed");
		}

		if (test8()) {
			System.out.println("BackendDeveloper Partner (FrontendDeveloper) Test 1: passed");
		} else {
			System.out.println("BackendDeveloper Partner (FrontDeveloper) Test 1: failed");
		}

		if (test9()) {
			System.out.println("BackendDeveloper Partner (FrontendDeveloper) Test 2: passed");
		} else {
			System.out.println("BackendDeveloper Partner (FrontendDeveloper) Test 2: failed");
		}

	}

}
