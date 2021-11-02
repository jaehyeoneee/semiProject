package dao;

import java.sql.*;

public class DBConnection {
    static final String ORACLE_NAS = "jdbc:mysql://sknas.asuscomm.com:3306/outflearn";

    public Connection getLocalOracle(){
        Connection conn=null;
        try{
            conn= DriverManager.getConnection(ORACLE_NAS, "root", "!j14682533");
            System.out.println("로컬 오라클 연결 성공");
        }catch (SQLException e) {
            System.out.println("로칼 오라클 연결 실패: " + e.getMessage());
        }
        return conn;
    }

    public void dbClose(ResultSet rs, Statement stmt, Connection conn){
        try{
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void dbClose(Statement stmt, Connection conn){
        try{
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBConnection ex = new DBConnection();
    }
}
