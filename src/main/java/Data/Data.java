package Data;

import javafx.beans.property.SimpleStringProperty;

public class Data{
    private SimpleStringProperty date;
    private SimpleStringProperty action;

    public Data(){
        this.date = new SimpleStringProperty();
        this.action = new SimpleStringProperty();
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getAction() {
        return action.get();
    }

    public SimpleStringProperty actionProperty() {
        return action;
    }

    public void setAction(String action) {
        this.action.set(action);
    }
}