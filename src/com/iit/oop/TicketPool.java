package com.iit.oop;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<String> ticketQueue; // Queue to store tickets
    private int maximumCapacity; // Maximum allowed tickets
    private int totalTickets;
    private int ticketCount;
    private int purchasedTicketCount;


    public TicketPool(int maximumCapacity,int totalTickets) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
        this.totalTickets =totalTickets;
        this.ticketCount = 1;
        this.purchasedTicketCount = 0;
    }

    public synchronized void addTicket() {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                System.out.println("Pool is full");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }

        // Early exit if system is not running
        if (!TicketSystem.isRunning() || ticketCount > totalTickets) {
            return;
        }

        String ticketId = "Ticket-" + ticketCount;
        this.ticketQueue.add(ticketId);
        ticketCount++;
        notifyAll();
        System.out.println(ticketId + " added by " + Thread.currentThread().getName() + ". Available tickets: " + ticketQueue.size());
    }



    public synchronized void removeTicket() {
        // Early exit if system is not running
        if (!TicketSystem.isRunning()) {
            return;
        }
        while (ticketQueue.isEmpty() && TicketSystem.isRunning() && ticketCount <= totalTickets) {
            try {
                System.out.println("Waiting for vendor release tickets..");
                wait(); // Wait until there is a ticket to remove
            } catch (InterruptedException e) {
                return;
            }
        }
        if (!TicketSystem.isRunning() || ticketCount > totalTickets) {
            return;
        }

        if (ticketQueue.size() > 0) {
            String purchasedTicket = ticketQueue.poll();
            purchasedTicketCount++;
            notifyAll();
            System.out.println(purchasedTicket + " purchased by " + Thread.currentThread().getName() + " Remaining tickets: " + ticketQueue.size());
        }
    }

    public synchronized int getTicketCount() {
        return ticketCount;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public synchronized boolean isTicketAvailable() {
        return ticketQueue.size() > 0 || ticketCount <= totalTickets;
    }

    public int getPurchasedTicketCount() {
        return purchasedTicketCount;
    }

    public int getTicketQueueSize() {
        return ticketQueue.size();
    }
}






