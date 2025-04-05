import java.util.Scanner;

public class FirstTicket extends Ticket {
    @Override
    public String getPassengerClass() {
        return "First";
    }

    @Override
    public void offerOptions(Scanner scanner) {
        System.out.println("\nFIRST CLASS OPTIONS:");
        System.out.println("1. Yoke and rudder pedals (additional fee)");
        System.out.println("2. No options");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            this.addOption("Yoke and rudder pedals");
            System.out.println("Added: Yoke and rudder pedals");
        }
    }
}