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
import model.Cart;
import model.Products;

public class DAOOrderDetails extends DBContext {

    //get id Product have count max
    public int getProducIdByOrderDetails() {
        String sql = "SELECT ProductID FROM OrderDetails GROUP BY ProductID ORDER BY COUNT(Quantity) DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt("ProductID");
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //get Product id and quantity by OrderID
    public List<Products> getProductByOrderID(int orderid) {
        List<Products> list = new ArrayList<>();
        String sql = "";
        return list;
    }

    //Insert new Orderdetails
    public void insertOrderDetails(int orderid, int productid, int quantity) {
        String sql = "INSERT INTO [dbo].[OrderDetails] ([OrderID], [ProductID], [Quantity]) VALUES (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderid);
            st.setInt(2, productid);
            st.setInt(3, quantity);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    
    

    public static void main(String[] args) {
//        DAOOrderDetails dao = new DAOOrderDetails();
//        /*int num = dao.getProducIdByOrderDetails();
//        System.out.println(num);*/
//        //dao.insertOrderDetails(115, 8, 2);
//        List<Integer> list = dao.getQuantityTop5();
//        for (Integer integer : list) {
//            System.out.println(integer);
        }
    }

