/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.daoUsers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Users;

public class ControlDeleteAccount extends HttpServlet {

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
            out.println("<title>Servlet ControlDeleteAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlDeleteAccount at " + request.getContextPath() + "</h1>");
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
        String id1 = request.getParameter("delete");
        daoUsers dao = new daoUsers();
        int id = Integer.parseInt(id1);
        dao.deleteUser(id);
        //List<Users> list = dao.getAllUsers();
        List<Users> list = dao.getAllUsersByRole();
        request.setAttribute("datauser", list);
        int count = dao.getTotalUsers();
        int endpage = count / 18;
        if (count % 3 != 0) {
            endpage++;
        }
        String index1 = request.getParameter("index");
        if (index1 == null) {
            index1 = "1";
        }
        int index = Integer.parseInt(index1);
        List<Users> list1 = dao.getUsersOffFetch(index);
        request.setAttribute("list", list);
        request.setAttribute("list1", list1);
        request.setAttribute("endpage", endpage);
        request.setAttribute("datauser", list);
        request.setAttribute("annouce", "Delete User Successfully!");
        request.getRequestDispatcher("Account.jsp").forward(request, response);
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
        daoUsers dao = new daoUsers();
        String name = request.getParameter("name");
        Users u = dao.checkUsers(name);
        List<Users> list = dao.getAllUsersByRole();
        request.setAttribute("datauser", list);
        int count = dao.getTotalUsers();
        int endpage = count / 18;
        if (count % 3 != 0) {
            endpage++;
        }
        String index1 = request.getParameter("index");
        if (index1 == null) {
            index1 = "1";
        }
        int index = Integer.parseInt(index1);
        List<Users> list1 = dao.getUsersOffFetch(index);
        request.setAttribute("datauser", list);
        if (u != null) {
            request.setAttribute("error", "user name already exist! Please enter another username...");
            request.setAttribute("list1", list1);
            request.setAttribute("endpage", endpage);
            request.getRequestDispatcher("Account.jsp").forward(request, response);
        } else {
            String pass = request.getParameter("password");
            String role1 = request.getParameter("role");
            String repass = request.getParameter("repassword");
            if (pass.equals(repass)) {
                int id = dao.getLastId();
                int role = Integer.parseInt(role1);
                dao.insertUser(id + 1, name, pass, role);
                //List<Users> list1 = dao.getAllUsers();
                request.setAttribute("success", "Insert new User successful!");
                int count1 = dao.getTotalUsers();
                int endpage1 = count1 / 18;
                if (count1 % 3 != 0) {
                    endpage1++;
                }
                String index2 = request.getParameter("index");
                if (index2 == null) {
                    index2 = "1";
                }
                int index3 = Integer.parseInt(index2);
                List<Users> list2 = dao.getUsersOffFetch(index3);
                //List<Users> list1 = dao.getUsersOffFetch(index);
                request.setAttribute("list1", list2);
                request.setAttribute("endpage", endpage1);
                request.getRequestDispatcher("Account.jsp").forward(request, response);
            } else {
                request.setAttribute("errorpass", "password and repassword not same! Please enter again...");
                request.setAttribute("list1", list1);
                request.setAttribute("endpage", endpage);
                request.getRequestDispatcher("Account.jsp").forward(request, response);
            }

        }

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
