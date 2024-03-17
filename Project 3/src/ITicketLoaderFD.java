import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public interface ITicketLoaderFD {
    List<ITicket> loadTickets(String filepathToDOT) throws FileNotFoundException;

}
