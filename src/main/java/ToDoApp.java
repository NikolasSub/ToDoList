import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoApp extends Application {

    public class Data{
        private SimpleStringProperty date;
        private SimpleStringProperty action;

        private Data(String date, String action){
            this.date = new SimpleStringProperty(date);
            this.action = new SimpleStringProperty(action);
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

    private TableView<Data> tableView = new TableView<>();
    private final ObservableList<Data> dataList = FXCollections.observableArrayList();

    public static void main(String[] args){
        launch(ToDoApp.class);
    }

    @Override
    public void start(Stage stage){
        DBUtilities.DBConnect();
        stage.setResizable(true);
        stage.setTitle("ToDoList");
        tableView.setEditable(false);
        tableView.setItems(dataList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Data, String> date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Data, String> action = new TableColumn<>("Action");
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
        tableView.getColumns().addAll(date, action);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        Button button = new Button("Add");
        vBox.setAlignment(Pos.CENTER);
        button.setOnMouseClicked((e -> {
            Stage secondaryStage = new Stage();
            secondaryStage.setResizable(false);
            secondaryStage.setTitle("Add Action!");
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            TextField dateText = new TextField();
            dateText.setEditable(false);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            dateText.setText(dateFormat.format(new Date()));
            TextArea requiredAction = new TextArea();
            Button addButton = new Button("OK");
            addButton.setOnMouseClicked(event -> {
                if (requiredAction.getText().length() != 0) dataList.add(new Data(dateText.getText(), requiredAction.getText()));
                secondaryStage.close();
            });
            hBox.getChildren().addAll(dateText, requiredAction, addButton);
            Scene secondaryScene = new Scene(hBox);
            secondaryStage.setScene(secondaryScene);
            secondaryStage.show();
        }));
        vBox.getChildren().addAll(tableView, button);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            DBUtilities.DBDisconnect();
            System.out.println("App is closed...");
        });
    }
}

