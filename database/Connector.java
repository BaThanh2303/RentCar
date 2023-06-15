package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    private  final static String connectionString = "jdbc:mysql://localhost:3306/rentcar";
    private final static String user = "root";
    private final static String pwd = "";
    private static Connector instance;
    private Connection conn;
    public Connector() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(connectionString,user,pwd);
    }
    public static Connector getInstance() throws Exception{
        if(instance == null){
            instance = new Connector();
        }
        return instance;
    }
    public Connection getConn() {
        return conn;
    }
}
