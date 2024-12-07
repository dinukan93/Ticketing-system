package com.iit.oop;

public class Customer implements Runnable {
    private final int customerId; // Unique identifier for the customer.
    private final int retrievalInterval; // Time interval (in seconds) between retrievals.
    private final TicketPool ticketPool; // Shared pool from where tickets are retrieved.

    // Constructor initializing the customer's attributes.
    public Customer(int customerId, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    // Overriding the run() method to define customer's behavior.
    @Override
    public void run() {
        while (true) { // Infinite loop to continuously purchase tickets.
            ticketPool.removeTicket(); // Remove a ticket from the pool.
            try {
                Thread.sleep(retrievalInterval * 1000); // Wait for the specified interval.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status and exit loop.
                break;
            }
        }
    }

}

