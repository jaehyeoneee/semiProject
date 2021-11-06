package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.HashtagDTO;
import dto.LikeThatDTO;

public class LikeThatDAO {

	DBConnection db = new DBConnection();

	public ArrayList<LikeThatDTO> get() {
		
		ArrayList<LikeThatDTO> likethats = new ArrayList<>();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql = "select * from like_that order by lt_id desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LikeThatDTO likethat = new LikeThatDTO();
				likethat.setId(rs.getInt("lt_id"));
				likethat.setUserId(rs.getInt("lt_user_us_id"));
				likethat.setCommentId(rs.getInt("lt_comment_cm_id"));
				likethat.setCommentReplyId(rs.getInt("lt_comment_reply_cr_id"));
				likethat.setCourseId(rs.getInt("lt_course_co_id"));
				likethat.setCreatedAt(rs.getDate("lt_created_at"));
				likethat.setUpdatedAt(rs.getDate("lt_updated_at"));
				likethats.add(likethat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return likethats;
	}

	public LikeThatDTO getOne(int lt_id) {
		LikeThatDTO dto = new LikeThatDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from like_that where lt_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, lt_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("lt_id"));
				dto.setUserId(rs.getInt("lt_user_us_id"));
				dto.setCommentId(rs.getInt("lt_comment_cm_id"));
				dto.setCommentReplyId(rs.getInt("lt_comment_reply_cr_id"));
				dto.setCourseId(rs.getInt("lt_course_co_id"));
				dto.setCreatedAt(rs.getDate("lt_created_at"));
				dto.setUpdatedAt(rs.getDate("lt_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int create(LikeThatDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "insert into like_that(lt_user_us_id,lt_comment_cm_id,lt_comment_reply_cr_id,lt_course_co_id) values(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCommentId());
			pstmt.setInt(3, dto.getCommentReplyId());
			pstmt.setInt(4, dto.getCourseId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public int update(LikeThatDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "update like_that set lt_user_us_id=?,lt_comment_cm_id=?,lt_comment_reply_cr_id=?,lt_course_co_id=? where lt_id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCommentId());
			pstmt.setInt(3, dto.getCommentReplyId());
			pstmt.setInt(4, dto.getCourseId());
			pstmt.setInt(5, dto.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public void delete(int lt_id) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "delete from like_that where lt_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, lt_id);

			pstmt.execute();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	public void like(int userId, String type, int fk_id)
	{
		Connection conn=db.getLocalOracle();
		PreparedStatement pstmt=null;
//		course, comment, comment_reply
		
		if (type == "course") {
			type = "lt_course_co_id";
		}else if (type == "comment") {
			type = "lt_comment_cm_id";
		}else if(type == "comment_reply") {
			type = "lt_comment_reply_cr_id";
		}
		
		String sql="insert into like_that(lt_user_us_id, "+ type +") values (?,?)" ;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2,  fk_id);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	public void unlike(int userId, String type, int fk_id)
	{
		Connection conn=db.getLocalOracle();
		PreparedStatement pstmt=null;
//		course, comment, comment_reply
		
		if (type == "course") {
			type = "lt_course_co_id";
		}else if (type == "comment") {
			type = "lt_comment_cm_id";
		}else if(type == "comment_reply") {
			type = "lt_comment_reply_cr_id";
		}
		// {userId, [course_id, comment_id, comment_reply_id]}
		
		String sql="delete from like_that where lt_user_us_id = ? AND  "+ type +" = ?" ;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2,  fk_id);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	public int countLike(int userId, String type, int fk_id)
	{
		int result = 0;
		Connection conn=db.getLocalOracle();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
//		course, comment, comment_reply
		
		if (type == "course") {
			type = "lt_course_co_id";
		}else if (type == "comment") {
			type = "lt_comment_cm_id";
		}else if(type == "comment_reply") {
			type = "lt_comment_reply_cr_id";
		}
		// {userId, [course_id, comment_id, comment_reply_id]}
		
		String sql="select count(*) as count from like_that where lt_user_us_id = ? AND "+type+"=?" ;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2,  fk_id);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}
	
	

	public static void main(String[] args) {

		LikeThatDAO dao = new LikeThatDAO();
		//dao.like(5, "comment",4);
		
		
//		  LikeThatDTO likethat = new LikeThatDTO(); 
//		  likethat.setUserId(5);
//		  likethat.setCommentId(4);
//		  likethat.setCommentReplyId(2);
//		  likethat.setCourseId(6);
//		  System.out.print(dao.create(likethat));
		 
		 
		
		
//		  ArrayList<LikeThatDTO> likethats = new LikeThatDAO().get();
//		  
//		  for (LikeThatDTO likethat: likethats) 
//		  { 
//			  System.out.println(likethat.getId());
//			  System.out.println(likethat.getUserId());
//			  System.out.println(likethat.getCommentId());
//			  System.out.println(likethat.getCommentReplyId());
//			  System.out.println(likethat.getCourseId());
//			  System.out.println(likethat.getCreatedAt());
//			  System.out.println(likethat.getUpdatedAt());
//		   }
		 
		 
		
		
//		  LikeThatDTO likethat=new LikeThatDAO().getOne(1);
//		  System.out.println(likethat.getId());
//		  System.out.println(likethat.getUserId());
//		  System.out.println(likethat.getCommentId());
//		  System.out.println(likethat.getCommentReplyId());
//		  System.out.println(likethat.getCourseId());
//		  System.out.println(likethat.getCreatedAt());
//		  System.out.println(likethat.getUpdatedAt());
		 
		 
		
		
//		  LikeThatDTO likethat=new LikeThatDTO(); 
//		  likethat.setUserId(5);
//		  likethat.setCommentId(5);
//		  likethat.setCommentReplyId(2);
//		  likethat.setCourseId(6);
//		  likethat.setId(1);
//		  System.out.print(dao.update(likethat));
		 
		
		 // dao.unlike(5,"comment",4);
		
//		 dao.like(5,"comment",4);
		
		 System.out.println(dao.countLike(5,"comment",4));
	}

}
