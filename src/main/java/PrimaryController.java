import TaskUtilities.TaskUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import Data.*;

import java.sql.SQLException;

public class PrimaryController {

    @FXML
    private Button button;

    @FXML
    private TableView<Data> tableView;

    @FXML
    private TableColumn<Data, String> tableColumnDate;

    @FXML
    private TableColumn<Data, String> tableColumnAction;

    //Инициилизация столбцов для времени и заметок
    public void initialize(){
        tableColumnDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        tableColumnAction.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
        try {
            TaskUtilities.fillTable(tableView);
        } catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //Запуск второго окна для добавления заметки
    @FXML
    private void runSecondaryStage(ActionEvent actionEvent){
        new SecondaryStage(tableView);
    }
}
