package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Adrian.Purcaru on 13/07/2017.
 */
public class DBManager {
    
    static final String CONNECTION_STRING= "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":pmb";
    
    private DBManager() {
        throw new UnsupportedOperationException();
    }
    
    private static void registerDriver() throws ClassNotFoundException {
        Class.forName(DBProperties.DRIVER_CLASS);
    }
    
    public static Connection getConnection() throws ClassNotFoundException {
        registerDriver();
        Properties properties = new Properties( );
        properties.put( "user", DBProperties.USER );
        properties.put( "password", DBProperties.PASS );
        
        Connection conn = null;
    
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return conn;
    }
    
    public static boolean checkConnection(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "Select 1 from dual";
        boolean res = statement.execute(sql);
        
        return res;
    }
}
