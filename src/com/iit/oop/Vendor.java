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
        while (TicketSystem.isRunning() && ticketPool.getTicketCount() <= totalTickets) {
            ticketPool.addTicket();
            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }


    //correct one
    /*@Override
    public void run() {
        for (int i = 1; i < totalTickets; i++) {
            ticketPool.addTicket();
            try {
                Thread.sleep(ticketReleaseRate * 1000); // To calculate to MS
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

}




