import java.util.List;
import java.util.NoSuchElementException;

public class TicketBackend implements ITicketBackend {

    FlightTicketADT graph = new FlightTicketGraphBD();

    /**
     * Search through all the stops in the graph and return true if it has given departure
     * 
     * @param departure departure of the journey
     * @return true when the departure exists
     */
    public boolean searchByDeparture(String departure) {
        if(departure == null) {
			return false;
		}
        return graph.containsVertex(departure);
    }

    /**
     * Search through all the stops in the graph and return true if it has given destination
     * 
     * @param destination destination of the journey
     * @return true when the departure exists
     */
    public boolean searchByDestination(String destination){
        if(destination == null) {
			return false;
		}
        return graph.containsVertex(destination);
    }

    /**
     * Search the graph and return true if it has the path from given departure 
     * and destination
     * 
     * @param departure departure of the journey
     * @param destination destination of the journey
     * @return true if there is a path
     */
    public boolean pathFound(String departure, String destination){
        try {
            graph.LeastTransferPath(departure, destination);
        }
        catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Search the graph and return the list of the paths which are cheapest top three
     * 
     * @param departure departure of the journey
     * @param destination destination of the journey
     * @return List List of Ticket for the cheapest path
     */
    public List<ITicket> getCheapestPath(String departure, String destination){   
        return graph.CheapestPath(departure, destination);
    }

    /**
     * Search the graph and return the list of the paths which are cheapest top three
     * 
     * @param departure departure of the journey
     * @param destination destination of the journey
     * @return List List of Ticket for the least transfer path
     */
    public List<ITicket> getLeastTransfer(String departure, String destination){
        return graph.LeastTransferPath(departure, destination);
    }

}