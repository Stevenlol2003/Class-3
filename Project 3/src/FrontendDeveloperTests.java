import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * @author 
 *
 */
public class FrontendDeveloperTests {
	
	/**
	 * test for the option 1
	 */
	@Test
	public void test1() {

		try {
			// 1. Create a new TextUITester object for each test, and
	        // pass the text that you'd like to simulate a user typing as only argument.
			TextUITester tester = new TextUITester("1\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend();
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
    		String out = "List all locations: ";

    		// 2. Run the code that you want to test here:
    		_instance.runCommandLoop();
    		
    		// 3. Check whether the output printed to System.out matches your expectations.
			String output = tester.checkOutput();
			//System.out.println(output);
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
	
	/**
	 * test for the option 2
	 */
	@Test
	public void test2() {
		try {
			// 1. Create a new TextUITester object for each test, and
	        // pass the text that you'd like to simulate a user typing as only argument.
			TextUITester tester = new TextUITester("2\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend();
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
    		String out = "List all tickets: ";

			// 2. Run the code that you want to test here:
			_instance.runCommandLoop();

			// 3. Check whether the output printed to System.out matches your expectations.
			String output = tester.checkOutput();
			//System.out.println(output);
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
	
	/**
	 * test for the option 3 and delete input value
	 */
	@Test
	public void test3() {
		try {
			// 1. Create a new TextUITester object for each test, and
	        // pass the text that you'd like to simulate a user typing as only argument.
			TextUITester tester = new TextUITester("3\nMadison\nMiami\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend();
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
    		String out = "          Madison to New York - $500.0\n" +
					"          New York to Atlanta - $200.0\n" +
					"          Atlanta to Miami - $300.0";
    		
    		// 2. Run the code that you want to test here:
    		_instance.runCommandLoop();
    		
    		// 3. Check whether the output printed to System.out matches your expectations.
			String output = tester.checkOutput();
			System.out.println(output);
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
	
	/**
	 * test for the option 4 (exit)
	 */
	@Test
	public void test4() {
		try {
			// 1. Create a new TextUITester object for each test, and
	        // pass the text that you'd like to simulate a user typing as only argument.
			TextUITester tester = new TextUITester("4\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend();
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
    		String out = "Goodbye!";
    		
    		// 2. Run the code that you want to test here:
    		_instance.runCommandLoop();
    		
    		// 3. Check whether the output printed to System.out matches your expectations.
			String output = tester.checkOutput();
			//System.out.println(output);
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
	
	/**
	 * test for the invalid input out of bound 1-4
	 */
	@Test
	public void test5() {
		try {
			// 1. Create a new TextUITester object for each test, and
			// pass the text that you'd like to simulate a user typing as only argument.
			TextUITester tester = new TextUITester("5\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend();
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
			String out = "Invalid Input!";

			// 2. Run the code that you want to test here:
			_instance.runCommandLoop();

			// 3. Check whether the output printed to System.out matches your expectations.
			String output = tester.checkOutput();
			//System.out.println(output);
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}

	private FlightTicketGraph<String,Integer> graph;

	/**
	 * Instantiate graph.
	 */
	@BeforeEach
	public void createGraph() {
		graph = new FlightTicketGraph<>();
		// insert vertices A-F
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		graph.insertVertex("F");
		// insert edges
		graph.insertEdge("A","B",6);
		graph.insertEdge("A","C",2);
		graph.insertEdge("A","D",5);
		graph.insertEdge("B","E",1);
		graph.insertEdge("B","C",2);
		graph.insertEdge("C","B",3);
		graph.insertEdge("C","F",1);
		graph.insertEdge("D","E",3);
		graph.insertEdge("E","A",4);
		graph.insertEdge("F","A",1);
		graph.insertEdge("F","D",1);
	}

	/**
	 * Checks the distance/total weight cost from the vertex A to C.
	 */
	@Test
	public void CodeReviewOfAlgorithmEngineer1() {
		assertTrue(graph.getCheapestPathCost("A", "C") == 2);
	}

	/**
	 * Checks the distance/total weight cost from the vertex A to E.
	 */
	@Test
	public void CodeReviewOfAlgorithmEngineer2() {
		assertTrue(graph.getCheapestPathCost("A", "E") == 6);
	}

	public void IntegrationTest1() {

	}

	public void IntegrationTest2() {

	}
}

