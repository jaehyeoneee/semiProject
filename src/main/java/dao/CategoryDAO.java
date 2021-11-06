package dao;

import dto.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
    DBConnection db = new DBConnection();

    public ArrayList<CategoryDTO> get() {

        ArrayList<CategoryDTO> categories = new ArrayList<>();
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "select * from category order by ca_id desc";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setId(rs.getInt("ca_id"));
                category.setName(rs.getString("ca_name"));
                category.setCreated_at(rs.getDate("ca_created_at"));
                category.setUpdated_at(rs.getDate("ca_updated_at"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return categories;
    }

    public CategoryDTO getOne(int ca_id) {
        CategoryDTO dto = new CategoryDTO();

        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from category where ca_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, ca_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto.setId(rs.getInt("ca_id"));
                dto.setName(rs.getString("ca_name"));
                dto.setCreated_at(rs.getDate("ca_created_at"));
                dto.setUpdated_at(rs.getDate("ca_updated_at"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return dto;
    }

    public int create(String name) {
        int result = 0;
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        String sql = "insert into category(ca_name) values(?)";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
        } finally {
            db.dbClose(pstmt, conn);
        }
        return result;
    }

    public int update(int ca_id, String ca_name) {
        int result = 0;
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        String sql = "update category set ca_name=? where ca_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, ca_name);
            pstmt.setInt(2, ca_id);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
        } finally {
            db.dbClose(pstmt, conn);
        }
        return result;
    }

    public void delete(int ca_id) {
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        String sql = "delete from category where ca_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, ca_id);

            pstmt.execute();
        } catch (SQLException e) {
        } finally {
            db.dbClose(pstmt, conn);
        }
    }

    public static void main(String[] args) {

//      생성
        int result = new CategoryDAO().create("hello");
        System.out.println(result);

//      수정
//        int result = new CategoryDAO().update(1, "hiroo");

//       삭제
//        new CategoryDAO().delete(1);

//        //      조회
        ArrayList<CategoryDTO> categories = new CategoryDAO().get();

        for (CategoryDTO category: categories) {
            System.out.println(category.getId());
            System.out.println(category.getName());
        }
    }
}
