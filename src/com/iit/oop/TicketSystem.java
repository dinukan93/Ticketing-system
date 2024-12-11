package com.iit.oop;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;


public class TicketSystem {
    public static volatile boolean isRunning = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Configuration paraValues = null;

        System.out.println("Real-Time Event Ticketing System!");
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
                    paraValues = startSystem(sc);
                    sc.next();
                    break;
                case 2:
                    if (paraValues != null) {
                        saveFile(paraValues);
                    } else {
                        System.out.println("No configuration to save.");
                    }
                    break;

                case 3:
                    paraValues=loadFile();
                    break;
                case 4:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static Configuration startSystem(Scanner sc) {
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


        Configuration paraValues = new Configuration(totalTickets, releaseRate, retrievalRate, maxCapacity, numVendors, numCustomers);

        TicketPool ticketPool = new TicketPool(paraValues.getMaxTicketCapacity(), paraValues.getTotalTickets());
        System.out.println("System configured successfully.");


        for (int vendorId = 1; vendorId <= paraValues.getNumVendors(); vendorId++) {
            Vendor vendor = new Vendor(vendorId, paraValues.getTicketReleaseRate(), ticketPool, paraValues.getTotalTickets());
            Thread vendorThread = new Thread(vendor, "Vendor ID- " + vendorId);
            vendorThread.start();
        }
        for (int customerId = 1; customerId <= paraValues.getNumCustomers(); customerId++) {
            Customer customer = new Customer(customerId, paraValues.getCustomerRetrievalRate(), ticketPool);
            Thread customerThread = new Thread(customer, "Customer ID- " + customerId);
            customerThread.start();

        }
        System.out.println("\nSystem is running. Enter '0' to stop.");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("0")) {
                isRunning = false;// Set flag to stop threads
                break;
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nSummary of Real-Time Ticket Management System:");
        System.out.println("Total Tickets Purchased: " + ticketPool.getTicketCount());
        System.out.println("Remaining Tickets in Pool: " + (ticketPool.getTotalTickets()-ticketPool.getTicketCount()));
        System.out.println("System stopped.");

        return paraValues;


    }
    public static boolean isRunning() {
        return isRunning;
    }

    public static void saveFile(Configuration paraValues) {
        Gson gson = new Gson();
        String json = gson.toJson(paraValues);

        try (FileWriter writer = new FileWriter("userInput.json")) {
            writer.write(json);
            System.out.println("Object saved to userInput.json");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    public static Configuration loadFile() {
        try (FileReader reader = new FileReader("userInput.json")) {
            Gson gson = new Gson();
            System.out.println("load from userInput.json successfully");
            return gson.fromJson(reader, Configuration.class);
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file 'userInput.json' was not found.");
            return null;
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return null;
        }
    }

    public static void exitSystem() {
        isRunning = false;
        System.out.println("Exiting the system...");
        System.exit(0);  // Exit the program
    }

}



