/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCategories;
import dal.DAOProducts;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Categories;
import model.Products;


public class ControlEditProduct extends HttpServlet {

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
            out.println("<title>Servlet ControlEditProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlEditProduct at " + request.getContextPath() + "</h1>");
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
        String edit = request.getParameter("edit");
        int id = Integer.parseInt(edit);
        DAOProducts dao = new DAOProducts();
        Products p = dao.getProductByID(id);
        DAOCategories dao1 = new DAOCategories();
        List<Categories> list1 = dao1.getAll();

        request.setAttribute("last", p);
        request.setAttribute("categories", list1);
        request.setAttribute("dataproduct", p);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
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
        DAOProducts dao = new DAOProducts();
        String id1 = request.getParameter("id");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String price1 = request.getParameter("price");
        String quantity1 = request.getParameter("quantity");
        String description = request.getParameter("description");
        String cid1 = request.getParameter("category");
        double price = Double.parseDouble(price1);
        int quantity = Integer.parseInt(quantity1);
        int cid = Integer.parseInt(cid1);
        int id = Integer.parseInt(id1);
        dao.updateProduct(name, price, quantity, description, image, cid, id);
        response.sendRedirect("manager");
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
