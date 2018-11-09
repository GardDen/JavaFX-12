package controllers;

import interfaces.impls.CollectionAddressBook;
import javafx.collections.ListChangeListener;
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
import javafx.stage.Window;
import objects.Person;

import java.io.IOException;

public class MainController {
    @FXML
    public Button addButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button editButton;
    @FXML
    public Button searchButton;
    @FXML
    public Label countLabel;
    @FXML
    private TableView tableAdressBook;
    @FXML
    private TableColumn<Person, String> columnFIO;
    @FXML
    private TableColumn<Person, String> columnPhone;

    public CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditController editController;
    private Stage edidDialogStage;
    private Window mainStage;

    @FXML
    private void initialize() {
        //tableAdressBook.getSelectionModel().getSelectionMode(SelectionMode.MULTIPLE);
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });
        addressBookImpl.fillTestData();

        tableAdressBook.setItems(addressBookImpl.getPersonList());

        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlLoader.load();
            editController = fxmlLoader.getController();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private void updateCountLabel() {
        countLabel.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }

    /**
     * The method listener pressing the buttons and perform certain action.
     * @param actionEvent
     */
    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;

        Person selectedPerson = (Person) tableAdressBook.getSelectionModel().getSelectedItem();
        mainStage = ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            fxmlEdit = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: start edit.fxml");
        }

        switch (clickedButton.getId()) {
            case "addButton":
                System.out.println("add " + selectedPerson);
                editController.setPerson(selectedPerson);
                showDialog();
                addressBookImpl.add(editController.getPerson());
                break;
            case "editButton":
                if (selectedPerson != null) {
                    System.out.println("edit " + selectedPerson);
                    showDialog();
                } else {
                    System.out.println("Select the row of the table!");
                }

                break;
            case "deleteButton":
                System.out.println("delete" + selectedPerson);
                addressBookImpl.delete((Person)tableAdressBook.getSelectionModel().getSelectedItem());
                break;
        }
    }

    private void showDialog() {
        if (edidDialogStage == null){
            edidDialogStage = new Stage();
            edidDialogStage.setTitle("Редактирование записи");
            edidDialogStage.setMinHeight(150);
            edidDialogStage.setMinWidth(300);
            edidDialogStage.setResizable(false);
            edidDialogStage.setScene(new Scene(fxmlEdit));
            edidDialogStage.initModality(Modality.WINDOW_MODAL);
            edidDialogStage.initOwner(mainStage);
        }
        edidDialogStage.showAndWait();
    }
}
