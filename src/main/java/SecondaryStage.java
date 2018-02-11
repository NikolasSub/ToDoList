import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Data.*;

import java.io.IOException;

public class SecondaryStage extends Stage {

    public SecondaryStage(TableView<Data> tableView){
        HBox hBox = new HBox();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ToDoApp.class.getResource("/View/SecondaryStage.fxml"));
            hBox = loader.load();
            //Привязка UI-элемента первого окна (TableView)
            //к контроллеру второго окна для обновления данных в первом окне
            SecondaryController secondaryController = loader.getController();
            secondaryController.secondaryTableView = tableView;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(hBox);
        this.setScene(scene);
        this.setTitle("Add Action!");
        this.show();
    }
}
