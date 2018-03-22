package persistence.textfile;

import model.Person;
import persistence.PersistenceException;
import persistence.PersonDAO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFilePersonDAO implements PersonDAO {
    /**
     * The path to the text file that will be used for loading and saving
     * the data.
     */
    private Path path;

    /**
     * Create a new data access object with the given file.
     *
     * @param path Path to the text file
     */
    public TextFilePersonDAO(Path path) {
        this.path = path;
    }

    /**
     * Save a list of people to the next file.
     *
     * @param people The list of people
     */
    public void save(List<Person> people) throws PersistenceException {
        try (BufferedWriter writer = Files.newBufferedWriter(this.path)) {
            for(Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }
        } catch(IOException e) {
            throw new PersistenceException(e);
        }

    }

    /**
     * Loads the list of people from a text file.
     *
     * @return The list of people
     */
    public List<Person> load() throws PersistenceException {
        List<Person> people = new ArrayList<>();

        try {
            List<String> peopleList = Files.readAllLines(this.path);

            for(int i = 0; i < peopleList.size(); i++) {
                try {
                    people.add(new Person(peopleList.get(i)));
                } catch(IllegalArgumentException e) {
                    throw new TextFilePersistenceException(this.path.toString(), i + 1, peopleList.get(i), e);
                }
            }
        } catch(IOException e) {
            throw new PersistenceException(e);
        }

        return people;
    }
}
