package com.iit.oop;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<String> ticketQueue; // Queue to store tickets
    private int maximumCapacity; // Maximum allowed tickets
    private int ticketCount; // Ticket count to generate unique ticket IDs
    private int totalTickets;

    //private int remainingTickets = paraValues.getTotalTickets;

    public TicketPool(int maximumCapacity,int totalTickets) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
        this.ticketCount = 1;
        this.totalTickets =totalTickets;
    }

    // Method to add tickets to the pool in a thread-safe way
    public synchronized void addTickets() {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                wait(); // Wait if the pool is at maximum capacity
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        if(ticketCount<=totalTickets) {
            // Generate unique ticket ID
            String ticketId = "TicketID: " + ticketCount;
            ticketQueue.add(ticketId);
            System.out.println(ticketId + " added by " + Thread.currentThread().getName() + ". Available tickets: " + ticketQueue.size());
            ticketCount++; // Always increment for uniqueness
            notifyAll(); // Notify waiting threads
        }
    }

    // Method to remove a ticket from the pool in a thread-safe way
    public synchronized void removeTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait(); // Wait until there is a ticket to remove
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        String purchasedTicket = ticketQueue.poll();
        System.out.println(purchasedTicket + " purchased by " + Thread.currentThread().getName() + " Remaining tickets: " + ticketQueue.size());
        notifyAll(); // Notify waiting threads
    }
}





