import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class for all ticket types
public abstract class Ticket implements Comparable<Ticket> {
    // Lists to store ticket features
    protected List<String> options;         // Paid options selected
    protected List<String> upgrades;        // Upgrades purchased
    protected List<String> complementaryPerks; // Free perks included

    // Constructor initializes empty lists
    public Ticket() {
        this.options = new ArrayList<>();
        this.upgrades = new ArrayList<>();
        this.complementaryPerks = new ArrayList<>();
    }

    // Add a paid option to the ticket
    public void addOption(String option) {
        options.add(option);
    }

    // Add an upgrade to the ticket
    public void addUpgrade(String upgrade) {
        upgrades.add(upgrade);
    }

    // Getter methods
    public List<String> getOptions() {
        return options;
    }

    public List<String> getUpgrades() {
        return upgrades;
    }

    public List<String> getComplementaryPerks() {
        return complementaryPerks;
    }

    // Abstract methods each ticket class must implement
    public abstract String getPassengerClass(); // Returns class name
    public abstract void offerOptions(Scanner scanner); // Shows class-specific options

    // Compare tickets first by class, then by number of options+upgrades
    @Override
    public int compareTo(Ticket other) {
        // Define class priority order
        String[] classOrder = {"Steerage", "Coach", "Business", "First"};

        // Find index positions for comparison
        int thisClassIndex = -1;
        int otherClassIndex = -1;

        for (int i = 0; i < classOrder.length; i++) {
            if (classOrder[i].equals(this.getPassengerClass())) {
                thisClassIndex = i;
            }
            if (classOrder[i].equals(other.getPassengerClass())) {
                otherClassIndex = i;
            }
        }

        // Compare by class priority first
        if (thisClassIndex != otherClassIndex) {
            return Integer.compare(thisClassIndex, otherClassIndex);
        }

        // If same class, compare by total options+upgrades
        return Integer.compare(
                this.options.size() + this.upgrades.size(),
                other.options.size() + other.upgrades.size()
        );
    }

    // Format ticket details as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Passenger Class: ").append(getPassengerClass()).append("\n");

        // Show complementary perks if any
        if (!complementaryPerks.isEmpty()) {
            sb.append("Complementary Perks:\n");
            for (String perk : complementaryPerks) {
                sb.append(" - ").append(perk).append("\n");
            }
        }

        // Show selected options if any
        if (!options.isEmpty()) {
            sb.append("Selected Options:\n");
            for (String option : options) {
                sb.append(" - ").append(option).append("\n");
            }
        }

        // Show upgrades if any
        if (!upgrades.isEmpty()) {
            sb.append("Selected Upgrades:\n");
            for (String upgrade : upgrades) {
                sb.append(" - ").append(upgrade).append("\n");
            }
        }

        // Show total count of options+upgrades
        sb.append("Total Options Selected: ").append(options.size() + upgrades.size());
        return sb.toString();
    }
}