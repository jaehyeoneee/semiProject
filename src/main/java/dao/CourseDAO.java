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
				course.setUserId(rs.getInt("co_author_us_id"));
				course.setHashtagId(rs.getInt("co_hashtag_ht_id"));
				course.setCategoryId(rs.getInt("co_category_ca_id"));
				course.setTitle(rs.getString("co_title"));
				course.setRating(rs.getDouble("co_rating"));
				course.setRegisterCount(rs.getInt("co_register_count"));
				course.setPricePerDay(rs.getInt("co_price_per_day"));
				course.setLike(rs.getInt("co_like"));
				course.setIntroduce(rs.getString("co_introduce"));
				course.setCurriculum(rs.getString("co_curriculum"));
				course.setRecommandFor(rs.getString("co_recommand_for"));
				course.setBeforeCheck(rs.getString("co_before_check"));
				course.setCreatedAt(rs.getDate("co_created_at"));
				course.setUpdatedAt(rs.getDate("co_updated_at"));
				
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
				dto.setUserId(rs.getInt("co_author_us_id"));
				dto.setHashtagId(rs.getInt("co_hashtag_ht_id"));
				dto.setCategoryId(rs.getInt("co_category_ca_id"));
				dto.setTitle(rs.getString("co_title"));
				dto.setRating(rs.getDouble("co_rating"));
				dto.setRegisterCount(rs.getInt("co_register_count"));
				dto.setPricePerDay(rs.getInt("co_price_per_day"));
				dto.setLike(rs.getInt("co_like"));
				dto.setIntroduce(rs.getString("co_introduce"));
				dto.setCurriculum(rs.getString("co_curriculum"));
				dto.setRecommandFor(rs.getString("co_recommand_for"));
				dto.setBeforeCheck(rs.getString("co_before_check"));
				dto.setCreatedAt(rs.getDate("co_created_at"));
				dto.setUpdatedAt(rs.getDate("co_updated_at"));
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

			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getHashtagId());
			pstmt.setInt(3, dto.getCategoryId());
			pstmt.setString(4, dto.getTitle());
			pstmt.setDouble(5, dto.getRating());
			pstmt.setInt(6, dto.getRegisterCount());
			pstmt.setInt(7, dto.getPricePerDay());
			pstmt.setInt(8, dto.getLike());
			pstmt.setString(9, dto.getIntroduce());
			pstmt.setString(10, dto.getCurriculum());
			pstmt.setString(11, dto.getRecommandFor());
			pstmt.setString(12, dto.getBeforeCheck());

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

			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getHashtagId());
			pstmt.setInt(3, dto.getCategoryId());
			pstmt.setString(4, dto.getTitle());
			pstmt.setDouble(5, dto.getRating());
			pstmt.setInt(6, dto.getRegisterCount());
			pstmt.setInt(7, dto.getPricePerDay());
			pstmt.setInt(8, dto.getLike());
			pstmt.setString(9, dto.getIntroduce());
			pstmt.setString(10, dto.getCurriculum());
			pstmt.setString(11, dto.getRecommandFor());	
			pstmt.setString(12, dto.getBeforeCheck());
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
	
	//유저가 구매한 강의
	public CourseDTO getOneByUserId(int co_author_us_id) {
		CourseDTO dto = new CourseDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from buy bu, course co, user us where bu.bu_user_us_id = us.us_id AND co.co_id = bu.bu_course_co_id AND bu.bu_user_us_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, co_author_us_id);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getInt("co_id"));
				dto.setUserId(rs.getInt("co_author_us_id"));
				dto.setHashtagId(rs.getInt("co_hashtag_ht_id"));
				dto.setCategoryId(rs.getInt("co_category_ca_id"));
				dto.setTitle(rs.getString("co_title"));
				dto.setRating(rs.getDouble("co_rating"));
				dto.setRegisterCount(rs.getInt("co_register_count"));
				dto.setPricePerDay(rs.getInt("co_price_per_day"));
				dto.setLike(rs.getInt("co_like"));
				dto.setIntroduce(rs.getString("co_introduce"));
				dto.setCurriculum(rs.getString("co_curriculum"));
				dto.setRecommandFor(rs.getString("co_recommand_for"));
				dto.setBeforeCheck(rs.getString("co_before_check"));
				dto.setCreatedAt(rs.getDate("co_created_at"));
				dto.setUpdatedAt(rs.getDate("co_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public int getBestCourseId() {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select lt_course_co_id as max_course_id, count(*) as cnt from like_that group by lt_course_co_id \r\n"
				+ "HAVING lt_course_co_id IS NOT NULL order by cnt desc limit 1";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt("max_course_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public CourseDTO getBestCourse() {
		CourseDTO dto = new CourseDTO();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = getBestCourseId();
		String sql = "select * from course where co_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, result);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getInt("co_id"));
				dto.setUserId(rs.getInt("co_author_us_id"));
				dto.setHashtagId(rs.getInt("co_hashtag_ht_id"));
				dto.setCategoryId(rs.getInt("co_category_ca_id"));
				dto.setTitle(rs.getString("co_title"));
				dto.setRating(rs.getDouble("co_rating"));
				dto.setRegisterCount(rs.getInt("co_register_count"));
				dto.setPricePerDay(rs.getInt("co_price_per_day"));
				dto.setLike(rs.getInt("co_like"));
				dto.setIntroduce(rs.getString("co_introduce"));
				dto.setCurriculum(rs.getString("co_curriculum"));
				dto.setRecommandFor(rs.getString("co_recommand_for"));
				dto.setBeforeCheck(rs.getString("co_before_check"));
				dto.setCreatedAt(rs.getDate("co_created_at"));
				dto.setUpdatedAt(rs.getDate("co_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public static void main(String[] args) {
		
		 CourseDAO dao = new CourseDAO();
		 
//		 CourseDTO course = new CourseDTO();
		 
//		 course.setUs_id(5); course.setHt_id(4); course.setCa_id(1);
//		 course.setTitle("제목"); course.setRating(2.6); course.setRegister_count(3);
//		 course.setPrice_per_day(20); course.setLike(4); course.setIntroduce("ㅇㅇㅇ");
//		 course.setCurriculum("ㅇㅇㅇ"); course.setRecommand_for("ㅇㅇㅇ");
//		 course.setBefore_check("ㅇㅇㅇ");
//		 
//		 System.out.print(dao.create(course));
		 

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
		
//		  CourseDTO course=new CourseDAO().getOneByUserId(5);
//		  
//		  System.out.println(course.getId()); 
//		  System.out.println(course.getUserId());
//		  System.out.println(course.getHashtagId()); 
//		  System.out.println(course.getCategoryId());
//		  System.out.println(course.getTitle());
//		  System.out.println(course.getRating());
//		  System.out.println(course.getRegisterCount());
//		  System.out.println(course.getPricePerDay());
//		  System.out.println(course.getLike());
//		  System.out.println(course.getIntroduce());
//		  System.out.println(course.getCurriculum());
//		  System.out.println(course.getRecommandFor());
//		  System.out.println(course.getBeforeCheck());
//		  System.out.println(course.getCreatedAt());
//		  System.out.println(course.getUpdatedAt());
		 System.out.println(dao.getBestCourse().getTitle());
		
	}

}
