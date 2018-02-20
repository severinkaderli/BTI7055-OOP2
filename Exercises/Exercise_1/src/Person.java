import java.time.LocalDate;

public class Person {
    /**
     * The last name of the person.
     */
    String name;

    /**
     * The first name of the person.
     */
    String firstName;

    /**
     * The date of birth of the person.
     */
    LocalDate dateOfBirth;

    /**
     * The current marital status of the person.
     */
    MaritalStatus maritalStatus;

    /**
     * Creates a new person object.
     *
     * @param name The last name of the person
     * @param firstName The first name of the person
     * @param dateOfBirth The date of birth of the person
     * @param maritalStatus The marital status of the person
     */
    public Person(String name, String firstName, LocalDate dateOfBirth, MaritalStatus maritalStatus) {
        this.name = name;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }

    /**
     * Returns the last name.
     *
     * @return The last name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the first name.
     *
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the date of birth.
     *
     * @return The date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns the marital status.
     *
     * @return The marital status
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    @Override
    public String toString() {
        return this.name + " " + this.firstName + ", " + this.dateOfBirth + ", " + this.maritalStatus;
    }
}
