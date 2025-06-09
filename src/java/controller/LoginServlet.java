/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LoginServlet xử lý các yêu cầu liên quan đến đăng nhập
 * Servlet này xử lý cả GET request (hiển thị form đăng nhập) và 
 * POST request (xử lý thông tin đăng nhập)
 * @author hiepn
 */
@WebServlet(name="LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
    /**
     * Xử lý GET request - hiển thị form đăng nhập
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet xảy ra
     * @throws IOException nếu có lỗi I/O xảy ra
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    } 

    /**
     * Xử lý POST request - xác thực thông tin đăng nhập
     * Nếu đăng nhập thành công, tạo session và chuyển hướng đến trang danh sách
     * Nếu đăng nhập thất bại, hiển thị thông báo lỗi và quay lại form đăng nhập
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet xảy ra
     * @throws IOException nếu có lỗi I/O xảy ra
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Lấy thông tin đăng nhập từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Kiểm tra thông tin đăng nhập
        if(authenticate(username, password)) {
            // Nếu đăng nhập thành công, tạo session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            // Chuyển hướng đến trang danh sách
            response.sendRedirect("list");
        } else {
            // Nếu đăng nhập thất bại, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Sai username hoặc password!");
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    /**
     * Phương thức kiểm tra thông tin đăng nhập (giả lập)
     * @param username tên đăng nhập cần kiểm tra
     * @param password mật khẩu cần kiểm tra
     * @return true nếu thông tin đăng nhập hợp lệ, false nếu không hợp lệ
     */
    private boolean authenticate(String username, String password) {
        // Kiểm tra username và password có trùng khớp không (mô phỏng kiểm tra với DB)
        return "admin".equals(username) && "123456".equals(password);
    }

    /** 
     * Trả về mô tả ngắn về servlet
     * @return chuỗi mô tả servlet
     */
    @Override
    public String getServletInfo() {
        return "LoginServlet - Xử lý đăng nhập người dùng";
    }
}
