import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    public void setDOB(String inputDOB) throws DateTimeParseException {
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