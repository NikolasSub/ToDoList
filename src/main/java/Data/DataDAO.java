package Data;

import DBUtilities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

//Слой для взаимодействия данных приложения с БД

public class DataDAO {

    public static ObservableList<Data> getAllData() throws SQLException, ClassNotFoundException{
        ObservableList<Data> data = FXCollections.observableArrayList();
        ResultSet rs = DBUtilities.dbSelectAllEntries();
        while (rs.next()){
           Data instanceOfData = new Data();
           instanceOfData.setDate(rs.getString("Date"));
           instanceOfData.setAction(rs.getString("Action"));
           data.add(instanceOfData);
        }

        return data;
    }

    public static void insertData(String date, String action) throws SQLException, ClassNotFoundException{
        try {
            DBUtilities.insertEntryIntoTable(date, action);
            System.out.println("Successfully added...");
        } catch (SQLException ex){
            System.out.println("Something wrong..." + ex);
        }
    }
}
