import javafx.application.Application;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ToDoApp extends Application {

    public static void main(String[] args){
        launch(ToDoApp.class);
    }

    @Override
    public void start(Stage stage) {
       new PrimaryStage();
       /* button.setOnMouseClicked((e -> {
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
    }*/
    }
}

