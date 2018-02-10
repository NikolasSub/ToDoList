import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondaryController {
    @FXML
    private TextField textDataField;

    @FXML
    private TextArea textAreaAction;

    @FXML
    private Button buttonSave;

    @FXML
    void saveEntry(ActionEvent event) {

    }

    public void initialize() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        textDataField.setText(dateFormat.format(new Date()));
    }

}
