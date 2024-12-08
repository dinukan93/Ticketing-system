package com.iit.oop;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<String> ticketQueue; // Queue to store tickets
    private int maximumCapacity; // Maximum allowed tickets
    private int totalTickets;
    private int ticketCount;

    public TicketPool(int maximumCapacity,int totalTickets) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
        this.totalTickets =totalTickets;
        this.ticketCount = 1;
    }

    public synchronized void addTickets() {
        while (ticketQueue.size() >= maximumCapacity && ticketCount <= totalTickets ) {
            try {
                System.out.println("waiting for customer buy ticket. ");
                wait(); // Wait if the pool is at maximum capacity
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        String ticketId = "TicketID: " + ticketCount;
        ticketQueue.add(ticketId);
        ticketCount++;
        notifyAll(); // Notify waiting threads
        System.out.println(ticketId + " added by " + Thread.currentThread().getName() + ". Available tickets: " + ticketQueue.size());
    }


    public synchronized void removeTicket() {
        while (ticketQueue.isEmpty() && ticketCount <= totalTickets ) {
            try {
                System.out.println("Waiting for vendor release tickets..");
                wait(); // Wait until there is a ticket to remove
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        String purchasedTicket = ticketQueue.poll();
        notifyAll(); // Notify waiting threads
        System.out.println(purchasedTicket + " purchased by " + Thread.currentThread().getName() + " Remaining tickets: " + ticketQueue.size());

    }

    public synchronized int getTicketCount() {
        return ticketCount;
    }
}





