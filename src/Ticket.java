import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Ticket implements Comparable<Ticket> {
    protected List<String> options;
    protected List<String> upgrades;
    protected List<String> complementaryPerks;

    public Ticket() {
        this.options = new ArrayList<>();
        this.upgrades = new ArrayList<>();
        this.complementaryPerks = new ArrayList<>();
    }

    public void addOption(String option) {
        options.add(option);
    }

    public void addUpgrade(String upgrade) {
        upgrades.add(upgrade);
    }

    public List<String> getOptions() {
        return options;
    }

    public List<String> getUpgrades() {
        return upgrades;
    }

    public List<String> getComplementaryPerks() {
        return complementaryPerks;
    }

    public abstract String getPassengerClass();
    public abstract void offerOptions(Scanner scanner);

    @Override
    public int compareTo(Ticket other) {
        String[] classOrder = {"Steerage", "Coach", "Business", "First"};

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

        if (thisClassIndex != otherClassIndex) {
            return Integer.compare(thisClassIndex, otherClassIndex);
        }

        return Integer.compare(
                this.options.size() + this.upgrades.size(),
                other.options.size() + other.upgrades.size()
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Passenger Class: ").append(getPassengerClass()).append("\n");

        if (!complementaryPerks.isEmpty()) {
            sb.append("Complementary Perks:\n");
            for (String perk : complementaryPerks) {
                sb.append(" - ").append(perk).append("\n");
            }
        }

        if (!options.isEmpty()) {
            sb.append("Selected Options:\n");
            for (String option : options) {
                sb.append(" - ").append(option).append("\n");
            }
        }

        if (!upgrades.isEmpty()) {
            sb.append("Selected Upgrades:\n");
            for (String upgrade : upgrades) {
                sb.append(" - ").append(upgrade).append("\n");
            }
        }

        sb.append("Total Options Selected: ").append(options.size() + upgrades.size());
        return sb.toString();
    }
}