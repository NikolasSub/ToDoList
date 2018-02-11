import TaskUtilities.TaskUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import Data.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondaryController {

    private static final int MAX = 100;

    @FXML
    private TextField textDataField;

    @FXML
    private TextArea textAreaAction;

    @FXML
    private Button buttonSave;

    public TableView<Data> secondaryTableView;


    //Закрытие второго окна и обновление содержимого в первом окне
    @FXML
    void saveEntry(ActionEvent event) {
        TaskUtilities.updateTable(secondaryTableView, textDataField.getText(), textAreaAction.getText());
        Stage stage = (Stage) buttonSave.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        textDataField.setText(dateFormat.format(new Date()));
        textAreaAction.lengthProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue.intValue() > oldValue.intValue()){
                    if (textAreaAction.getText().length() >= MAX){
                        textAreaAction.setText(textAreaAction.getText().substring(0, MAX));
                    }
                }
        });
    }
}
