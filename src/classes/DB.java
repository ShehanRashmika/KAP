

package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DB {

    private static Connection c;
    
      public static Connection ConnDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/kap", "root", "123");
            return conn;
        } catch (Exception e) {
        }
        return null;

    }
    

    private static void setNewConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        c =  DriverManager.getConnection("jdbc:mysql://localhost:3307/kap", "root", "123");

    }

    public static void iud(String sql) throws Exception {
        if (c == null) {
            setNewConnection();
        }
        c.createStatement().execute(sql);

    }

    public static ResultSet search(String sql) throws Exception {
        if (c == null) {
            setNewConnection();
        }
        return c.createStatement().executeQuery(sql);

    }



}
//