/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.TodoDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Todo;

/**
 * ListServlet xử lý việc hiển thị danh sách các todo
 * Servlet này chủ yếu xử lý GET request để lấy và hiển thị danh sách todo từ database
 * @author hiepn
 */
@WebServlet(name="ListServlet", urlPatterns={"/list"})
public class ListServlet extends HttpServlet {

    /**
     * Xử lý GET request - lấy và hiển thị danh sách todo
     * Phương thức này sẽ:
     * 1. Lấy tất cả todo từ database
     * 2. Đặt danh sách todo vào request attribute
     * 3. Chuyển tiếp request đến trang JSP để hiển thị
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet xảy ra
     * @throws IOException nếu có lỗi I/O xảy ra
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Tạo đối tượng TodoDAO để tương tác với database
        TodoDAO todoDAO = new TodoDAO();
        try {
            // Lấy danh sách todo từ database
            ArrayList<Todo> list = todoDAO.getAll();
            // Đặt danh sách vào request attribute để JSP có thể truy cập
            request.setAttribute("list", list);
            
            // Tạo một đối tượng chuyển tiếp đến trang JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
            // Chuyển tiếp request đến JSP
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            // Log lỗi nếu có vấn đề khi truy cập database
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /**
     * Xử lý POST request
     * Hiện tại không xử lý POST request vì chức năng list chỉ cần GET
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet xảy ra
     * @throws IOException nếu có lỗi I/O xảy ra
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
    }

    /** 
     * Trả về mô tả ngắn về servlet
     * @return chuỗi mô tả servlet
     */
    @Override
    public String getServletInfo() {
        return "ListServlet - Hiển thị danh sách todo";
    }
}
