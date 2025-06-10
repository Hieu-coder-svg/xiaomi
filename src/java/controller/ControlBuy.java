
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCategories;
import dal.DAOOrderDetails;
import dal.DAOOrders;
import dal.daoUsers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import model.Users;

public class ControlBuy extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlBuy</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlBuy at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        //if product exist 
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        /*
        st.setInt(1, id);
            st.setTimestamp(2, orderdate);
            st.setString(3, status);
            st.setInt(4, CustomerID);
            st.setString(5, receiverName);
            st.setString(6, receiverPhone);
            st.setString(7, receiverAddress);
            st.setDouble(8, total);
            st.executeUpdate();
         */
        Users user = null;
        Object u = session.getAttribute("account");
        if (u != null) {
            user = (Users) u;
            //session.removeAttribute("cart");
            //session.setAttribute("size", 0);

            response.sendRedirect("checkout.jsp");
        } else {
            response.sendRedirect("Login.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone1 = request.getParameter("phone");
        String cid1 = request.getParameter("cid");
        String total1 = request.getParameter("total");
        String status = "done";
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        try {
            int cid = Integer.parseInt(cid1);
            double total = Double.parseDouble(total1);
            LocalDateTime currentDateTime = LocalDateTime.now();
            Timestamp orderDate = Timestamp.valueOf(currentDateTime);
            DAOOrders dao = new DAOOrders();
            int number = dao.getLastIdOrder();
            dao.insertOrder(number + 1, orderDate, status, cid, name, phone1, address, total);
            Cart cart1 = (Cart) session.getAttribute("cart");
            //int size = (int)(session.getAttribute("list"));
            DAOOrderDetails daoorderdetail = new DAOOrderDetails();
            List<Item> list = cart1.getItems();
            List<Item> listorder = new ArrayList<>();
            for (Item i : list) {
                int productid = i.getProduct().getProductID();
                int quantity = i.getQuantity();
                int orderid = number;
                listorder.add(i);
                //session.setAttribute("listorder", listorder);
                daoorderdetail.insertOrderDetails(orderid+1, productid, quantity);
            }
            session.setAttribute("listorder", listorder);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("home");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
