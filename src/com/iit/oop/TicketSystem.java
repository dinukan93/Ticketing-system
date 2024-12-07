package com.iit.oop;
import java.util.Scanner;


public class TicketSystem {
    private static boolean exit = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total tickets: ");
        int totalTickets = sc.nextInt();

        System.out.print("Enter ticket release rate (tickets per second): ");
        int releaseRate = sc.nextInt();

        System.out.print("Enter customer retrieval rate (tickets per second): ");
        int retrievalRate = sc.nextInt();

        System.out.print("Enter maximum ticket capacity: ");
        int maxCapacity = sc.nextInt();


        System.out.print("Enter number of vendors: ");
        int numVendors = sc.nextInt();

        System.out.print("Enter number of customers: ");
        int numCustomers = sc.nextInt();


        Configuration paraValues = new Configuration(totalTickets,releaseRate,retrievalRate,maxCapacity);

        TicketPool ticketPool = new TicketPool(paraValues.getMaxTicketCapacity(),paraValues.getTotalTickets());
        System.out.println("System configured successfully.");

        int ticketsPerRelease = 5; // Number of tickets each vendor releases
        int releaseInterval = 50;
        for(int vendorId=1;vendorId<=numVendors;vendorId++) {
            Vendor vendor = new Vendor(vendorId, ticketsPerRelease, releaseInterval, ticketPool, paraValues.getTotalTickets());
            Thread vendorThread = new Thread(vendor, "Vendor ID- "+vendorId);
            vendorThread.start();
        }

        int retrievalInterval=2;
        for(int customerId=1;customerId<=numCustomers;customerId++) {
            Customer customer = new Customer(customerId,retrievalInterval,ticketPool);
            Thread customerThread = new Thread(customer, "Customer ID- "+customerId);
            customerThread.start();
        }


        /*System.out.println("Real-Time Event Ticketing System!");
        while (true) {
            System.out.println("\n  Menu");
            System.out.println("1. Start Ticketing System");
            System.out.println("2. Save to a file");
            System.out.println("3. Load from a file");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    startSystem();
                    break;
                case 2:
                    //
                    break;
                case 3:
                    //
                    break;
                case 4:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }*/
    }
}



