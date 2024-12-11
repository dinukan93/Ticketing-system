package com.iit.oop;

public class Customer implements Runnable {
    private int customerId;
    private int customerRetrievalRate;
    private TicketPool ticketPool;

    public Customer(int customerId, int customerRetrievalRate, TicketPool ticketPool) {
        this.customerId = customerId;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (TicketSystem.isRunning() && ticketPool.getTicketCount() <= ticketPool.getTotalTickets()) {
            ticketPool.removeTicket();
            try {
                Thread.sleep(customerRetrievalRate * 800);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}


