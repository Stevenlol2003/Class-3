import java.util.List;

public interface ITicketBackendFD {
	public boolean searchByDeparture(String name);

	public boolean searchByDestination(String name);

	public boolean pathFound(String departure, String destination);

	public List<ITicket> getCheapestPath(String departure, String destination);

	public List<ITicket> getLeastTransfer(String departure, String destination);
}
