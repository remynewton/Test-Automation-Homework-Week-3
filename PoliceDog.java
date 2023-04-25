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