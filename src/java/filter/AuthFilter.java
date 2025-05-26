package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Lấy URL request
        String path = req.getServletPath();

        // Danh sách các chức năng CRUD cần chặn
        boolean isRestricted = path.equals("/adminaccount") || path.equals("/manager") || path.equals("/crudcategory");

        // Kiểm tra nếu user chưa login hoặc role = 1 thì chặn truy cập
        if (isRestricted && (session == null || session.getAttribute("userRole") == null 
                || (int) session.getAttribute("userRole") == 1)) {
            res.sendRedirect("home"); // Chuyển hướng về trang login
            return;
        }
        
        chain.doFilter(request, response); // Cho phép tiếp tục xử lý nếu hợp lệ
    }

    @Override
    public void destroy() {
    }
}