import java.util.Scanner;

public class CoachTicket extends Ticket {
    @Override
    public String getPassengerClass() {
        return "Coach";
    }

    @Override
    public void offerOptions(Scanner scanner) {
        System.out.println("\nCOACH OPTIONS:");
        System.out.println("1. Handrail (additional fee)");
        System.out.println("2. Carry-on bag (additional fee)");
        System.out.println("3. No options");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (choice == 1) {
            addOption("Handrail");
            System.out.println("Added: Handrail");
        } else if (choice == 2) {
            addOption("Carry-on bag");
            System.out.println("Added: Carry-on bag");
        }
    }
}