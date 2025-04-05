import java.util.*;

// Main system class for Ghost Airways ticket management
public class TicketSystem {
    // Stores all created tickets
    private List<Ticket> tickets = new ArrayList<>();
    // Scanner for user input
    private Scanner scanner = new Scanner(System.in);

    // Main system loop - displays menu and processes user choices
    public void run() {
        while (true) {
            // Display main menu options
            System.out.println("\nGHOST AIRWAYS TICKETING SYSTEM");
            System.out.println("1. Add New Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Search Tickets");
            System.out.println("4. Exit");
            System.out.print("Select option: ");

            // Get and validate user choice
            int choice = getValidChoice(4);

            // Process menu selection
            switch (choice) {
                case 1:
                    addTicket();  // Create new ticket
                    break;
                case 2:
                    displayAllTickets();  // Show all tickets
                    break;
                case 3:
                    searchTickets();  // Search tickets
                    break;
                case 4:
                    System.out.println("Thank you for using Ghost Airways!");
                    return;  // Exit program
            }
        }
    }

    // Creates and adds a new ticket to the system
    private void addTicket() {
        // Step 1: Select ticket class
        Ticket ticket = createTicket();
        if (ticket == null) return;  // Exit if invalid selection

        // Step 2: Offer class-specific options
        ticket.offerOptions(scanner);

        // Step 3: Offer general upgrades
        offerUpgrades(ticket);

        // Add completed ticket to collection
        tickets.add(ticket);
        System.out.println("\nTICKET CREATED:\n" + ticket);
    }

    // Creates a ticket based on selected class
    private Ticket createTicket() {
        System.out.println("\nSELECT CLASS:");
        System.out.println("1. Steerage");
        System.out.println("2. Coach");
        System.out.println("3. Business");
        System.out.println("4. First");
        System.out.print("Your choice: ");

        int choice = getValidChoice(4);

        // Instantiate appropriate ticket class
        switch (choice) {
            case 1: return new SteerageTicket();
            case 2: return new CoachTicket();
            case 3: return new BusinessTicket();
            case 4: return new FirstTicket();
            default: return null;
        }
    }

    // Offers available upgrades for any ticket class
    private void offerUpgrades(Ticket ticket) {
        System.out.println("\nAVAILABLE UPGRADES:");
        System.out.println("1. Thrill Seeker (Boeing 737 MAX)");
        System.out.println("2. Wingman (strapped to wing)");
        System.out.println("3. No upgrades");
        System.out.print("Your choice: ");

        int choice = getValidChoice(3);

        // Add selected upgrade
        if (choice == 1) {
            ticket.addUpgrade("Thrill Seeker");
        } else if (choice == 2) {
            ticket.addUpgrade("Wingman");
            selectWing(ticket);  // Additional option for Wingman
        }
    }

    // Handles wing selection for Wingman upgrade
    private void selectWing(Ticket ticket) {
        System.out.println("\nSELECT WING:");
        System.out.println("1. Left Wing");
        System.out.println("2. Right Wing");
        System.out.println("3. No Preference");
        System.out.print("Your choice: ");

        int choice = getValidChoice(3);

        // Add wing selection as an option
        if (choice == 1) {
            ticket.addOption("Left Wing Selection");
        } else if (choice == 2) {
            ticket.addOption("Right Wing Selection");
        }
    }

    // Displays all tickets sorted by class and options
    private void displayAllTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets available!");
            return;
        }

        // Sort tickets using Ticket's compareTo method
        Collections.sort(tickets);

        System.out.println("\nALL TICKETS:");
        for (Ticket t : tickets) {
            System.out.println(t + "\n---");  // Uses Ticket's toString()
        }
    }

    // Searches tickets based on criteria
    private void searchTickets() {
        System.out.println("\nSEARCH BY:");
        System.out.println("1. Thrill Seeker Upgrade");
        System.out.println("2. Wingman Upgrade");
        System.out.println("3. No Options/Upgrades");
        System.out.print("Your choice: ");

        int choice = getValidChoice(3);
        List<Ticket> results = new ArrayList<>();

        // Filter tickets based on search criteria
        for (Ticket t : tickets) {
            switch (choice) {
                case 1:  // Thrill Seeker
                    if (t.getUpgrades().contains("Thrill Seeker")) {
                        results.add(t);
                    }
                    break;
                case 2:  // Wingman
                    if (t.getUpgrades().contains("Wingman")) {
                        results.add(t);
                    }
                    break;
                case 3:  // Basic tickets
                    if (t.getOptions().isEmpty() && t.getUpgrades().isEmpty()) {
                        results.add(t);
                    }
                    break;
            }
        }

        // Display search results
        if (results.isEmpty()) {
            System.out.println("No matching tickets found!");
        } else {
            System.out.println("\nSEARCH RESULTS:");
            for (Ticket t : results) {
                System.out.println(t + "\n---");
            }
        }
    }

    // Validates user input is within allowed range
    private int getValidChoice(int maxOption) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                }
                System.out.print("Please enter 1-" + maxOption + ": ");
            } catch (Exception e) {
                scanner.nextLine();  // Clear invalid input
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}