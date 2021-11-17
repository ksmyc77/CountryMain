package db;

import java.sql.*;

public class DBConnect {


    private static Connection dbConnect;

    public static Connection getConnect() throws SQLException, ClassNotFoundException {
        if(dbConnect==null) {
            String url="jdbc:mysql://localhost/?user=root";
            String id="root";
            String password="m1164819";
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            dbConnect = DriverManager.getConnection(url, id, password);
        }
        return dbConnect;
    }

    public static void createDB(String dbName){

        String dbSql = "SELECT * FROM Information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";

        try {
            Connection conn =  DBConnect.getConnect();
            PreparedStatement pstm;
            ResultSet rs;

            pstm = conn.prepareStatement(dbSql);
            pstm.setString(1, dbName);
            rs = pstm.executeQuery();
            //데이터베이스가 없다면 데이터베이스 생성
            if(!rs.next()){
                Statement stmt = conn.createStatement();
                String sql = "create database " + dbName + " default character set utf8 collate utf8_general_ci";
                System.out.println("DB 생성 완료");
                stmt.execute(sql);
            }
            //데이터베이스를 변환 (use database)
            conn.setCatalog(dbName);
        } catch (Exception e) {
            System.out.println("CreateOrChangeDatabase err : " + e);
        }
    }

    public static void close(Connection c, PreparedStatement p, ResultSet r) {
        try {
            if(r!=null)r.close();
            if(p!=null)p.close();
            if(c!=null)c.close();
        }catch(Exception e) {}
    }
    public static void close(Connection c, PreparedStatement p) {
        try {
            if(p!=null)p.close();
            if(c!=null)c.close();
        }catch(Exception e) {}
    }

}
