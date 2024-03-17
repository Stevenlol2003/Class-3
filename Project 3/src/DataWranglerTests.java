import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataWranglerTests {
    /**
     * tests returning the 15th Ticket's departure in the DOT file
     */
    @Test
    public void test1() {

        String expected = null; // ideal departure

        try {

	    // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = ticketList.get(14).getDeparture();
            
	    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

	// check if this is correct
        assertEquals(expected, "Salt Lake City, UT");
    }

    /**
     * tests returning the 30th ticket's Destination in the DOT file
     */
    @Test
    public void test2() {

	
        String expected = null; // ideal destination

        try {

            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = ticketList.get(29).getDestination();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // check if this is correct
        assertEquals(expected, "New York City, NY (Metropolitan Area)");

    }

    /**
     *tests returning the 100th ticket's price in the DOT file
     */
    @Test
    public void test3() {
    
        String expected = null; // ideal price

        try {

            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = "" + ticketList.get(99).getPrice();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // check if this is correct
        assertEquals(expected, "129.35");
    
    }

    /**
     * tests returning ticket's price on the last row in the DOT file
     */
    @Test
    public void test4() {

        String expected = null; // ideal price

        try {

            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = "" + ticketList.get(106031).getPrice();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // check if this is correct
        assertEquals(expected, "91.49");
    }

    /**
     * tests returning the 200nd ticket's destination, departure, and price in the DOT file
     */
    @Test
    public void test5() {

        String expected = null;

        try {
	
            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
    
	    expected = ticketList.get(199).getDestination() + " - " + ticketList.get(199).getDeparture() + " - " + ticketList.get(199).getPrice();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(expected, "New Orleans, LA - Houston, TX - 96.84");
    }

    /**
     * This test tests my Ticket class works with pathFound method of Backend. 
     */
   @Test
   public void Integration1(){

        // make a ticket
        TicketBackend ticket = new TicketBackend();

	String departure = "Houston, TX";
        String destination = "New Orleans, LA";

	// the ticket should exist
        assertTrue(ticket.pathFound(departure, destination));
   }

    /**
     * This test tests my Ticket class works with pathFound method of Backend. 
     */
   @Test
   public void Integration2(){

       	TicketBackend ticket= new TicketBackend();

	// Notice that we have to search using both state and city name
	String departure = "Houston";
        String destination = "New Orleans";

	// the ticket should not exist
        assertEquals(ticket.getCheapestPath(departure, destination), null);
   }

    /**
     * This test tests whether we can get the existing ticket using searchByDeparture Backend class.
     */
    @Test
    public void CodeReviewOfBackendDeveloper1() {

    }

    /**
     * This test tests whether we can get the existing ticket using searchByDestination Backend class. 
     */
    @Test
    public void CodeReviewOfBackendDeveloper2(){
    }
}
