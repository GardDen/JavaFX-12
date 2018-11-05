package controller;

import interfaces.impls.CollectionAddressBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public Button changeButton;

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
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        addressBookImpl.fillTestData();

        tableAdressBook.setItems(addressBookImpl.getPersonList());

        updateCountLabel();
    }

    private void updateCountLabel() {
        countLabel.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }

    public void showDialog(ActionEvent actionEvent) {

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
