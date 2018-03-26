
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "person")
@XmlType(propOrder = {"name", "firstName", "dateOfBirth", "maritalStatus"})
public class Person implements Serializable {
    /**
     * The last name of the person.
     */
    private String name;

    /**
     * The first name of the person.
     */
    private String firstName;

    /**
     * The date of birth of the person.
     */
    private LocalDate dateOfBirth;
    /**
     * The current marital status of the person.
     */
    private MaritalStatus maritalStatus;

    /**
     * This creates an empty person object.
     */
    public Person() {

    }

    /**
     * Creates a new person object.
     *
     * @param name          The last name of the person
     * @param firstName     The first name of the person
     * @param dateOfBirth   The date of birth of the person
     * @param maritalStatus The marital status of the person
     */
    public Person(String name, String firstName, LocalDate dateOfBirth, MaritalStatus maritalStatus) {
        this.name = name;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }

    /**
     * Creates a new person object from a comma separated string of values.
     *
     * @param personLine The string of values
     */
    public Person(String personLine) {
        String[] values = personLine.split(",?\\s");

        if (values.length != 4) {
            throw new IllegalArgumentException("Invalid number of values");
        }

        this.name = values[0];
        this.firstName = values[1];
        this.dateOfBirth = LocalDate.parse(values[2]);
        this.maritalStatus = MaritalStatus.valueOf(values[3]);
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
     * Sets the name.
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the first name.
     *
     * @param firstName The first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Sets the date of birth.
     *
     * @param dateOfBirth The date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the marital status.
     *
     * @return The marital status
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the martial status.
     *
     * @param maritalStatus The marital status
     */
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return this.name + " " + this.firstName + ", " + this.dateOfBirth + ", " + this.maritalStatus;
    }
}
