package interfaces.impls;

import interfaces.Pressable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Person;

public class CollectionAddressBook implements Pressable {

    private ObservableList<Person> persons = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonList() {
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

    public void printAddressBook() {
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }


    public void fillTestData() {
        Person person1 = new Person("Den", "+375(29)1234567");
        Person person2 = new Person();
        person2.setFio("Helena");
        person2.setPhone("+375445678923");
        Person person3 = new Person("Jon", "+375102343244232");
        Person person4 = new Person("Marks", "+375291111111");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(new Person("Mike", "+375291234566"));
    }
}
