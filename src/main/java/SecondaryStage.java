import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondaryStage extends Stage {

    public SecondaryStage(){
        HBox hBox = new HBox();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ToDoApp.class.getResource("/View/SecondaryStage.fxml"));
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(hBox);
        this.setScene(scene);
        this.setTitle("Add Action!");
        this.show();
    }
}
