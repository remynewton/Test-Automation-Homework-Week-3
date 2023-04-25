# Test-Automation-Homework-Week-3

On the suggestion of my mentor and also for the latest homework I've done, I made a ton of changes to my code. Therefore, I will only include code in this readme related to prompts for the latest homework I'ved done. This was the prompt:

2. Practical task:
Add 5 interfaces to the existing hierarchy.
Use polymorphism with the abstract class and interface from the hierarchy.
Create final class, method, variable.
Create a static block, method, variable.

I made Crime.java into an interface and added a bunch of static variables to it. I also made classes for all the crimes listed.

```
public interface Crime {
    public static final String THEFT = "Theft";
    public static final String ASSAULT = "Assault";
    public static final String BURGLARY = "Burglary";
    public static final String FRAUD = "Fraud";
    public static final String DRUG_POSSESSION = "Drug Possession";
    public static final String MURDER = "Murder";
    public static final String JAVA_INSTR = "Java Instruction";

    public static final int LOW_SEVERITY = 1;
    public static final int MEDIUM_SEVERITY = 2;
    public static final int HIGH_SEVERITY = 3;

    public static final String THEFT_DESC = "Taking someone's property without their consent.";
    public static final String ASSAULT_DESC = "Physically attacking someone, causing them harm.";
    public static final String BURGLARY_DESC = "Entering a building illegally to commit a crime.";
    public static final String FRAUD_DESC = "Deceiving someone to obtain money or property.";
    public static final String DRUG_POSSESSION_DESC = "Illegally possessing controlled substances.";
    public static final String MURDER_DESC = "Intentionally killing another person.";
    public static final String JAVA_INSTR_DESC = "The vile act of teaching the Java programming language";
    
    public String getDescription();
    public void setSeverity(int severity);
    public int getSeverity();
}
```

I created a final static class in Jail.java called HoldingCell, which fixes the problem encountered when a given jail is at full capacity and one tries to add an inmate to said jail with a nice little try exception block and a class that extends Exception. It also includes it's own static method called "addInmate". At the suggestion of my mentor, I've also added an int variable called totalJails that I can use to keep track of the jails.

```
import java.util.ArrayList;

public class Jail {
    private ArrayList<Criminal> inmates = new ArrayList<>();
    private int capacity;
    protected static int totalJails;

    public Jail(int capacity) {
        this.capacity = capacity;
        totalJails++;
    }

    public ArrayList<Criminal> getInmates() {
        return inmates;
    }

    public void setInmates(ArrayList<Criminal> inmates) {
        this.inmates = inmates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addInmate(Criminal criminal) {
        try {
            if (inmates.size() < capacity) {
                inmates.add(criminal);
            } else {
                throw new JailFullException();
            }
        } catch (JailFullException e) {
            System.out.println(e.getMessage());
            HoldingCell.addInmate(criminal);
        }
    }    

    public boolean removeInmate(Criminal criminal) {
        return inmates.remove(criminal);
    }

    public class JailFullException extends Exception {
        public JailFullException() {
            super("This jail is at full capacity! The inmate will be kept in holding for now.");
        }
    }    

    public static final class HoldingCell {
        private static ArrayList<Criminal> inmates = new ArrayList<>();
        private static int capacity;
    
        public static ArrayList<Criminal> getInmates() {
            return inmates;
        }
    
        public static void setInmates(ArrayList<Criminal> inmates) {
            HoldingCell.inmates = inmates;
        }
    
        public int getCapacity() {
            return capacity;
        }
    
        public void setCapacity(int capacity) {
            HoldingCell.capacity = capacity;
        }
    
        public static boolean addInmate(Criminal criminal) {
            if (inmates.size() < capacity) {
                inmates.add(criminal);
                return true;
            }
            return false;
        }
    
        public static boolean removeInmate(Criminal criminal) {
            return inmates.remove(criminal);
        }
    }
}
```

I added a final method to PoliceStation.java called printReport:

```
import java.util.*;

class PoliceStation {

    protected List<Case> solvedCases = new ArrayList<Case>();

    public void addCase(Case c) {
        solvedCases.add(c);
    }

    public final void printReport() {
        for (Case c : solvedCases) {
            System.out.println("Case Report:");
            System.out.println("  Description: " + c.getDescription());
            System.out.println("  Officer: " + c.getAssignedOfficer().getName() + " (" + c.getAssignedOfficer().getRank() + ")");
            System.out.println("  Criminal: " + c.getSuspect().getName() + " (" + c.getSuspect().getAddress() + ")");
            System.out.println("  Victim: " + c.getVictim().getName() + " (" + c.getVictim().getAddress() + ")");
            System.out.println("  Outcome: " + (c.checkSolved() ? "Solved" : "Unsolved"));
        }
    }

    public static void main(String[] args) {
        Officer officer1 = new Officer("John Doe", "06/12/1981", "123 Main St", 12345, "Patrol");
        List<String> trainings1 = Arrays.asList("Patrol");
        PoliceDog patroldog1 = new PoliceDog("Fido", "05/17/2019", true, "German Shepherd", trainings1);
        ArrayList<Crime> crimes1 = new ArrayList<>();
        crimes1.add(new JavaInstruction());
        Criminal criminal1 = new Criminal("Andrei Trukhanovich", "07/17/1991", "456 Elm St", crimes1);
        Victim victim1 = new Victim("Remy Newton", "05/22/1997", "789 Oak Ave", "9876");
        Case case1 = new Case("repeated Java instruction", officer1, criminal1, victim1, false);
        Jail jail1 = new Jail(50);

        System.out.println("Officer " + officer1.getName() + " from " + officer1.getRank() + " department is investigating a case of " + case1.getDescription() + ". That's " + officer1.getProfile() + ".");
        System.out.println("The victim of the crime is " + victim1.getProfile() + ".");
        System.out.println("Officer " + officer1.getName() + " uses his trusty police dog " + patroldog1.getName() + " to patrol the area for the criminal.");
        patroldog1.patrol();
        System.out.println("The officer has apprehended the criminal. " + criminal1.getProfile());
        jail1.addInmate(criminal1);
        System.out.println(patroldog1.getName() + " gets a treat.");
        String verb;
        if (jail1.getInmates().size() > 1) {
            verb = "are";

        } else {
            verb = "is";
        }
        System.out.println("There " + verb + " now " + jail1.getInmates().size() + " inmate(s) in the jail.");
    }
}
```

I also created a bunch of interfaces that I'm using to keep track of the trainings for police dogs and provide abstract methods with polymorphism thereof:

```
import java.util.*;

public class PoliceDog extends Beast implements SearchAndRescue, Detection, Patrol, Cadaver {
    public String breed;
    protected List<String> trainings;

    public PoliceDog(String name, String DOB, boolean furry, String breed, List<String> trainings) {
        super(name, DOB, furry);
        this.breed = breed;
        this.trainings = trainings;
    }

    @Override
    public void searchAndRescue() {
        if (trainings.contains("Search and Rescue")) {
            System.out.println("Performing search and rescue operation...");
        } else {
            System.out.println("This dog is not trained for search and rescue.");
        }
    }

    @Override
    public void detect() {
        if (trainings.contains("Detection")) {
            System.out.println("Detecting for drugs and explosives...");
        } else {
            System.out.println("This dog is not trained for detection.");
        }
    }

    @Override
    public void patrol() {
        if (trainings.contains("Patrol")) {
            System.out.println("Patrolling the area...");
        } else {
            System.out.println("This dog is not trained for patrol.");
        }
    }

    @Override
    public void findCadaver() {
        if (trainings.contains("Cadaver")) {
            System.out.println("Searching for cadaver...");
        } else {
            System.out.println("This dog is not trained for cadaver detection.");
        }
    }
}
```
