import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TicketLoaderFD implements ITicketLoaderFD
{
    @Override
    public List<ITicket> loadTickets(String filepathToDOT) throws FileNotFoundException {
        List<ITicket> list = new ArrayList<>();
        list.add(new TicketFD(500.0, "Madison", "New York"));
        list.add(new TicketFD(200.0, "New York", "Atlanta"));
        list.add(new TicketFD(300.0, "Atlanta", "Miami"));
        return list;
    }
}
