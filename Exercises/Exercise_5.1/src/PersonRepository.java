import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.time.LocalDate;

public class PersonRepository {

    public static void main(String[] args) {
        People people = new People();
        people.add(new Person("Kaderli", "Severin", LocalDate.now(), MaritalStatus.CIVIL_PARTNERSHIP));
        people.add(new Person("Schär", "Marius", LocalDate.now(), MaritalStatus.SINGLE));

        try {
            JAXBContext context = JAXBContext.newInstance(People.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(people, System.out);
        } catch(JAXBException e) {
            e.printStackTrace();
        }

    }
}
