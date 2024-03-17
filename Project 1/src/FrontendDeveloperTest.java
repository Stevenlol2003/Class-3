import java.io.File;
import java.util.Scanner;

public class FrontendDeveloperTest {

	/**
	 * test for close the loop
	 * @return true only when pass all the test
	 */
	public static boolean test1() { 
		try {
			// 1. Create a new TextUITester object for each test, and
	        // pass the text that you'd like to simulate a user typing as only argument.

			TextUITester tester = new TextUITester("4\n");

			String out = "Goodbye!";
			Scanner sc = new Scanner(System.in);
			IBookMapperBackend backend = new BookMapperBackendFD();
			IISBNValidator validator = new ISBNValidatorFD();
			BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
			
	        
	        // 2. Run the code that you want to test here:
	        front.runCommandLoop(); // (this code should read from System.in and write to System.out)

	        // 3. Check whether the output printed to System.out matches your expectations.
	        String output = tester.checkOutput();
	        
	        if(!output.contains(out))//&& output.contains("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"+" \n")
	        	return false;	
		}catch(Exception e) {};
		return true;
		}
	
	
	/**
	 * test isbnLookup()
	 * @return true only when pass all the test
	 */
	public static boolean test2() { 
		try {
			TextUITester tester = new TextUITester("1\n2\n4\n");
			
			String out = "You are in the Lookup ISBN Menu:\n"
					+ "          Enter ISBN to look up:";
			Scanner sc = new Scanner(System.in);
			IBookMapperBackend backend = new BookMapperBackendFD();
			IISBNValidator validator = new ISBNValidatorFD();
			BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
			
	        
	        // 2. Run the code that you want to test here:
	        front.runCommandLoop(); // (this code should read from System.in and write to System.out)

	        // 3. Check whether the output printed to System.out matches your expectations.
	        String output = tester.checkOutput();
	        
	        if(!output.contains(out))//&& output.contains("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"+" \n")
	        	return false;			
		}catch(Exception e) {};
        return true;
		}
	/**
	 * test titleSearch()
	 * @return true only when pass all the test
	 */
	public static boolean test3() { 
        try {
        	TextUITester tester = new TextUITester("2\nfhsl saljfl\n4\n");
    		
    		String out = "You are in the Search for Title Word Menu:\n"
    				+ "          Enter a word to search for in book titles (empty for all books): ";
    		Scanner sc = new Scanner(System.in);
    		IBookMapperBackend backend = new BookMapperBackendFD();
    		IISBNValidator validator = new ISBNValidatorFD();
    		BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
    		
            
            // 2. Run the code that you want to test here:
            front.runCommandLoop(); // (this code should read from System.in and write to System.out)

            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            
            if(!output.contains(out))//&& output.contains("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"+" \n")
            	return false;			
        }catch(Exception e) {};
        return true;
		}
	/**
	 * test Set Author Name Filter situation
	 * @return true only when pass all the test
	 */
	public static boolean test4() { 
		try {
        	TextUITester tester = new TextUITester("3\nfhsl saljfl\n4\n");
    		
    		String out = "You are in the Set Author Filter Menu:\n"
    				+ "          Author name must currently contain: none\n"
    				+ "          Enter a new string for author names to contain (empty for any): ";
    		Scanner sc = new Scanner(System.in);
    		IBookMapperBackend backend = new BookMapperBackendFD();
    		IISBNValidator validator = new ISBNValidatorFD();
    		BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
    		
            
            // 2. Run the code that you want to test here:
            front.runCommandLoop(); // (this code should read from System.in and write to System.out)

            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            
            if(!output.contains(out))//&& output.contains("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"+" \n")
            	return false;			
        }catch(Exception e) {};

		return true;		
		}
	
	/**
	 *  test exception when ISBN is invalid 
	 * @return true only when pass all the test
	 */
	public static boolean test5() { 
		try {
        	TextUITester tester = new TextUITester("1\nfhsl saljfl\n4\n");
    		
    		String out = "Invalid ISBN number";
    		Scanner sc = new Scanner(System.in);
    		IBookMapperBackend backend = new BookMapperBackendFD();
    		IISBNValidator validator = new ISBNValidatorFD();
    		BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
    		
            
            // 2. Run the code that you want to test here:
            front.runCommandLoop(); // (this code should read from System.in and write to System.out)

            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            
            if(!output.contains(out))//&& output.contains("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"+" \n")
            	return false;			
        }catch(Exception e) {};

		return true;		
		}
	
	//test Lookup ISBN and Search by Title Word
	public static boolean FDtest1() { 
		try {
        	TextUITester tester = new TextUITester("1\nISBN\n2\ntitleword\n4\n");
    		
    		String out1 = "Invalid ISBN number";
    		String out2 = "You are in the Search for Title Word Menu:\n"
    				+ "          Enter a word to search for in book titles (empty for all books):";
    		Scanner sc = new Scanner(System.in);
    		IBookMapperBackend backend = new BookMapperBackendFD();
    		IISBNValidator validator = new ISBNValidatorFD();
    		BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
            
            front.runCommandLoop(); 

            String output = tester.checkOutput();
            
            if(!output.contains(out1))
            	return false;
            if(!output.contains(out2))
            	return false;
            
        }catch(Exception e) {};
		return true;
	}
	
	    //test Set Author Name Filter and Exit Application
		public static boolean FDtest2() { 
			try {
	        	TextUITester tester = new TextUITester("3\nauthor1\n4\n");
	    		
	    		String out1 = "You are in the Set Author Filter Menu:\n"
	    				+ "          Author name must currently contain:";
	    		String out2 = "Goodbye!";
	    		Scanner sc = new Scanner(System.in);
	    		IBookMapperBackend backend = new BookMapperBackendFD();
	    		IISBNValidator validator = new ISBNValidatorFD();
	    		BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
	           
	            front.runCommandLoop(); 

	            String output = tester.checkOutput();
	            
	            if(!output.contains(out1))
	            	return false;
	            if(!output.contains(out2))
	            	return false;
	        }catch(Exception e) {
	        	return false;
	        };
			return true;
		}
		
		public static boolean FDIntegrationTest1() {
			try {
	        	TextUITester tester = new TextUITester("1\n"
	        			+ "439785960"
	        			+ "4\n");
	    		
	    		String out1 = "Harry Potter and the Half-Blood Prince (Harry Potter  #6)";
	    		String out2 = "Goodbye!";
	    		Scanner sc = new Scanner(System.in);
	    		IBookMapperBackend backend = new BookMapperBackend();
	    		IISBNValidator validator = new ISBNValidator();
	    		BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
	           
	            front.runCommandLoop(); 

	            String output = tester.checkOutput();
	            
	            if(!output.contains(out1))
	            	return false;
	            if(!output.contains(out2))
	            	return false;
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
	        	return false;
	        };
			return true;
		}
		
		public static boolean FDIntegrationTest2() {
			try {
	        	TextUITester tester = new TextUITester("3\n"
	        			+"J.K. Rowling\n"
	        			+"2\n"
	        			+ "Harry Potter and the Half-Blood Prince (Harry Potter  #6)\n"
	        			+ "4\n");
	    		
	    		String out1 = "You are in the Set Author Filter Menu:\n"
	    				+ "          Author name must currently contain:";
	    		String out2 = "Goodbye!";
	    		String out3 = "Harry Potter and the Half-Blood Prince (Harry Potter  #6)";
	    		Scanner sc = new Scanner(System.in);
	    		IBookMapperBackend backend = new BookMapperBackendFD();
	    		IISBNValidator validator = new ISBNValidatorFD();
	    		BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
	           
	            front.runCommandLoop(); 

	            String output = tester.checkOutput();
	            
	            if(!output.contains(out1))
	            	return false;
	            if(!output.contains(out2))
	            	return false;
	            if(!output.contains(out3))
	            	return false;
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
	        	return false;
	        };
			return true;
		}
	/**
	 * run all the tests
	 * @param args
	 */
	public static void main(String[] args) {

		 
		System.out.println("FD Individual test1: " + test1());
		System.out.println("FD Individual test2: " + test2());
		System.out.println("FD Individual test3: " + test3());
		System.out.println("FD Individual test4: " + test4());
		System.out.println("FD Individual test5: " + test5());
		System.out.println("FD Integration Test 1: " + FDIntegrationTest1());
		System.out.println("FD Integration Test 2: " + FDIntegrationTest2());
		System.out.println("FD Individual test6: " + FDtest1());
		System.out.println("FD Individual test7: " + FDtest2());
		
	}

}
