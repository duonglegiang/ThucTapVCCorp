package test_sql;

import java.util.*;
import java.lang.*;
import java.sql.*;

public class ConnectSQL {
    public static String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    public static String USER_NAME = "root";
    public static String PASSWORD = "12345678";
 
    public static Connection getConnection(String dbURL, String userName, String password) throws SQLException {
        Connection conn = null;
        
        try {
        	// Dang ky Driver    	
        	Class.forName("com.mysql.cj.jdbc.Driver");

        	// mo ket noi
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String args[]) throws SQLException {
    	Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
    	
    	// tao truy van (select)
    	Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student");

        // in ra man hinh
        while (rs.next()) {
        	System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        }
        
        Random r = new Random();
        int id_vd = r.nextInt();
        
        // chen ( insert )
        String sql1 = "INSERT INTO student (id, name, address) VALUES (" + String.valueOf(id_vd) + ", 'Le van hung', 'England');";
        stmt.executeUpdate(sql1);
        
        // update  
        String sql2 = "UPDATE student SET address = 'Nghệ An' WHERE address = 'England';";
        stmt.executeUpdate(sql2);
        
//        delete
        String sql3 = "DELETE FROM student WHERE name = 'Le van hung';";
        stmt.executeUpdate(sql3);
        // close connection
        conn.close();
    }

    
}
