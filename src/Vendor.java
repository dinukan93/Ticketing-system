// Vendor Class
class Vendor implements Runnable {
    private int vendorId;           // Unique ID for the vendor
    private int ticketsPerRelease; // Number of tickets released per batch
    private int releaseInterval;   // Time interval between releases (in milliseconds)
    private TicketPool ticketPool; // Shared ticket pool (thread-safe)

    // Constructor
    public Vendor(int vendorId, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }

    // Run method for the Vendor thread
    @Override
    public void run() {
        for (int i = 0; i < vendorId; i++) {

        }
        ticketPool.addTickets();
    }
}
