import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "persons")
public class People {

    @XmlElement(name = "person")
    public List<Person> people;

    public People() {
        this.people = new ArrayList<>();
    }

    public void add(Person person) {
        people.add(person);
    }
}
