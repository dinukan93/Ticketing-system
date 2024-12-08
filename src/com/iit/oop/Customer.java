package com.iit.oop;

public class Customer implements Runnable {
    private int customerId;
    private int customerRetrievalRate;
    private TicketPool ticketPool; // Shared pool


    // Constructor initializing the customer's attributes.
    public Customer(int customerId, int customerRetrievalRate, TicketPool ticketPool) {
        this.customerId = customerId;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (ticketPool.getTicketCount() <= ticketPool.getTicketCount()) { // Infinite loop to continuously purchase tickets.
            ticketPool.removeTicket();
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status and exit loop.
                break;
            }
        }
    }

}

