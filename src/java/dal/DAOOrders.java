/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Orders;
import model.Users;


public class DAOOrders extends DBContext {

    //get last ID order
    public int getLastIdOrder() {
        String sql = "select top 1 * from Orders\n"
                + "order by OrderID desc";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt("OrderID");
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    /*
    private int OrderID;
    private Timestamp OrderDate;
    private String Status;
    private Users Customer;
    private String ReceiverName, ReceiverPhone, ReceiverAddress;
    private double Total;
     */
    //insert into order
    public void insertOrder(int id, Timestamp orderdate, String status, int CustomerID, String receiverName, String receiverPhone, String receiverAddress, double total) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([OrderID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[Status]\n"
                + "           ,[CustomerID]\n"
                + "           ,[ReceiverName]\n"
                + "           ,[ReceiverPhone]\n"
                + "           ,[ReceiverAddress]\n"
                + "           ,[Total])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setTimestamp(2, orderdate);
            st.setString(3, status);
            st.setInt(4, CustomerID);
            st.setString(5, receiverName);
            st.setString(6, receiverPhone);
            st.setString(7, receiverAddress);
            st.setDouble(8, total);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get all orders
    public List<Orders> getAllOrder(String name) {
        List<Orders> list = new ArrayList<>();
        String sql = "select * from Orders where ReceiverName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderID(rs.getInt("OrderID"));
                o.setOrderDate(rs.getTimestamp("OrderDate"));
                o.setStatus(rs.getString("Status"));
                o.setCustomerID(rs.getInt("CustomerID"));
                o.setReceiverName(rs.getString("ReceiverName"));
                o.setReceiverPhone(rs.getString("ReceiverPhone"));
                o.setReceiverAddress(rs.getString("ReceiverAddress"));
                o.setTotal(rs.getDouble("Total"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Orders> getAllOrder12() {
        List<Orders> list = new ArrayList<>();
        String sql = "select * from Orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderID(rs.getInt("OrderID"));
                o.setOrderDate(rs.getTimestamp("OrderDate"));
                o.setStatus(rs.getString("Status"));
                o.setCustomerID(rs.getInt("CustomerID"));
                o.setReceiverName(rs.getString("ReceiverName"));
                o.setReceiverPhone(rs.getString("ReceiverPhone"));
                o.setReceiverAddress(rs.getString("ReceiverAddress"));
                o.setTotal(rs.getDouble("Total"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public double getRevenueYear(String year) {
        String sql = "select SUM(Total) from Orders\n"
                + "where OrderDate like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + year + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                double number = rs.getDouble(1);
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //getTotal Order in year
    public int getTotalOrder(String year) {
        String sql = "select COUNT(OrderID) from Orders \n"
                + "where OrderDate like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + year + "%");
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

    //getTotal Order in year
    public List<Integer> getTop3User() {
        List<Integer> list = new ArrayList();
        String sql = "select top 3 CustomerID from Orders  \n"
                + "Group by CustomerID\n"
                + " order by COUNT(OrderID)  desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt(1);
                list.add(number);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //getTotal quantity top 3 user Order in year
    public List<Integer> getQuantityTop3User() {
        List<Integer> list = new ArrayList();
        String sql = "select top 3 COUNT(CustomerID) from Orders  \n"
                + "Group by CustomerID\n"
                + " order by COUNT(OrderID)  desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt(1);
                list.add(number);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();
        /*List<Orders> list = dao.getAllOrder("davidroberts");
        for (Orders orders : list) {
            System.out.println(list.size());
            System.out.println(orders.getReceiverAddress());
        }*/
        //double num = dao.getRevenueYear("2022");
        //System.out.println(num);
        //int num = dao.getTotalOrder("2024");
        //System.out.println(num);
        List<Integer> list = dao.getTop3User();
        int id1 = list.get(0);
        System.out.println(id1);
    }
}
