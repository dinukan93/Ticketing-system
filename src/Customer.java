public class Customer implements Runnable{
    private int customerId;
    private double retrievalInterval;
    private TicketPool ticketPool; //Access to Shared Resource

    public Customer(int customerId, double retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        ticketPool.removeTicket();
    }
}
