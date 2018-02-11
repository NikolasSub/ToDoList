package TaskUtilities;

import Data.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//Вспомогательный класс для асинхронного выполнения кода

public class TaskUtilities {
    //Объект, используемый для генерации потоков - создаём пул потоков со свойствами демонов
    private static final Executor exec = Executors.newCachedThreadPool((r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }));

    //Заполнение данными из таблицы
    public static void fillTable(TableView<Data> tableView) throws SQLException, ClassNotFoundException{
        tableView.getItems().clear();
        Task<ObservableList<Data>> fillTask = new Task<ObservableList<Data>>() {
            @Override
            protected ObservableList<Data> call() throws Exception {
                return DataDAO.getAllData();
            }
        };

        fillTask.setOnFailed(e -> fillTask.getException().printStackTrace());
        fillTask.setOnSucceeded(e -> tableView.setItems(fillTask.getValue()));
        exec.execute(fillTask);
    }

    //Обновление данных в таблице
    public static void updateTable(TableView<Data> tableView, String date, String action){
        Task<ObservableList<Data>> updateTask = new Task<ObservableList<Data>>() {
            @Override
            protected ObservableList<Data> call() throws Exception {
                DataDAO.insertData(date, action);
                return DataDAO.getAllData();
            }
        };

        updateTask.setOnFailed(e -> updateTask.getException().printStackTrace());
        updateTask.setOnSucceeded(e -> tableView.setItems(updateTask.getValue()));
        exec.execute(updateTask);
    }
}
