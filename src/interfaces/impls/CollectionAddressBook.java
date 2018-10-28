package interfaces.impls;

import interfaces.Pressable;
import objects.Person;

import java.util.ArrayList;

public class CollectionAddressBook implements Pressable {

    private ArrayList<Person> persons = new ArrayList<Person>();

    public ArrayList<Person> getPersons() {
        return persons;
    }

    @Override
    public void add(Person person) {
        persons.add(person);
    }

    @Override
    public void update(Person person) {
        persons.add(person);
    }

    @Override
    public void delete(Person person) {
        persons.remove(person);
    }
}
