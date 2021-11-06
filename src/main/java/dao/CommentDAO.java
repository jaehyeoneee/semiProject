package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CommentDTO;

public class CommentDAO {

	DBConnection db = new DBConnection();

	public ArrayList<CommentDTO> get() {
		
		ArrayList<CommentDTO> comments= new ArrayList<>();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql = "select * from comment order by cm_id desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentDTO comment = new CommentDTO();
				
				comment.setId(rs.getInt("cm_id"));
				comment.setUserId(rs.getInt("cm_author_us_id"));
				comment.setCourseId(rs.getInt("cm_course_co_id"));
				comment.setContent(rs.getString("cm_content"));
				comment.setLike(rs.getInt("cm_like"));
				comment.setType(rs.getString("cm_type"));
				comment.setIsSolved(rs.getInt("cm_is_solved"));
				comment.setCreatedAt(rs.getDate("cm_created_at"));
				comment.setUpdatedAt(rs.getDate("cm_updated_at"));
				
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return comments;
	}
	public CommentDTO getOne(int cm_id) {
		CommentDTO dto = new CommentDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from comment where cm_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cm_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("cm_id"));
				dto.setUserId(rs.getInt("cm_author_us_id"));
				dto.setCourseId(rs.getInt("cm_course_co_id"));
				dto.setContent(rs.getString("cm_content"));
				dto.setLike(rs.getInt("cm_like"));
				dto.setType(rs.getString("cm_type"));
				dto.setIsSolved(rs.getInt("cm_is_solved"));
				dto.setCreatedAt(rs.getDate("cm_created_at"));
				dto.setUpdatedAt(rs.getDate("cm_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int create(CommentDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "insert into comment(cm_author_us_id,cm_course_co_id,cm_content,cm_like,cm_is_solved,cm_type) values(?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCourseId());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getLike());
			pstmt.setInt(5, dto.getIsSolved());
			pstmt.setString(6, dto.getType());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public int update(CommentDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "update comment set cm_author_us_id=?,cm_course_co_id=?,cm_content=?,cm_like=?,cm_is_solved=?,cm_type=? where cm_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCourseId());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getLike());
			pstmt.setInt(5, dto.getIsSolved());
			pstmt.setString(6, dto.getType());
			pstmt.setInt(7, dto.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public void delete(int cm_id) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "delete from comment where cm_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cm_id);

			pstmt.execute();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	public CommentDTO getByTitle(String word) {
		CommentDTO dto = new CommentDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from comment cm, comment_reply cr where cm.cm_id=cr.cr_comment_cm_id AND cm.cm_content like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%"+word+"%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("cm_id"));
				dto.setUserId(rs.getInt("cm_author_us_id"));
				dto.setCourseId(rs.getInt("cm_course_co_id"));
				dto.setContent(rs.getString("cm_content"));
				dto.setLike(rs.getInt("cm_like"));
				dto.setType(rs.getString("cm_type"));
				dto.setIsSolved(rs.getInt("cm_is_solved"));
				dto.setCreatedAt(rs.getDate("cm_created_at"));
				dto.setUpdatedAt(rs.getDate("cm_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}

	public CommentDTO getByWord(String word,String orderBy, int page) {
		CommentDTO dto = new CommentDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		if (word != "") {
			sql = "select * from comment cm, course co where cm.cm_course_co_id=co.co_id AND co.co_title like ?";
		}else if (orderBy != "") {
			if (orderBy == "like") {
				sql = "select * from comment cm, course co where cm.cm_course_co_id=co.co_id order by co.co_like DESC";		
			}else if (orderBy == "id") {
				sql = "select * from comment cm, course co where cm.cm_course_co_id=co.co_id order by co.co_id DESC";				
			}
		}else if (page != 1) {
			sql = "select * from comment cm, course co where cm.cm_course_co_id=co.co_id limit ?,?";
		}
		 
		try {
				pstmt = conn.prepareStatement(sql);
			
			if (word != "") {
				pstmt.setString(1, "%"+word+"%");
			}else if (page != 1) {
				
				pstmt.setInt(1, 1);
				pstmt.setInt(2, page);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("cm_id"));
				dto.setUserId(rs.getInt("cm_author_us_id"));
				dto.setCourseId(rs.getInt("cm_course_co_id"));
				dto.setContent(rs.getString("cm_content"));
				dto.setLike(rs.getInt("cm_like"));
				dto.setType(rs.getString("cm_type"));
				dto.setIsSolved(rs.getInt("cm_is_solved"));
				dto.setCreatedAt(rs.getDate("cm_created_at"));
				dto.setUpdatedAt(rs.getDate("cm_updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public void toggleSolvedStatus(int cm_id) {
		Connection conn=db.getLocalOracle();
		PreparedStatement pstmt=null;
		
		String sql="update comment set cm_is_solved = not cm_is_solved where cm_id=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cm_id);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public static void main(String[] args) {
		
		
		  CommentDAO dao = new CommentDAO();
//		  
//		  CommentDTO comment = new CommentDTO();
//		  
//		  comment.setUserId(5); 
//		  comment.setCourseId(6); 
//		  comment.setContent("dkdk7777");
//		  comment.setLike(3);
//		  comment.setIsSolved(1);
//		  comment.setType("강사");
//		  
//		  System.out.print(dao.create(comment));
		 
		 
		
//		  ArrayList<CommentDTO>comments= new CommentDAO().get();
//		  
//		  for (CommentDTO comment: comments) { 
//		  System.out.println(comment.getId());
//		  System.out.println(comment.getUserId());
//		  System.out.println(comment.getCourseId());
//		  System.out.println(comment.getContent());
//		  System.out.println(comment.getLike()); 
//		  System.out.println(comment.getIsSolved()); 
//		  System.out.println(comment.getType());
//		  System.out.println(comment.getCreatedAt());
//		  System.out.println(comment.getUpdatedAt()); }
		 
		 
		
//		  CommentDTO comment=new CommentDAO().getOne(4);
//		 
//		  System.out.println(comment.getId()); 
//		  System.out.println(comment.getUserId());
//		  System.out.println(comment.getCourseId());
//		  System.out.println(comment.getContent());
//		  System.out.println(comment.getLike()); 
//		  System.out.println(comment.getIsSolved()); 
//		  System.out.println(comment.getType());
//		  System.out.println(comment.getCreatedAt());
//		  System.out.println(comment.getUpdatedAt());
		 
		 
		
//		  CommentDAO dao = new CommentDAO(); 
//		  CommentDTO comment=new CommentDTO();
//		  
//		  comment.setUserId(5); 
//		  comment.setCourseId(6); 
//		  comment.setContent("dkdkuu");
//		  comment.setLike(9); 
//		  comment.setIsSolved(2);
//		  comment.setType("강사u");
//		  comment.setId(3);
//		  
//		  System.out.print(dao.update(comment));
		 
		 
//		 CommentDAO dao = new CommentDAO();
//		 dao.delete(3);	
		
//		  CommentDTO comment=new CommentDAO().getOneByTitle("r");
//		 
//		  System.out.println(comment.getId()); 
//		  System.out.println(comment.getUserId());
//		  System.out.println(comment.getCourseId());
//		  System.out.println(comment.getContent());
//		  System.out.println(comment.getLike()); 
//		  System.out.println(comment.getIsSolved()); 
//		  System.out.println(comment.getType());
//		  System.out.println(comment.getCreatedAt());
//		  System.out.println(comment.getUpdatedAt());
		
//		CommentDAO dao=new CommentDAO();
//		dao.getOneByWord(null);
		
//		CommentDAO dao=new CommentDAO();
//		 System.out.println(dao.getByTitle("d").getContent());
		
		//dao.toggleSolvedStatus(4);
		  
//		  CommentDTO comment=new CommentDAO().getByWord();
//			 
//			  System.out.println(comment.getId()); 
//			  System.out.println(comment.getUserId());
//			  System.out.println(comment.getCourseId());
//			  System.out.println(comment.getContent());
//			  System.out.println(comment.getLike()); 
//			  System.out.println(comment.getIsSolved()); 
//			  System.out.println(comment.getType());
//			  System.out.println(comment.getCreatedAt());
//			  System.out.println(comment.getUpdatedAt());
	}

}
