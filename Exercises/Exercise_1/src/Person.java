import java.time.LocalDate;

public class Person {

    String name;
    String firstName;

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    LocalDate dateOfBirth;
    MaritalStatus maritalStatus;

    public Person(String name, String firstName, LocalDate dateOfBirth, MaritalStatus maritalStatus) {
        this.name = name;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return this.name + " " + this.firstName + ", " + this.dateOfBirth + ", " + this.maritalStatus;
    }
}
