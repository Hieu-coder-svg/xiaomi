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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Categories;
import model.ProductCart;
import model.Products;

public class ControlPrice1 extends HttpServlet {

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
            out.println("<title>Servlet ControlPrice1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlPrice1 at " + request.getContextPath() + "</h1>");
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
        //String cid = request.getParameter("cid");
        HttpSession session = request.getSession();
        Integer cid2 = (Integer) session.getAttribute("cid");
        DAOProducts dao = new DAOProducts();
        if (cid2 != null) {
            List<Products> list = dao.getPriceAscByCid(cid2);
            request.setAttribute("products", list);
        } else {
            List<Products> list = dao.getPriceAsc();
            request.setAttribute("products", list);
        }
        //List<Products> list = dao.getPriceAsc();
        DAOCategories dao1 = new DAOCategories();
        List<Categories> list1 = dao1.getAll();
        DAOProducts dao2 = new DAOProducts();
        Products p = dao2.getLast();
        request.setAttribute("categories", list1);
        //request.setAttribute("products", list);
        request.setAttribute("last", p);
        //request.setAttribute("categories", list1);
        //request.setAttribute("products", list);
        request.setAttribute("last", p);
        //request.setAttribute("categories", list1);
        //request.getRequestDispatcher("Home.jsp").forward(request, response);
        // request.setAttribute("products", list);

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
        String min1 = request.getParameter("minprice");
        String max1 = request.getParameter("maxprice");
        double min = Double.parseDouble(min1);
        double max = Double.parseDouble(max1);
        DAOProducts dao = new DAOProducts();
        HttpSession session = request.getSession();
        Integer cid2 = (Integer) session.getAttribute("cid");
        //DAOProducts dao = new DAOProducts();
        if (cid2 != null) {
            List<Products> list = dao.getPriceInRange(cid2, min, max);
            if(list.size()==0){
                request.setAttribute("notfound", "Not found Product...Please search again!");
            }
            else{
                request.setAttribute("products", list);
            }
            //request.setAttribute("products", list);
        } else {
            List<Products> list = dao.getByPrice(min, max);
            if(list.size() == 0){
                request.setAttribute("notfound", "Not found Product...Please search again!");
            }
            else{
                request.setAttribute("products", list);
            }
            //request.setAttribute("produ
            request.setAttribute("products", list);
        }
        //DAOProducts dao = new DAOProducts();
        DAOCategories dao1 = new DAOCategories();
        List<Categories> list1 = dao1.getAll();
        DAOProducts dao2 = new DAOProducts();
        Products p = dao2.getLast();
        request.setAttribute("categories", list1);
        //request.setAttribute("products", list);
        request.setAttribute("last", p);
        //request.setAttribute("categories", list1);
        //request.setAttribute("products", list);
        //request.setAttribute("last", p);
        //request.setAttribute("categories", list1);
        //request.getRequestDispatcher("Home.jsp").forward(request, response);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
