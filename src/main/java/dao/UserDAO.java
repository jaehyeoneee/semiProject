package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import dto.UserDTO;

public class UserDAO {

	DBConnection db = new DBConnection();

	public ArrayList<UserDTO> get() {
		
		ArrayList<UserDTO> users = new ArrayList<>();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql = "select * from user order by us_id desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserDTO user = new UserDTO();
				
				user.setId(rs.getInt("us_id"));
				user.setUsername(rs.getString("us_username"));
				user.setPassword(rs.getString("us_password"));
				user.setName(rs.getString("us_name"));
				user.setAddress(rs.getString("us_address"));
				user.setEmail(rs.getString("us_email"));
				user.setPhone(rs.getString("us_phone"));
				user.setIntroduce(rs.getString("us_introduce"));
				user.setRole(rs.getString("us_role"));
				user.setThumbnail(rs.getString("us_thumbnail"));
				user.setCreatedAt(rs.getDate("us_created_at"));
				user.setUpdatedAt(rs.getDate("us_updated_at"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return users;
	}

	public UserDTO getOne(int us_id) {
		UserDTO dto = new UserDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from user where us_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, us_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("us_id"));
				dto.setUsername(rs.getString("us_username"));
				dto.setPassword(rs.getString("us_password"));
				dto.setName(rs.getString("us_name"));
				dto.setAddress(rs.getString("us_address"));
				dto.setEmail(rs.getString("us_email"));
				dto.setPhone(rs.getString("us_phone"));
				dto.setIntroduce(rs.getString("us_introduce"));
				dto.setRole(rs.getString("us_role"));
				dto.setThumbnail(rs.getString("us_thumbnail"));
				dto.setCreatedAt(rs.getDate("us_created_at"));
				dto.setUpdatedAt(rs.getDate("us_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int create(UserDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "insert into user(us_username,us_password,us_name,us_address,us_email,us_phone,us_introduce,us_role,"
				+ "us_thumbnail) values(?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getIntroduce());
			pstmt.setString(8, dto.getRole());
			pstmt.setString(9, dto.getThumbnail());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public int update(UserDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "update user set us_username=?,us_password=?,us_name=?,us_address=?,us_email=?,us_phone=?,"
				+ "us_introduce=?,us_role=?,us_thumbnail=? where us_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getIntroduce());
			pstmt.setString(8, dto.getRole());
			pstmt.setString(9, dto.getThumbnail());
			pstmt.setInt(10, dto.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public void delete(int us_id) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "delete from user where us_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, us_id);

			pstmt.execute();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}//
	}

	public static void main(String[] args) {

		
		
		
		/*
		 * ArrayList<UserDTO> users = new UserDAO().get();
		 * 
		 * for (UserDTO user: users) { System.out.println(user.getId());
		 * System.out.println(user.getUsername());
		 * System.out.println(user.getPassword()); System.out.println(user.getName());
		 * System.out.println(user.getAddress()); System.out.println(user.getEmail());
		 * System.out.println(user.getPhone()); System.out.println(user.getIntroduce());
		 * System.out.println(user.getRole()); }
		 */
		 		
		
	
		
		/*
		 * UserDAO dao = new UserDAO();
		 * 
		 * UserDTO user = new UserDTO();
		 * 
		 * user.setUsername("ww"); user.setPassword("1234"); user.setName("re");
		 * user.setAddress("tt"); user.setEmail("yl"); user.setPhone("q");
		 * user.setIntroduce("w"); user.setRole("e"); user.setThumbnail("r");
		 * 
		 * System.out.print(dao.create(user));
		 */
		 
		 
		 
		
		/*
		 * UserDTO user=new UserDAO().getOne(3); System.out.println(user.getId());
		 * System.out.println(user.getUsername());
		 * System.out.println(user.getPassword()); System.out.println(user.getName());
		 * System.out.println(user.getAddress()); System.out.println(user.getEmail());
		 * System.out.println(user.getPhone()); System.out.println(user.getIntroduce());
		 * System.out.println(user.getRole()); System.out.println(user.getCreated_at());
		 */
		 
	
		/*
		 * UserDAO dao = new UserDAO(); UserDTO user=new UserDTO();
		 * 
		 * user.setUsername("wwu"); user.setPassword("1234u"); user.setName("reu");
		 * user.setAddress("ttu"); user.setEmail("ylu"); user.setPhone("qu");
		 * user.setIntroduce("wu"); user.setRole("eu"); user.setThumbnail("ru");
		 * user.setId(2); System.out.print(dao.update(user));
		 */
		
	
		/* UserDAO dao = new UserDAO(); dao.delete(4); */
		 
	}
}