# Test-Automation-Homework-Week-3

On the suggestion of my mentor and also for the latest homework I've done, I made a ton of changes to my code. Therefore, I will only include code in this readme related to prompts for the latest homework I'ved done. This was the prompt:

2. Practical task:
Add 5 interfaces to the existing hierarchy.
Use polymorphism with the abstract class and interface from the hierarchy.
Create final class, method, variable.
Create a static block, method, variable.

I renamed Crime.java as "ICrime.java", making it into an interface. I initially used final static variables for it, but decided to use enums instead on the suggestion of my mentor. I also made a class called Crime.java.

```
public interface ICrime {
    String getDescription();
    
    enum Severity {
        LOW(1),
        MEDIUM(2),
        HIGH(3);
        
        private int value;
        
        Severity(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    void setSeverity(Severity severity);
    Severity getSeverity();
}
```

Crime.java
```
public class Crime implements ICrime {
    private String description;
    private Severity severity;
    private Type type;

    public Crime(Type type) {
        setType(type);
    }

    enum Type {
        THEFT("Taking someone's property without their consent.", Severity.LOW),
        ASSAULT("Physically attacking someone, causing them harm.", Severity.LOW),
        BURGLARY("Entering a building illegally to commit a crime.", Severity.MEDIUM),
        FRAUD("Deceiving someone to obtain money or property.", Severity.MEDIUM),
        DRUG_POSSESSION("Illegally possessing controlled substances.", Severity.MEDIUM),
        MURDER("Intentionally killing another person.", Severity.HIGH),
        JAVA_INSTR("The vile act of teaching the Java programming language", Severity.MEDIUM);

        private final String description;
        private final Severity severity;

        Type(String description, Severity severity) {
            this.description = description;
            this.severity = severity;
        }

        public String getDescription() {
            return description;
        }

        public Severity getSeverity() {
            return severity;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        this.description = type.getDescription();
        this.severity = type.getSeverity();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
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
        crimes1.add(new Crime(Crime.Type.JAVA_INSTR));
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

At the the suggestion of my mentor, I refactored my code for Person.java so that the variable "age" would actually be used and would automatically be calculated and stored when the date of birth is set.
```
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Person {
    private String name;
    private LocalDate DOB;
    private String address;
    static private Period age;

    public Person (String name, String DOB, String address) {
        this.name = name;
        setDOB(DOB);
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age.getYears();
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(String inputDOB) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(inputDOB, formatter);
        this.DOB = date;
        LocalDate currentDate = LocalDate.now();
        age = Period.between(DOB, currentDate);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract String getProfile();
}
```

# Part 2
The prompt for my homework was:

 2. Practical tasks:
Create 5 custom exceptions.
Handle exceptions in 2 ways.
Use try-catch with resources.
Log messages to the console, file.
libraries of log4j could be downloaded manually from maven central. here and here (you'll need both of them at the same time); add them to your project in IDE (e.g. sample of instruction for Intellj Idea)

I was unable to get my homework to work, sadly. I spent most of the day trying to get log4j to work for a new class I was trying to create that would log everything in a file for me, but it's still a work in progress.

I also already created some custom exceptions in Jail.java that I’m quite happy with :slightly_smiling_face: In addition to adding exceptions in different parts of my program, such as Person.java, to catch any basic errors like improper inputs. I think it’s improved the sorta command line UI of my program, which makes me happy.

Here's my new and improved Jail.java code, for example:

```
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

public class Jail {
    private ArrayList<Criminal> inmates = new ArrayList<>();
    private int capacity;
    protected static int totalJails;

    public Jail(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater than zero!");
        }
        this.capacity = capacity;
        totalJails++;
        JailList.addJail(this);
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

    public boolean removeInmate(Criminal criminal) throws InmateNotFoundException {
        if (inmates.remove(criminal)) {
            return true;
        }
    
        System.out.println("The specified inmate was not found in this jail.");
        System.out.print("Do you want to move the inmate here? (yes/no): ");
    
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
    
            if (input.equalsIgnoreCase("yes")) {
                if (Jail.HoldingCell.getInmates().contains(criminal)) {
                    Jail.HoldingCell.removeInmate(criminal);
                    addInmate(criminal);
                    System.out.println("The inmate has been moved to this jail from the Holding Cell.");
                    return true;
                } else {
                    for (Jail jail : JailList.getJails()) {
                        if (jail.getInmates().contains(criminal)) {
                            jail.removeInmate(criminal);
                            addInmate(criminal);
                            System.out.println("The inmate has been moved to this jail from another jail.");
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Do nothing
        }
        throw new InmateNotFoundException();
    }    
    
    public class InmateNotFoundException extends Exception {
        public InmateNotFoundException() {
            super("The specified inmate was not found in this jail.");
        }
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

    public class JailList {
        private static List<Jail> jails = new ArrayList<>();
    
        public static void addJail(Jail jail) {
            jails.add(jail);
        }
    
        public static List<Jail> getJails() {
            return jails;
        }
    }
}
```
