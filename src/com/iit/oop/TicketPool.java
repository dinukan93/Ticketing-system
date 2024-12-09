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




    //Correct one
    /*public synchronized void addTicket(){
        //if(ticketCount > totalTickets) {
          //  return;

        while (ticketQueue.size() >= maximumCapacity) {
            try {
                System.out.println("Pool is full");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace(); // For command line interface (CLI)
                throw new RuntimeException(e.getMessage()); // For Client-Server application
            }
        }

        if (!TicketSystem.isRunning()) {return;}

        String ticketId = "Ticket-" + ticketCount;
        this.ticketQueue.add(ticketId);
        ticketCount++;
        notifyAll(); // Notify all the waiting threads
        System.out.println(ticketId + " added by " + Thread.currentThread().getName() + ". Available tickets: " + ticketQueue.size());

    }*/

    /*public synchronized void removeTicket() {
        if (ticketCount > totalTickets) {
            return;
        }
        while (ticketQueue.isEmpty()) {
            try {
                System.out.println("Customer waiting, no tickets available.");
                wait(); // Wait until a ticket is available
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        String purchasedTicket = ticketQueue.poll();
        notifyAll();
        System.out.println(purchasedTicket + " purchased by " + Thread.currentThread().getName() + ". Remaining tickets: " + ticketQueue.size());
    }*/

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
            notifyAll();
            System.out.println(purchasedTicket + " purchased by " + Thread.currentThread().getName() + " Remaining tickets: " + ticketQueue.size());
        }
    }












    //correct one
    /*public synchronized void removeTicket() {
        while (ticketQueue.isEmpty() && ticketCount <= totalTickets ) {
            try {
                System.out.println("Waiting for vendor release tickets..");
                wait(); // Wait until there is a ticket to remove
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        if (!TicketSystem.isRunning()) {return;}

        if (ticketCount < totalTickets) {
            String purchasedTicket = ticketQueue.poll();
            notifyAll(); // Notify waiting threads
            System.out.println(purchasedTicket + " purchased by " + Thread.currentThread().getName() + " Remaining tickets: " + ticketQueue.size());
        }

    }*/

    public synchronized int getTicketCount() {
        return ticketCount;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public synchronized boolean isTicketAvailable() {
        return ticketQueue.size() > 0 || ticketCount <= totalTickets;
    }
}






