package persistence.serialized;

import model.Person;
import persistence.PersonDAO;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

public class SerializedPersonDAO implements PersonDAO {
    /**
     * The path to the file that will be used for loading and saving
     * the data.
     */
    private Path path;

    /**
     * Create a new data access object with the given file.
     *
     * @param path Path to the text file
     */
    public SerializedPersonDAO(Path path) {
        this.path = path;
    }

    /**
     * Save a list of people to the file as serialized objects.
     *
     * @param people The list of people
     */
    public void save(List<Person> people) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(this.path));
            outputStream.writeObject(new ArrayList<>(people));
            outputStream.close();
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
            ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(this.path));
            people = (ArrayList<Person>) inputStream.readObject();
            inputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return people;
    }
}
