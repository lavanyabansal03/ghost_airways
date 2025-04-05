import java.util.Scanner;

public class SteerageTicket extends Ticket {
    @Override
    public String getPassengerClass() {
        return "Steerage";
    }

    @Override
    public void offerOptions(Scanner scanner) {
        System.out.println("\nSteerage Class Options:");
        System.out.println("1. Sit on available cargo (additional fee)");
        System.out.println("2. No options");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            addOption("Sit on available cargo");
        }
    }
}