public class Ticket implements ITicket{

    private double price;
    private String destination;
    private String departure;

    public Ticket() {
        this.price = 0;
        this.destination = null;
        this.departure = null;
    }

    public Ticket(double price, String departure, String destination) {
        this.price = price;
        this.destination = destination;
        this.departure = departure;
    }

    /**
     * Returns the price of the ticket.
     * @return price of the ticket
     */
    @Override
    public Double getPrice(){
	    
	return this.price;
    }

    /**
     * Returns a string that contains the destination of the ticket
     * as a single string
     * @return destination as single string
     *
     */
    @Override
    public String getDestination(){
	return this.destination;
    }

    /**
     * Returns  the departure name of this ticket
     * @return departure name of plane ticket
     */
    @Override
    public String getDeparture(){
	return this.departure;
    }

}
