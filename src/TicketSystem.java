import java.util.Scanner;
public class TicketSystem {
    private static TicketPool tkpool;
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

        Configuration paraValues = new Configuration(totalTickets,releaseRate,retrievalRate,maxCapacity);

        TicketPool tkpool= new TicketPool(maxCapacity);
        System.out.println("System configured successfully.");

        int vendorId = 1;
        int ticketsPerRelease = 5;
        int releaseInterval = 2000;

        Vendor vendor = new Vendor(vendorId, ticketsPerRelease, releaseInterval, tkpool);
        Thread vendorThread = new Thread(vendor);
        vendorThread.start();

        //Vendor












        /*System.out.println("Real-Time Event Ticketing System!");
        while (true) {
            System.out.println("\n  Menu");
            System.out.println("1. Configure System");
            System.out.println("2. Start Ticketing System");
            System.out.println("3. Display Ticket Availability");
            System.out.println("4. Save to a file");
            System.out.println("5. Load from a file");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    getUserInputs(sc);
                    break;
                case 2:
                    startSystem();
                    break;
                case 3:
                    //
                    break;
                case 4:
                    //
                    break;
                case 5:
                    //
                    break;
                case 6:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }*/
    }

    /*public static void getUserInputs(Scanner sc) {

        System.out.print("Enter total tickets: ");
        int totalTickets = sc.nextInt();

        System.out.print("Enter ticket release rate (tickets per second): ");
        int releaseRate = sc.nextInt();

        System.out.print("Enter customer retrieval rate (tickets per second): ");
        int retrievalRate = sc.nextInt();

        System.out.print("Enter maximum ticket capacity: ");
        int maxCapacity = sc.nextInt();

        Configuration paraValues = new Configuration(totalTickets,releaseRate,retrievalRate,maxCapacity);

        TicketPool tkpool= new TicketPool(maxCapacity);

        System.out.println("System configured successfully.");

    }*/

    /*public static void startSystem() {

        int vendorId = 1;
        int ticketsPerRelease = 5;
        int releaseInterval = 2000;

        Vendor vendor = new Vendor(vendorId, ticketsPerRelease, releaseInterval, tkpool);
        Thread vendorThread = new Thread(vendor);
        vendorThread.start();

    }*/


    /*private static void exitSystem() {
    }*/
}

/*Configuration totalTickets;
        Configuration ticketReleaseRate;
        Configuration customerRetrievalRate;
        Configuration maxTicketCapacity;

        Customer customerId;
        Customer retrievalInterval;

        Vendor vendorId;
        Vendor ticketsPerRelease;
        Vendor releaseInterval;

        TicketPool maxCapacity;*/

//Configuration parameterValues=null;

/*System.out.print("Enter total tickets: ");
        int totalTickets = sc.nextInt();

        System.out.print("Enter ticket release rate (tickets per second): ");
        int releaseRate = sc.nextInt();

        System.out.print("Enter customer retrieval rate (tickets per second): ");
        int retrievalRate = sc.nextInt();

        System.out.print("Enter maximum ticket capacity: ");
        int maxCapacity = sc.nextInt();

        Configuration paraValues = new Configuration(totalTickets,releaseRate,retrievalRate,maxCapacity);

        TicketPool ticketkpool= new TicketPool(maxCapacity);

        System.out.println("System configured successfully.");*/
