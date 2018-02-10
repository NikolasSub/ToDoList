package DBUtilities;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBUtilities {
    private static Connection dbConnection = null;
    private static final String JAVA_DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private static String dbName = "ToDoTable";
    private static String connectionURL = "jdbc:derby:" + dbName + "; create = true";
    private static String createTable = "create table ToDoList " +
                                        "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT PK PRIMARY KEY," +
                                        "DATE VARCHAR(20), Action VARCHAR(101))";

    public static void dbConnect(){
        Statement creationTable;
        try {
            Class.forName(JAVA_DB_DRIVER);
            System.out.println("Driver is loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver is out...");
            e.printStackTrace();
        }
        try{
            dbConnection = DriverManager.getConnection(connectionURL);
            System.out.println("Connect to DB...");
            ResultSet rs = dbConnection.getMetaData()
                        .getTables(null, null, "TODOLIST", null);
            if (rs.next()) {
                System.out.println("Table exists...");
            } else {
                creationTable = dbConnection.createStatement();
                creationTable.execute(createTable);
                System.out.println("Table created...");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void dbDisconnect(){
        boolean isShutDown = true;
        try {
            if (dbConnection != null && !dbConnection.isClosed())
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getSQLState().equals("XJ015")){
                isShutDown = false;
            }
        }
        if (isShutDown){
            System.out.println("The DB shutdown normally...");
        } else {
            System.out.println("The DB is not shutdown normally...");
        }
    }

    public static ResultSet dbSelectAllEntries() throws SQLException, ClassNotFoundException{
        String select = "select * from TODOLIST";
        Statement statement = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        try{
            dbConnect();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(select);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        } catch (SQLException ex){
            System.out.println("Problem with select" + ex);
        } finally {
            if(statement != null){
                statement.close();
            }
            if (rs != null){
                rs.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void insertEntryIntoTable(String template) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        try {
            dbConnect();
            statement= dbConnection.createStatement();
            statement.execute(template);
            } catch (SQLException ex){
                System.out.println("Cannot complete SQL operation, because " + ex);
            } finally {
                if (statement != null){
                    statement.close();
                }
                dbDisconnect();
        }
    }
}
