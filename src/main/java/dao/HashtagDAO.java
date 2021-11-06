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
				hashtag.setCategoryId(rs.getInt("ht_category_ca_id"));
				hashtag.setCreatedAt(rs.getDate("ht_created_at"));
				hashtag.setUpdatedAt(rs.getDate("ht_updated_at"));
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
				dto.setCategoryId(rs.getInt("ht_category_ca_id"));
				dto.setCreatedAt(rs.getDate("ht_created_at"));
				dto.setUpdatedAt(rs.getDate("ht_updated_at"));
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

		String sql = "insert into hashtag(ht_name,ht_category_ca_id) values(?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getCategoryId());

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

		String sql = "update hashtag set ht_category_ca_id=?,ht_name=? where ht_id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getCategoryId());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getId());

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
		
		/*
		 * HashtagDTO hashtag1 = new HashtagDTO(); hashtag1.setName("tag2");
		 * hashtag1.setCategoryId(2); System.out.print(dao.create(hashtag1));
		 */
		 
		
		/*
		 * ArrayList<HashtagDTO> hashtags = new HashtagDAO().get();
		 * 
		 * for (HashtagDTO hashtag: hashtags) { System.out.println(hashtag.getId());
		 * System.out.println(hashtag.getCategoryId());
		 * System.out.println(hashtag.getName()); }
		 */
		 
		
		/*
		 * HashtagDTO hashtag=new HashtagDAO().getOne(14);
		 * System.out.println(hashtag.getId());
		 * System.out.println(hashtag.getCategoryId());
		 * System.out.println(hashtag.getName());
		 */
		 
		
		/*
		 * HashtagDTO hashtag2=new HashtagDTO(); hashtag2.setCategoryId(2);
		 * hashtag2.setName("tagupdate22"); hashtag2.setId(10);
		 * System.out.print(dao.update(hashtag2));
		 */
		
		//dao.delete(4);
	}
}

