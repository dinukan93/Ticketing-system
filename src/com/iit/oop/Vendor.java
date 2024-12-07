package com.iit.oop;
public class Vendor implements Runnable {
    private final int vendorId; // Unique identifier for the vendor.
    private final int ticketsPerRelease; // Number of tickets released each time.
    private final int releaseInterval; // Time interval (in seconds) between releases.
    private int totalTickets;
    private final TicketPool ticketPool; // Shared pool where tickets are added.


    public Vendor(int vendorId, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool, int totalTickets) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
    }

    /*// Constructor initializing the vendor's attributes.
    public Vendor(int vendorId, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }*/

    // Overriding the run() method to define vendor's behavior.
    @Override
    public void run() {
        /*while (true) { // Infinite loop to continuously release tickets.
            ticketPool.addTickets(); // Add tickets to the pool.
            try {
                Thread.sleep(releaseInterval * 1000); // Wait for the specified interval.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status and exit loop.
                break;
            }
        }*/
        for (int i = 1; i < totalTickets; i++) {
            ticketPool.addTickets();
            try {
                Thread.sleep(releaseInterval * 1000); // To calculate to MS
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

