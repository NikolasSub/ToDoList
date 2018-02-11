import javafx.application.Application;
import javafx.stage.Stage;

public class ToDoApp extends Application {

    public static void main(String[] args){
        launch(ToDoApp.class);
    }

    @Override
    public void start(Stage stage) {
       new PrimaryStage();
    }
}

