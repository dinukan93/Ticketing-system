package com.iit.oop;
public class Vendor implements Runnable {
    private int vendorId;
    private int ticketReleaseRate;
    private int totalTickets;
    private TicketPool ticketPool; // Shared pool



    public Vendor(int vendorId, int ticketReleaseRate , TicketPool ticketPool, int totalTickets) {
        this.vendorId = vendorId;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {
        while (ticketPool.getTicketCount() <= totalTickets) { // Only keep running until all tickets are released
            ticketPool.addTickets();
            try {
                Thread.sleep(ticketReleaseRate * 1000); // To simulate delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


