package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.HashtagDTO;

public class HashtagDAO {

	DBConnection db = new DBConnection();

	public ArrayList<HashtagDTO> get() {
		
		ArrayList<HashtagDTO> hashtags = new ArrayList<>();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql = "select * from hashtag order by ht_id desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HashtagDTO hashtag = new HashtagDTO();
				hashtag.setId(rs.getInt("ht_id"));
				hashtag.setName(rs.getString("ht_name"));
				hashtag.setCreated_at(rs.getDate("ht_created_at"));
				hashtag.setUpdated_at(rs.getDate("ht_updated_at"));
				hashtags.add(hashtag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return hashtags;
	}

	public HashtagDTO getOne(int ht_id) {
		HashtagDTO dto = new HashtagDTO();

		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from hashtag where ht_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ht_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("ht_id"));
				dto.setName(rs.getString("ht_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int create(HashtagDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "insert into hashtag(ht_name) values(?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getName());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public int update(HashtagDTO dto) {
		int result = 0;
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "update hashtag set ht_name=? where ht_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
		return result;
	}

	public void delete(int ht_id) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;

		String sql = "delete from hashtag where ht_id=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ht_id);

			pstmt.execute();
		} catch (SQLException e) {
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	public static void main(String[] args) {

		//HashtagDAO dao = new HashtagDAO();
		
		//dao.delete(1);
		
		
		/*
		 * HashtagDTO hashtag1 = new HashtagDTO(); hashtag1.setName("tag2");
		 * System.out.print(dao.create(hashtag1));
		 */
		 
		
		/*
		 * HashtagDTO hashtag2=new HashtagDTO(); 
		 * hashtag2.setName("tagupdate");
		 * hashtag2.setId(4); System.out.print(dao.update(hashtag2));
		 */
		
		/*
		 * ArrayList<HashtagDTO> hashtags = new HashtagDAO().get();
		 * 
		 * for (HashtagDTO hashtag: hashtags) 
		 * { System.out.println(hashtag.getId());
		 * System.out.println(hashtag.getName()); }
		 */
		
		/*
		 * HashtagDTO hashtag=new HashtagDAO().getOne(4);
		 * System.out.println(hashtag.getName());
		 */
	}
}
