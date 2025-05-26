/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.Users;
/*
private int CategoryID;
    private String CategoryName, CategoryName;

 */
public class DAOCategories extends DBContext {

    //getAll Category
    public List<Categories> getAll() {
        String sql = "  select * from Categories ";
        List<Categories> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categories c = new Categories();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setDescription(rs.getString("CategoryName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get total Users
    public int getTotalCategories() {
        String sql = "select COUNT(*) from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt(1);
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //get next 18 Users
    public List<Categories> getCategoriesOffFetch(int index) {
        List<Categories> list = new ArrayList<>();
        String sql = "select * from Categories order by CategoryID offset ? rows fetch next 15 rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 15);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categories c = new Categories();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setDescription(rs.getString("Description"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get last id of Category
    public int getLastIdCategory() {
        String sql = "select * from Categories order by CategoryID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt(1);
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //Insert new Category
    public void insertNewCategory(int id, String name, String des) {
        String sql = "INSERT INTO [dbo].[Categories] ([CategoryID], [CategoryName], [Description]) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, name);
            pre.setString(3, des);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //delete Category
    public void deleteCategory(int id) {
        String sql = "DELETE FROM [dbo].[Categories]\n"
                + "      WHERE CategoryID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Update Category
    public void updateCategory(String name, String des, int id) {
        String sql = "UPDATE [dbo].[Categories] SET [CategoryName] = ?, [Description] = ? WHERE CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, des);
            st.setInt(3, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //get Category by id
    public Categories getCategoryByID(int id){
        String sql = "select * from Categories where CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categories c = new Categories();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setDescription(rs.getString("Description"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        DAOCategories dao = new DAOCategories();
        /*List<Categories> list = dao.getAll();
        for (Categories c : list) {
            System.out.println(c.getCategoryID() + "," + c.getCategoryName());
        }*/
        //int num = dao.getTotalCategories();
        //System.out.println(num);
        //List<Categories> list = dao.getCategoriesOffFetch(1);
        /*for (Categories categories : list) {
            System.out.println(categories.getCategoryName());
            System.out.println(list.size());
        }*/
        //int num = dao.getLastIdCategory();
        //System.out.println(num);
        //dao.insertNewCategory(5, "SmartTv", "SmartTv for everyone and have low price...");
        dao.updateCategory("smart car", "all of electric call, sport car, family car...", 5);
    }
}
