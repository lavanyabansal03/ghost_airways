import java.util.Scanner;

public class BusinessTicket extends Ticket {
    @Override
    public String getPassengerClass() {
        return "Business";
    }

    @Override
    public void offerOptions(Scanner scanner) {
        System.out.println("\nBUSINESS OPTIONS:");
        System.out.println("1. Window or aisle seat (additional fee)");
        System.out.println("2. Overhead bin storage (additional fee)");
        System.out.println("3. No options");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            this.addOption("Window or aisle seat");
            System.out.println("Added: Window or aisle seat");
        } else if (choice == 2) {
            this.addOption("Overhead bin storage");
            System.out.println("Added: Overhead bin storage");
        }
    }
}