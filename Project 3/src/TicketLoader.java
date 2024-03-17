import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.io. *;
import java.util.Scanner;

public class TicketLoader implements  ITicketLoader{
    /**
     * This method loads the list of plane tickets data from a DOT formatted file.
     * @param filepathToDot path to the DOT formatted file. relative to the executable
     * @return a list of plane ticket objects
     * @throws FileNotFoundException
     */
     @Override
     // @SuppressWarnings({ "unchecked", "rawtypes" })
     public List<ITicket> loadTickets(String filepathToDot) throws FileNotFoundException{

	// create a new list here 
        List<ITicket> list = new ArrayList(); 

	// make a scanner
        Scanner sc = new Scanner(new FileInputStream(filepathToDot), "UTF-8");

        sc.nextLine(); //skipping the first row of the XML file's <Games>

        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

		// stop the while loop when reach at the last line
                if (line.equals("}")) {
                    break;
                }

		// get the departure by getting the index between the departure
		int index = line.indexOf("-");
		String departure = line.substring(0, index).strip();
		departure = departure.substring(1, departure.length() - 1);


		// get the destination by getting the index between the destination
		int index1 = line.indexOf(">");
		int index2 = line.indexOf("[");
		String destination = line.substring(index1 + 1, index2).strip();
		destination = destination.substring(1, destination.length() - 1);

		// get the price by getting the index between the price
		index1 = line.indexOf("=");
                index2 = line.indexOf("]");
                String price = line.substring(index1 + 1, index2).strip();

		// make a new ticket object
		double priceDouble = Double. parseDouble(price);
        Ticket ticket = new Ticket(priceDouble, destination, departure);

		// add the new created ticket object
                list.add(ticket);
                
            }
        } catch (Exception e) { e.printStackTrace(); }

	// exception happnes and then close this scanner
	sc.close();

        return list;
    }
}
