import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimaryStage extends Stage{

    public PrimaryStage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ToDoApp.class.getResource("/View/PrimaryStage.fxml"));
            VBox vBox = loader.load();
            Scene scene = new Scene(vBox);
            this.setScene(scene);
        } catch (IOException ioe) {
            System.out.println("Cannot run...");
            ioe.printStackTrace();
        }
        this.setTitle("ToDoApp");
        this.show();
    }
}
