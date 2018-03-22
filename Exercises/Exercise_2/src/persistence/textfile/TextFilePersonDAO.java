package persistence.textfile;

import model.Person;
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
    public void save(List<Person> people) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(this.path);

            for(Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads the list of people from a text file.
     *
     * @return The list of people
     */
    public List<Person> load() {
        List<Person> people = new ArrayList<>();

        try {
            List<String> peopleList = Files.readAllLines(this.path);

            for(String personString : peopleList) {
                people.add(new Person(personString));
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return people;
    }
}
