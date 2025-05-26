/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCategories;
import dal.DAOOrderDetails;
import dal.DAOProducts;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Categories;
import model.Products;

public class HomeDisplay extends HttpServlet {

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
            out.println("<title>Servlet HomeControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeControl at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        session.removeAttribute("listorderdetail");
        DAOProducts dao = new DAOProducts();
        //List<Products> list = dao.getAll();
        String index1 = request.getParameter("index");
        if (index1 == null) {
            index1 = "1";
        }
        int index = Integer.parseInt(index1);
        
        List<Products> list3 = dao.getAllOffFetchHome(index);
        int count = dao.getTotalProducts();
        int page = count / 15;
        if (count % 15 != 0) {
            page++;
        }
        //request.setAttribute("endpage", page);
        //request.setAttribute("product", list);
        DAOCategories dao1 = new DAOCategories();
        List<Categories> list1 = dao1.getAll();
        DAOProducts dao2 = new DAOProducts();
        DAOOrderDetails dao4 = new DAOOrderDetails();
        int num = dao4.getProducIdByOrderDetails();//get id Product have count max
        Products p = dao2.getProductByID(num);
         request.setAttribute("endpage", page);
        //request.setAttribute("product", list3);
        request.setAttribute("categories", list1);
        request.setAttribute("products", list3);
        request.setAttribute("last", p);
        request.setAttribute("categories", list1);
        //request.setAttribute("products", list);
        request.setAttribute("last", p);
        //request.setAttribute("success", "Order successfully, Continue shopping");
        //request.setAttribute("categories", list1);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
        
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

    public static void main(String[] args) {
        
        DAOProducts dao4 = new DAOProducts();
        List<Products> list = dao4.getProductByCategoryID(2);
        for (Products p : list) {
            System.out.println(p.getProductName()+"," + p.getDescription());
        }
    }
}
