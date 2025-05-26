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
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.Products;

public class ControlManagerProduct extends HttpServlet {

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
            out.println("<title>Servlet ControlManagerProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlManagerProduct at " + request.getContextPath() + "</h1>");
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
        String index1 = request.getParameter("index");
        if (index1 == null) {
            index1 = "1";
        }
        int index = Integer.parseInt(index1);
        DAOCategories daoc = new DAOCategories();
        List<Categories> listc = daoc.getAll();
        request.setAttribute("datac", listc);
        DAOProducts dao = new DAOProducts();
        List<Products> list = dao.getAllOffFetchProduct(index);
        int count = dao.getTotalProducts();
        int page = count / 10;
        if (count % 10 != 0) {
            page++;
        }
        DAOCategories daocate = new DAOCategories();
        List<Categories> list1 = daocate.getAll();
        request.setAttribute("endpage", page);
        request.setAttribute("success", "Add new Product successful!");
        request.setAttribute("product", list);
        request.setAttribute("datac", list1);
        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
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

        //insert Product
        int id = dao.getLastProduct() + 1;
        String name = request.getParameter("name");
        Products p = dao.getInfor(name);
        if (p != null) {
            request.setAttribute("exist", "ProductName already exist!");
            String index1 = request.getParameter("index");
            if (index1 == null) {
                index1 = "1";
            }
            int index = Integer.parseInt(index1);
            DAOCategories daoc = new DAOCategories();
            List<Products> list = dao.getAllOffFetchProduct(index);
            int count = dao.getTotalProducts();
            int page = count / 10;
            if (count % 10 != 0) {
                page++;
            }
            request.setAttribute("endpage", page);
            request.setAttribute("product", list);
            request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
        } else {
            String image = request.getParameter("image");
            String price1 = request.getParameter("price");
            String quantity1 = request.getParameter("quantity");
            String description = request.getParameter("description");
            String cid1 = request.getParameter("category");
            double price = Double.parseDouble(price1);
            int quantity = Integer.parseInt(quantity1);
            int cid = Integer.parseInt(cid1);
            dao.insertProduct(id, name, price, quantity, description, image, cid);
            response.sendRedirect("manager");
        }

        //request.setAttribute("datatest", name);
        //request.getRequestDispatcher("newjsp.jsp").forward(request, response);
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
        DAOProducts dao = new DAOProducts();
        int id = dao.getLastProduct();
        System.out.println(id);
    }
}
