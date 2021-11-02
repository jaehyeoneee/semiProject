package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CourseDTO;

public class CourseDAO {

	DBConnection db = new DBConnection();

	public ArrayList<CourseDTO> get() {
		
		ArrayList<CourseDTO> courses = new ArrayList<>();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql = "select * from course order by co_id desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseDTO course = new CourseDTO();
				
				course.setId(rs.getInt("co_id"));
				course.setUs_id(rs.getInt("co_author_us_id"));
				course.setHt_id(rs.getInt("co_hashtag_ht_id"));
				course.setCa_id(rs.getInt("co_category_ca_id"));
				course.setTitle(rs.getString("co_title"));
				course.setRating(rs.getDouble("co_rating"));
				course.setRegister_count(rs.getInt("co_register_count"));
				course.setPrice_per_day(rs.getInt("co_price_per_day"));
				course.setLike(rs.getInt("co_like"));
				course.setIntroduce(rs.getString("co_introduce"));
				course.setCurriculum(rs.getString("co_curriculum"));
				course.setRecommand_for(rs.getString("co_recommand_for"));
				course.setBefore_check(rs.getString("co_before_check"));
				course.setCreated_at(rs.getDate("co_created_at"));
				course.setUpdated_at(rs.getDate("co_updated_at"));
				
			courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return courses;
	}

	public CourseDTO getOne(int co_id) {
		CourseDTO dto = new CourseDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from course where co_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, co_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("co_id"));
				dto.setUs_id(rs.getInt("co_author_us_id"));
				dto.setHt_id(rs.getInt("co_hashtag_ht_id"));
				dto.setCa_id(rs.getInt("co_category_ca_id"));
				dto.setTitle(rs.getString("co_title"));
				dto.setRating(rs.getDouble("co_rating"));
				dto.setRegister_count(rs.getInt("co_register_count"));
				dto.setPrice_per_day(rs.getInt("co_price_per_day"));
				dto.setLike(rs.getInt("co_like"));
				dto.setIntroduce(rs.getString("co_introduce"));
				dto.setCurriculum(rs.getString("co_curriculum"));
				dto.setRecommand_for(rs.getString("co_recommand_for"));
				dto.setBefore_check(rs.getString("co_before_check"));
				dto.setCreated_at(rs.getDate("co_created_at"));
				dto.setUpdated_at(rs.getDate("co_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int create(CourseDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "insert into course(co_author_us_id,co_hashtag_ht_id,co_category_ca_id,co_title,"
				+ "co_rating,co_register_count,co_price_per_day,co_like,co_introduce,co_curriculum"
				+ ",co_recommand_for,co_before_check) values(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUs_id());
			pstmt.setInt(2, dto.getHt_id());
			pstmt.setInt(3, dto.getCa_id());
			pstmt.setString(4, dto.getTitle());
			pstmt.setDouble(5, dto.getRating());
			pstmt.setInt(6, dto.getRegister_count());
			pstmt.setInt(7, dto.getPrice_per_day());
			pstmt.setInt(8, dto.getLike());
			pstmt.setString(9, dto.getIntroduce());
			pstmt.setString(10, dto.getCurriculum());
			pstmt.setString(11, dto.getRecommand_for());
			pstmt.setString(12, dto.getBefore_check());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public int update(CourseDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "update course set co_author_us_id=?,co_hashtag_ht_id=?,co_category_ca_id=?,"
				+ "co_title=?,co_rating=?,co_register_count=?,co_price_per_day=?,co_like=?"
				+ ",co_introduce=?,co_curriculum=?,co_recommand_for=?,co_before_check=? where co_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUs_id());
			pstmt.setInt(2, dto.getHt_id());
			pstmt.setInt(3, dto.getCa_id());
			pstmt.setString(4, dto.getTitle());
			pstmt.setDouble(5, dto.getRating());
			pstmt.setInt(6, dto.getRegister_count());
			pstmt.setInt(7, dto.getPrice_per_day());
			pstmt.setInt(8, dto.getLike());
			pstmt.setString(9, dto.getIntroduce());
			pstmt.setString(10, dto.getCurriculum());
			pstmt.setString(11, dto.getRecommand_for());	
			pstmt.setString(12, dto.getBefore_check());
			pstmt.setInt(13, dto.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public void delete(int co_id) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "delete from course where co_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, co_id);

			pstmt.execute();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	public static void main(String[] args) {
		
		
		
		/*
		 * CourseDAO dao = new CourseDAO();
		 * 
		 * CourseDTO course = new CourseDTO();
		 * 
		 * course.setUs_id(5); course.setHt_id(4); course.setCa_id(1);
		 * course.setTitle("제목"); course.setRating(2.6); course.setRegister_count(3);
		 * course.setPrice_per_day(20); course.setLike(4); course.setIntroduce("ㅇㅇㅇ");
		 * course.setCurriculum("ㅇㅇㅇ"); course.setRecommand_for("ㅇㅇㅇ");
		 * course.setBefore_check("ㅇㅇㅇ");
		 * 
		 * System.out.print(dao.create(course));
		 */
		 
		
		
		
		/*
		 * ArrayList<CourseDTO>courses = new CourseDAO().get();
		 * 
		 * for (CourseDTO course: courses) { System.out.println(course.getId());
		 * System.out.println(course.getUs_id()); System.out.println(course.getHt_id());
		 * System.out.println(course.getCa_id()); System.out.println(course.getTitle());
		 * System.out.println(course.getRating());
		 * System.out.println(course.getRegister_count());
		 * System.out.println(course.getPrice_per_day());
		 * System.out.println(course.getLike());
		 * System.out.println(course.getIntroduce());
		 * System.out.println(course.getCurriculum());
		 * System.out.println(course.getRecommand_for());
		 * System.out.println(course.getBefore_check());
		 * System.out.println(course.getCreated_at());
		 * System.out.println(course.getUpdated_at()); }
		 */
		 
		
		/*
		 * CourseDTO course=new CourseDAO().getOne(3);
		 * 
		 * System.out.println(course.getId()); System.out.println(course.getUs_id());
		 * System.out.println(course.getHt_id()); System.out.println(course.getCa_id());
		 * System.out.println(course.getTitle());
		 * System.out.println(course.getRating());
		 * System.out.println(course.getRegister_count());
		 * System.out.println(course.getPrice_per_day());
		 * System.out.println(course.getLike());
		 * System.out.println(course.getIntroduce());
		 * System.out.println(course.getCurriculum());
		 * System.out.println(course.getRecommand_for());
		 * System.out.println(course.getBefore_check());
		 * System.out.println(course.getCreated_at());
		 * System.out.println(course.getUpdated_at());
		 */
		 
	
		
		/*
		 * CourseDAO dao = new CourseDAO(); CourseDTO course=new CourseDTO();
		 * 
		 * course.setUs_id(5); course.setHt_id(7); course.setCa_id(1);
		 * course.setTitle("제목u"); course.setRating(9.0); course.setRegister_count(9);
		 * course.setPrice_per_day(29); course.setLike(9); course.setIntroduce("ㅇㅇㅇu");
		 * course.setCurriculum("ㅇㅇㅇu"); course.setRecommand_for("ㅇㅇㅇu");
		 * course.setBefore_check("ㅇㅇㅇu");course.setId(3);
		 * 
		 * System.out.print(dao.update(course));
		 */
		 
		/*
		 * CourseDAO dao = new CourseDAO(); dao.delete(3);
		 */
	}

}
