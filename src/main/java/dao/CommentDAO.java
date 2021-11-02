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

		String sql = "insert into comment(cm_author_us_id,cm_course_co_id,cm_content,cm_like,cm_type) values(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCourseId());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getLike());
			pstmt.setString(5, dto.getType());

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

		String sql = "update comment set cm_author_us_id=?,cm_course_co_id=?,cm_content=?,cm_like=?,cm_type=? where cm_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCourseId());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getLike());
			pstmt.setString(5, dto.getType());
			pstmt.setInt(6, dto.getId());

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

	public static void main(String[] args) {
		
		/*
		 * CommentDAO dao = new CommentDAO();
		 * 
		 * CommentDTO comment = new CommentDTO();
		 * 
		 * comment.setUs_id(5); comment.setCo_id(4); comment.setContent("dkdk");
		 * comment.setLike(3); comment.setType("강사");
		 * 
		 * System.out.print(dao.create(comment));
		 */
		 
		/*
		 * ArrayList<CommentDTO>comments= new CommentDAO().get();
		 * 
		 * for (CommentDTO comment: comments) { System.out.println(comment.getId());
		 * System.out.println(comment.getUs_id());
		 * System.out.println(comment.getCo_id());
		 * System.out.println(comment.getContent());
		 * System.out.println(comment.getLike()); System.out.println(comment.getType());
		 * System.out.println(comment.getCreated_at());
		 * System.out.println(comment.getUpdated_at()); }
		 */
		 
		/*
		 * CommentDTO comment=new CommentDAO().getOne(1);
		 * 
		 * System.out.println(comment.getId()); System.out.println(comment.getUs_id());
		 * System.out.println(comment.getCo_id());
		 * System.out.println(comment.getContent());
		 * System.out.println(comment.getLike()); System.out.println(comment.getType());
		 * System.out.println(comment.getCreated_at());
		 * System.out.println(comment.getUpdated_at());
		 */
		 
		/*
		 * CommentDAO dao = new CommentDAO(); CommentDTO comment=new CommentDTO();
		 * 
		 * comment.setUserId(5); comment.setCourseId(4); comment.setContent("dkdku");
		 * comment.setLike(9); comment.setType("강사u");comment.setId(1);
		 * 
		 * System.out.print(dao.update(comment));
		 */
		 
		 CommentDAO dao = new CommentDAO(); dao.delete(1);	 
	}

}
