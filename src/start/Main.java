package start;

import interfaces.impls.CollectionAddressBook;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Person;

public class Main extends Application {
    private CollectionAddressBook addressBook = new CollectionAddressBook();


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        primaryStage.setTitle("Phone book");
        primaryStage.setScene(new Scene(root, 600, 540));
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.show();

        testData();


    }

    private void testData() {

        Person person1 = new Person("Den", "+375(29)1234567");

        Person person2 = new Person();
        person2.setName("Elena");
        person2.setPhone("+375445678923");

        Person person3 = new Person("Jon", "+375102343244232");
        Person person4 = new Person("Maks", "+375291111111");

        addressBook.add(person1);
        addressBook.add(person2);
        addressBook.add(person3);
        addressBook.add(person4);


    }

    private void testingInterface() {
        //addressBook.update(person1);
        //addressBook.delete(person1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
