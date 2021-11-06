package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class AuthDAO {
	
	DBConnection db=new DBConnection();
	
	public boolean isLogin(String username, String password)
	{
		boolean flag=false;
		
		Connection conn=db.getLocalOracle();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from user where us_username=? and us_password=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			
			if(rs.next())
				flag=true; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(rs, pstmt, conn);
		}
		return flag;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthDAO dao=new AuthDAO();
		System.out.print(dao.isLogin("ww", "1230"));
	}

}
