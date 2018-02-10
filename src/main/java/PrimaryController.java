import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import Data.*;

import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PrimaryController {
    //Объект, используемый для генерации потоков
    private Executor exec;

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
        //Создаём пул потоков
        exec = Executors.newCachedThreadPool((r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }));
        try {
            fillTable();
        } catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //Заполнение данными из таблицы
    private void fillTable() throws SQLException, ClassNotFoundException{
        tableView.getItems().clear();
        Task<ObservableList<Data>> fillTask = new Task<ObservableList<Data>>() {
            @Override
            protected ObservableList<Data> call() throws Exception {
                    return DataDAO.getAllData();
            }
        };

        fillTask.setOnFailed(e -> fillTask.getException().printStackTrace());
        fillTask.setOnSucceeded(e -> {
            tableView.setItems(fillTask.getValue());
        });
        exec.execute(fillTask);
    }

    //Запуск второго окна для добавления заметки
    @FXML
    private void runSecondaryStage(ActionEvent actionEvent){
        new SecondaryStage();
    }
}
