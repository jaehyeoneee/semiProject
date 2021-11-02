package dao;

import dto.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
    DBConnection db = new DBConnection();

    public ArrayList<CategoryDTO> getUsers() {
        ArrayList<CategoryDTO> categories = new ArrayList<>();
        Connection conn = db.getLocalOracle();
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "select * from category order by ca_id desc";
        try{
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
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.dbClose(rs, pstmt, conn);
        }
        return categories;
    }
    
    

    public static void main(String[] args) {
        System.out.println("한글출력 입니다.aaaㄴㄴㄴㄴ");
        ArrayList<CategoryDTO> categories = new CategoryDAO().getUsers();

        for (CategoryDTO category: categories) {
            System.out.println(category.getId());
            System.out.println(category.getName());
        }
    }
}
