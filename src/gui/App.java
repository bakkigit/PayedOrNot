package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
//    static final String driver = "com.mysql.cj.jdbc.Driver";
//    static final String url = "jdbc:mysql://localhost:3306";
//    static final String username = "root";
//    static final String password = "daoudi123";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Create();
//        Table();
        GuiListView1.launch(GuiListView1.class);
    }


//    public static void Create() throws ClassNotFoundException, SQLException {
//        Connection conn = null;
//        Statement stmt = null;
//        Class.forName(driver);
//
//        conn = DriverManager.getConnection(url,username,password);
//        stmt = conn.createStatement();
//        String sql = "CREATE DATABASE MASJID";
//        stmt.executeUpdate(sql);
//        System.out.println("Succes!");
//        stmt.close();
//        conn.close();
//
//    }


//    public static Connection Table(){
//        Connection conn = null;
//        Statement stmt = null;
//        String driver = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/masjid";
//        String username = "root";
//        String password = "daoudi123";
//
//        try {
//            //Registrere JDBC Driver
//            Class.forName(driver);
//
//            //Open a connection to out DB
//            System.out.println("Connecting to DB");
//            conn = DriverManager.getConnection(url,username,password);
//            System.out.println("Connected");
//
//            //Excuting the Query
//            System.out.println("Creating the Table");
//            stmt = conn.createStatement();
//            String Mysql = "CREATE TABLE BRUGER"+
//                    "(id INTEGER not NULL AUTO_INCREMENT,"+
//                    "navn VARCHAR(255),"+
//                    "nr INTEGER,"+
//                    "betalt VARCHAR(255),"+
//                    "PRIMARY KEY(id))";
//            stmt.executeUpdate(Mysql);
//            System.out.println("Creating table SUCCES!");
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//        return conn;
//    }



}
