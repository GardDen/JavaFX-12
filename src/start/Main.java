package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        primaryStage.setTitle("Phone book");
        primaryStage.setScene(new Scene(root, 600, 540));
        primaryStage.show();
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
