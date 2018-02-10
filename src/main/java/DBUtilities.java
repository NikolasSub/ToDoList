import java.sql.*;


public class DBUtilities {
    private static Connection dbConnection = null;
    private static final String JAVA_DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static PreparedStatement insertRow;

    private static String dbName = "ToDoTable";
    private static String connectionURL = "jdbc:derby:" + dbName + "; create = true";
    private static String createTable = "create table ToDoList (id INTEGER PRIMARY KEY, Date DATE, Action VARCHAR(101))";
    private static String insertData = "insert into ToDoList (Date, Action) values (?,?)";
    
    public static void DBConnect(){
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

    public static void DBDisconnect(){
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

    public static void updateDBTable(String insertStr){
            try {
                if (dbConnection != null)
                    insertRow = dbConnection.prepareStatement(insertData);
            } catch (SQLException ex){

            }
    }
}
