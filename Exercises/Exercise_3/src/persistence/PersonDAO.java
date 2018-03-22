package persistence;

import model.Person;

import java.util.List;

public interface PersonDAO {
    void save(List<Person> people) throws PersistenceException;
    List<Person> load() throws PersistenceException;
}
