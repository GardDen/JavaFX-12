package controllers;

import interfaces.impls.CollectionAddressBook;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import objects.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    private TableView tableAddressBook;
    @FXML
    private TableColumn<Person, String> columnFIO;
    @FXML
    private TableColumn<Person, String> columnPhone;

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private Window mainStage;
    private ResourceBundle resourceBundle;

    /**
     * Подключаем Listeners к таблице. Слушает любые изменения данных таблицы. Изменение, добавление, удаление и
     * отображает их в нашей таблице.
     */
    private void initListeners()  {
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

        tableAddressBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)  {
                if (event.getClickCount() == 2) {
                    editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    /**
     * Заносимм в коллекцию тестовые данные. А потом подключаем её к таблице для отображения этих данных.
     */
    private void fillData() {
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }

    /**
     * Инициализируем FXMLLoader (загружает файл .fxml, в котором хранится информация о графическом интерфейсе
     * расположении всех кнопок и окон для диалогового окна).
     * 1 устанавливает место откуда грузиться будет файл
     * 2 загружается
     * 3 получаем ссылку на контроллер диалогового окна
     * 4 все оборачиваем в try так как может выскочить ошибка загрузки fxml файлв
     * @param root
     */
    private void initLoader(String root) {
        try {
            fxmlLoader.setLocation(getClass().getResource(root));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException exc) {
            System.out.println("Error: Неверное расположение fxml файла или ошибка внутри него.");
            exc.printStackTrace();
        }
    }

    private void updateCountLabel() {
        countLabel.setText(resourceBundle.getString("key.count") + ": " + addressBookImpl.getPersonList().size());
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
        Person person = new Person();
        editDialogController.setTxtFIO("");
        editDialogController.setTxtPhone("");

        switch (clickedButton.getId()) {
            case "addButton":
                editDialogController.setPerson(person);
                showDialog();
                addressBookImpl.add(editDialogController.getPerson());
                break;
            case "editButton":
                person = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
                if (person == null) {
                    //старт окна message
                    System.out.println("Чтобы изменить строку выберите ее.");
                    return;
                }
                editDialogController.setPerson(person);
                showDialog();
                //addressBookImpl.edit(editDialogController.getPerson());
                //editEntry();
                break;
            case "deleteButton":
                person = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
                if (person == null) {
                    //старт окна message
                    System.out.println("Чтобы удалить строку выберите ее.");
                    return;
                }
                addressBookImpl.delete(person);
                break;
        }
    }

    /**
     * Используется ленивая инициализация editDialogStage, только один раз. Добовляем ссылку к главному контроллеру
     * и просто прячем показываем её.
     */
    private void showDialog() {
        if (editDialogStage == null){
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
        editDialogStage.showAndWait();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        initListeners();
        fillData();
        initLoader("../fxml/edit.fxml");
    }

    /*private void showMessage() {
        initLoader("../fxml/message.fxml");
        editDialogStage.setTitle("Сообщение");
        editDialogStage.setScene(new Scene(fxmlEdit));
        editDialogStage.showAndWait();
    }*/

     /*private void editEntry() {
        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            System.out.println("edit " + selectedPerson);
            showDialog();
        } else {
            System.out.println("Select the row of the table!");
            showMessage();
            editEntry();
        }
    }*/
}
