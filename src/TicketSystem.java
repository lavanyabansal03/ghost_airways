import java.util.*;

public class TicketSystem {
    private List<Ticket> tickets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\nGHOST AIRWAYS TICKETING SYSTEM");
            System.out.println("1. Add New Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Search Tickets");
            System.out.println("4. Exit");
            System.out.print("Select option: ");

            int choice = getValidChoice(4);

            switch (choice) {
                case 1:
                    addTicket();
                    break;
                case 2:
                    displayAllTickets();
                    break;
                case 3:
                    searchTickets();
                    break;
                case 4:
                    System.out.println("Thank you for using Ghost Airways!");
                    return;
            }
        }
    }

    private void addTicket() {
        Ticket ticket = createTicket();
        if (ticket == null) return;

        // Offer class-specific options
        ticket.offerOptions(scanner);

        // Offer upgrades
        offerUpgrades(ticket);

        tickets.add(ticket);
        System.out.println("\nTICKET CREATED:\n" + ticket);
    }

    private Ticket createTicket() {
        System.out.println("\nSELECT CLASS:");
        System.out.println("1. Steerage");
        System.out.println("2. Coach");
        System.out.println("3. Business");
        System.out.println("4. First");
        System.out.print("Your choice: ");

        int choice = getValidChoice(4);

        switch (choice) {
            case 1: return new SteerageTicket();
            case 2: return new CoachTicket();
            case 3: return new BusinessTicket();
            case 4: return new FirstTicket();
            default: return null;
        }
    }

    private void offerUpgrades(Ticket ticket) {
        System.out.println("\nAVAILABLE UPGRADES:");
        System.out.println("1. Thrill Seeker (Boeing 737 MAX)");
        System.out.println("2. Wingman (strapped to wing)");
        System.out.println("3. No upgrades");
        System.out.print("Your choice: ");

        int choice = getValidChoice(3);

        if (choice == 1) {
            ticket.addUpgrade("Thrill Seeker");
        } else if (choice == 2) {
            ticket.addUpgrade("Wingman");
            selectWing(ticket);
        }
    }

    private void selectWing(Ticket ticket) {
        System.out.println("\nSELECT WING:");
        System.out.println("1. Left Wing");
        System.out.println("2. Right Wing");
        System.out.println("3. No Preference");
        System.out.print("Your choice: ");

        int choice = getValidChoice(3);

        if (choice == 1) {
            ticket.addOption("Left Wing Selection");
        } else if (choice == 2) {
            ticket.addOption("Right Wing Selection");
        }
    }

    private void displayAllTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets available!");
            return;
        }

        Collections.sort(tickets);
        System.out.println("\nALL TICKETS:");
        for (Ticket t : tickets) {
            System.out.println(t + "\n---");
        }
    }

    private void searchTickets() {
        System.out.println("\nSEARCH BY:");
        System.out.println("1. Thrill Seeker Upgrade");
        System.out.println("2. Wingman Upgrade");
        System.out.println("3. No Options/Upgrades");
        System.out.print("Your choice: ");

        int choice = getValidChoice(3);
        List<Ticket> results = new ArrayList<>();

        for (Ticket t : tickets) {
            switch (choice) {
                case 1:
                    if (t.getUpgrades().contains("Thrill Seeker")) {
                        results.add(t);
                    }
                    break;
                case 2:
                    if (t.getUpgrades().contains("Wingman")) {
                        results.add(t);
                    }
                    break;
                case 3:
                    if (t.getOptions().isEmpty() && t.getUpgrades().isEmpty()) {
                        results.add(t);
                    }
                    break;
            }
        }

        if (results.isEmpty()) {
            System.out.println("No matching tickets found!");
        } else {
            System.out.println("\nSEARCH RESULTS:");
            for (Ticket t : results) {
                System.out.println(t + "\n---");
            }
        }
    }

    private int getValidChoice(int maxOption) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                }
                System.out.print("Please enter 1-" + maxOption + ": ");
            } catch (Exception e) {
                scanner.nextLine();
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}