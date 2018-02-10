package Data;

import DBUtilities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        String insertEntry =  "BEGIN\n" +
                "INSERT INTO ToDoList\n" +
                "(DATE, ACTION)\n" +
                "VALUES\n" +
                "(" + date + "," +action + ");\n" +
                "END;";
        try {
            DBUtilities.insertEntryIntoTable(insertEntry);
        } catch (SQLException ex){
            System.out.println("Something wrong..." + ex);
        }
    }
}
