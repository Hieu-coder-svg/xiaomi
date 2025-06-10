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


public class AdminControlAccount extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AdminControlAccount</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminControlAccount at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException { //Lấy danh sách người dùng và phân trang dữ liệu.
        //processRequest(request, response);
        daoUsers dao = new daoUsers();
        List<Users> list = dao.getAllUsersByRole();
        request.setAttribute("datauser", list);
        int count = dao.getTotalUsers();
        int endpage = count/18; //Tính số trang cần thiết, với mỗi trang chứa 18 người dùng.
        if(count % 18 != 0){ // Nếu số người dùng không chia hết cho 18, tăng số trang lên 1 để hiển thị những người còn lại.
            endpage++;
        }
        String index1 = request.getParameter("index");
        if(index1 == null){
            index1="1";
        }
        int index = Integer.parseInt(index1);
        List<Users> list1 = dao.getUsersOffFetch(index);
        request.setAttribute("list", list);
        request.setAttribute("list1", list1);
        request.setAttribute("endpage", endpage);
        request.getRequestDispatcher("Account.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {//Tìm kiếm người dùng theo tên từ dữ liệu đầu vào.
        //processRequest(request, response);
        String name = request.getParameter("usersearch");
        daoUsers dao = new daoUsers();
        Users u = dao.checkUsers(name);
        List<Users> list = dao.getAllUsersByRole();
        //request.setAttribute("datauser", list);
        if(u==null){
            request.setAttribute("result", "Not found user...please enter again!");
            request.setAttribute("datauser", list);
            request.getRequestDispatcher("Account.jsp").forward(request, response);
        }
        else{
            request.setAttribute("user", u);
            request.getRequestDispatcher("Account.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
