package objects;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    //нужны для лучшего отображения данных в таблице, благодаря связям,
    // более быстро на автомате обновляет данные в таблице
    private SimpleStringProperty fio = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person(String name, String phone) {
        this.fio = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
    }

    public Person() {
    }

    public String getFio() {
        return fio.get();
    }

    public SimpleStringProperty fioProperty() {
        return fio;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return fio + " " + phone;
    }
}
