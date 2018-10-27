package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public void showDialog(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            //поменять на сокращенный путь
            Parent root = FXMLLoader.load(getClass().getResource("D:\\Google\\Project\\Java\\JavaFX 12\\src\\fxml\\edit.fxml"));
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
