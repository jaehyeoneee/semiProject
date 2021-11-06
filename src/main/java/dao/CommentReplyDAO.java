package dao;

import dto.CommentReplyDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentReplyDAO {
    DBConnection db = new DBConnection();

    public ArrayList<CommentReplyDTO> get() {

        ArrayList<CommentReplyDTO> commentReplyReplies= new ArrayList<>();
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "select * from comment_reply order by cr_id desc";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CommentReplyDTO commentReply = new CommentReplyDTO();

                commentReply.setId(rs.getInt("cr_id"));
                commentReply.setAuthorId(rs.getInt("cr_author_us_id"));
                commentReply.setCommentId(rs.getInt("cr_comment_cm_id"));
                commentReply.setContent(rs.getString("cr_content"));
                commentReply.setLike(rs.getInt("cr_like"));
                commentReply.setCreatedAt(rs.getDate("cr_created_at"));
                commentReply.setUpdatedAt(rs.getDate("cr_updated_at"));

                commentReplyReplies.add(commentReply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return commentReplyReplies;
    }
    public CommentReplyDTO getOne(int cr_id) {
        CommentReplyDTO dto = new CommentReplyDTO();

        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from comment_reply where cr_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, cr_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto.setId(rs.getInt("cr_id"));
                dto.setAuthorId(rs.getInt("cr_author_us_id"));
                dto.setCommentId(rs.getInt("cr_comment_cm_id"));
                dto.setContent(rs.getString("cr_content"));
                dto.setLike(rs.getInt("cr_like"));
                dto.setCreatedAt(rs.getDate("cr_created_at"));
                dto.setUpdatedAt(rs.getDate("cr_updated_at"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return dto;
    }

    public int create(int authorId, int commentId, String content) {
        int result = 0;
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        String sql = "insert into comment_reply(cr_author_us_id,cr_comment_cm_id,cr_content) values(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, authorId);
            pstmt.setInt(2, commentId);
            pstmt.setString(3, content);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
        } finally {
            db.dbClose(pstmt, conn);
        }
        return result;
    }

    public int update(int commentReplyId, String content) {
        int result = 0;
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        String sql = "update comment_reply set cr_author_us_id=?, cr_content=? where cr_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, content);
            pstmt.setInt(2, commentReplyId);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
        } finally {
            db.dbClose(pstmt, conn);
        }
        return result;
    }

    public void delete(int cr_id) {
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        String sql = "delete from comment_reply where cr_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, cr_id);

            pstmt.execute();
        } catch (SQLException e) {
        } finally {
            db.dbClose(pstmt, conn);
        }
    }

    public static void main(String[] args) {
        CommentReplyDAO dao = new CommentReplyDAO();

//      조회
//        CommentReplyDTO commentReply1 = dao.getOne(1);
//        System.out.println(commentReply1.getContent());
//      생성
//        int result = dao.create(5, 4, "댓글내용", 0);
//        System.out.println(result);
//      수정
//        int result = dao.update(1, "hello");
//      삭제
//        dao.delete(1);


        //      전체 조회
//        ArrayList<CommentReplyDTO> commentReplies = dao.get();
//        for (CommentReplyDTO commentReply: commentReplies) {
//            System.out.println(commentReply.getContent());
//        }
    }
}
