public class TicketFD implements ITicket{
    Double price;
    String destination;
    String departure;
    String genre;

    public TicketFD(Double price, String departure, String destination) {
        this.price = price;
        this.destination = destination;
        this.departure = departure;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public String getDeparture() {
        return this.departure;
    }
}
