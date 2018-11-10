package interfaces.impls;

import interfaces.Pressable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Person;

/**
 * Этот класс является коллекцией.
 * Содержит в себе, обертывает, ObservableList persons.
 * @see Pressable интерфейс завязывает действия графического интерфейса и данных содержащихся в коллекции,
 * т.е. добавить, изменить, удалить.
 */
public class CollectionAddressBook implements Pressable {
        private ObservableList<Person> persons = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonList() {
        return persons;
    }

    @Override
    public void add(Person person) {
        if (person.getFio() != null && person.getPhone() != null) {
            persons.add(person);
        }
    }

    @Override
    public void update(Person person) {
        persons.add(person);
    }

    @Override
    public void delete(Person person) {
        persons.remove(person);
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
