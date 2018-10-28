package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public Button deleteButton;

    @FXML
    public Button changeButton;

    @FXML
    public Button searchButton;

    @FXML
    private Button addButton;

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
