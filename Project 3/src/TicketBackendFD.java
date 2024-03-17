import java.util.ArrayList;
import java.util.List;

public class TicketBackendFD implements ITicketBackendFD {

	public boolean searchByDeparture(String name) {
		return true;
	}

	public boolean searchByDestination(String name) {
		return true;
	}

	public boolean pathFound(String departure, String destination) {
		return true;
	}

	public List<ITicket> getCheapestPath(String departure, String destination) {
		List<ITicket> path = new ArrayList<>();
		path.add(new TicketFD(500.0, "Madison", "New York"));
		path.add(new TicketFD(200.0, "New York", "Atlanta"));
		path.add(new TicketFD(300.0, "Atlanta", "Miami"));
		return path;
	}

	public List<ITicket> getLeastTransfer(String departure, String destination) {
		List<ITicket> path = new ArrayList<>();
		path.add(new TicketFD(500.0, "Madison", "New York"));
		path.add(new TicketFD(200.0, "New York", "Atlanta"));
		path.add(new TicketFD(300.0, "Atlanta", "Miami"));
		return path;
	}

}
