import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * @author 
 *
 */
public class BackendDeveloperTests {

	/**
	 * test for the option 1
	 */
	@Test
	public void test1() {
		TicketBackend bd= new TicketBackend();
        String departure = "Madison";
        
        assertTrue(bd.searchByDeparture(departure));
	}

    /**
	 * test for the option 1
	 */
	@Test
	public void test2() {
		TicketBackend bd= new TicketBackend();
        String destination = "Chicago";
        
        assertTrue(bd.searchByDestination(destination));
	}

    /**
	 * test for the option 1
	 */
	@Test
	public void test3() {
        TicketBackend bd= new TicketBackend();
		String departure = "Madison";
        String destination = "Chicago";

        assertTrue(bd.pathFound(departure, destination));
	}

    /**
	 * test for the option 1
	 */
	@Test
	public void test4() {
		TicketBackend bd= new TicketBackend();
		String departure = "Madison";
        String destination = "Chicago";

        assertEquals(bd.getCheapestPath(departure, destination), null);
	}

    /**
	 * test for the option 1
	 */
	@Test
	public void test5() {
		TicketBackend bd= new TicketBackend();
		String departure = "Madison";
        String destination = "Chicago";

        assertEquals(bd.getLeastTransfer(departure, destination), null);
	}
}
