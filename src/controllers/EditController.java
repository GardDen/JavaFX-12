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
        if (person == null) {
            return;
        }
        this.person = person;
        txtFIO.setText(person.getFio());
        txtPhone.setText(person.getPhone());
    }

    public void actionOk(ActionEvent event) {
        try {
            person.setFio(txtFIO.getText());
            person.setPhone(txtPhone.getText());

        } catch (NullPointerException exc) {
            System.out.println("Error: invalid information.");
        }
        actionClose(event);
    }

    public void actionClose(ActionEvent event) {
//        Node source = (Node) event.getSource();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }

    public Person getPerson() {
        return person;
    }
}
