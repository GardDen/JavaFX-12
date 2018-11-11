package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Person;

public class EditDialogController {


    @FXML
    private TextField txtFIO;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    private Person person;

    public void setPerson(Person person) {
        if (person == null) {
            System.out.println("person = null");
            return;
        }
        this.person = person;
        txtFIO.setText(person.getFio());
        txtPhone.setText(person.getPhone());
    }

    public void actionSave(ActionEvent event) {
        try {
            person.setFio(txtFIO.getText());
            person.setPhone(txtPhone.getText());
            if (person.getPhone().equals("") || person.getFio().equals("")) {
                System.out.println("Поля Имя или Телефон не могут быть пустыми");
            }
        } finally {
            actionClose(event);
        }
    }

    public void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();//было hide() хз как лучше, может будет неправильно работать
    }

    public Person getPerson() {
        return person;
    }

    public void setTxtFIO(String txtFIO) {
        this.txtFIO.setText(txtFIO);
    }

    public void setTxtPhone(String txtPhone) {
        this.txtPhone.setText(txtPhone);
    }
}
