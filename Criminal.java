import java.util.ArrayList;
import java.util.StringJoiner;

public class Criminal extends Person {
    private ArrayList<ICrime> crimes;
    private int crimeCount;

    public Criminal(String name, String DOB, String address, ArrayList<ICrime> crimes) {
        super(name, DOB, address);
        this.crimes = crimes;
        this.crimeCount = crimes.size();
    }

    public ArrayList<ICrime> getCrimes() {
        return crimes;
    }

    public int getCrimeCount() {
        return crimeCount;
    }

    @Override
    public String toString() {
        return getProfile();
    }
    
    @Override
    public int hashCode() {
        return getProfile().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Criminal) {
            Criminal otherCriminal = (Criminal) obj;
            return getProfile().equals(otherCriminal.getProfile());
        }
        return false;
    }
    
    @Override
    public String getProfile() {
        StringJoiner joiner = new StringJoiner(", ");
        for (ICrime crime : crimes) {
            joiner.add(crime.getDescription() + " (Severity: " + crime.getSeverity() + ")");
        }
        return "The criminal " + getName() + " has committed " + crimeCount + " crime(s), including: " + joiner.toString() + ".";
    }
}