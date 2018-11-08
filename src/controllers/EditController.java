package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Person;

public class EditController {
    @FXML
    public TextField txtFIO;

    @FXML
    public TextField txtPhone;
    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private Person person;

    public void setPerson(Person person) {
        this.person = person;

    }

    public void actionOk(ActionEvent event) {

        actionClose(event);
    }

    public void actionClose(ActionEvent event) {
//        Node source = (Node) event.getSource();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }
}
