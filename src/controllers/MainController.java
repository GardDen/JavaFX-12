package controllers;

import interfaces.impls.CollectionAddressBook;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Person;

import java.io.IOException;

public class MainController {
    public CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    public Button deleteButton;

    @FXML
    public Button editButton;

    @FXML
    public Button searchButton;

    @FXML
    public Label countLabel;

    @FXML
    private Button addButton;

    @FXML
    private TableView tableAdressBook;

    @FXML
    private TableColumn<Person, String> columnFIO;

    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    private void initialize() {
        //tableAdressBook.getSelectionModel().getSelectionMode(SelectionMode.MULTIPLE);

        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        addressBookImpl.fillTestData();
        tableAdressBook.setItems(addressBookImpl.getPersonList());
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });
    }

    private void updateCountLabel() {
        countLabel.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }

    public void showDialog(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        Person selectedPerson = (Person) tableAdressBook.getSelectionModel().getSelectedItem();

        switch (clickedButton.getId()) {
            case "addButton":
                System.out.println("add " + selectedPerson);
                break;
            case "editButton":
                System.out.println("edit " + selectedPerson);
                break;
            case "deleteButton":
                System.out.println("delete" + selectedPerson);
                break;
        }

        try {
            addButton.setText("Заблокировано!");
            Stage stage = new Stage();
            //поменять на сокращенный путь
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
            stage.setTitle("Редактирование записи");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
