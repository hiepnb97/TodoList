/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */

package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * AuthFilter class để xử lý việc xác thực người dùng
 * Filter này sẽ kiểm tra session để đảm bảo người dùng đã đăng nhập
 * trước khi cho phép truy cập vào các URL được bảo vệ
 * @author hiepn
 */
@WebFilter(filterName="AuthFilter", urlPatterns={"/delete", "/edit", "/add"})
public class AuthFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthFilter() {
    }

    /**
     * Phương thức chính của filter để xử lý request
     * Kiểm tra xem người dùng đã đăng nhập chưa thông qua session
     * Nếu chưa đăng nhập sẽ chuyển hướng về trang login
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     * @throws IOException if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {
        
        HttpSession session = ((HttpServletRequest)request).getSession();
        
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if(session.getAttribute("username") != null) {
            // Nếu đã đăng nhập, cho phép request đi tiếp
            chain.doFilter(request, response);
        } else {
            // Nếu chưa đăng nhập, chuyển hướng về trang login
            ((HttpServletResponse)response).sendRedirect("login");
        }
    }
    
    /**
     * Lấy đối tượng FilterConfig của filter này
     * @return đối tượng FilterConfig
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    /**
     * Thiết lập đối tượng FilterConfig cho filter này
     * @param filterConfig đối tượng FilterConfig cần thiết lập
     */
    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    /**
     * Phương thức được gọi khi filter bị hủy
     */
    public void destroy() { 
    }

    /**
     * Phương thức khởi tạo filter
     * @param filterConfig đối tượng FilterConfig để khởi tạo filter
     */
    public void init(FilterConfig filterConfig) { 
	this.filterConfig = filterConfig;
	if (filterConfig != null) {
	    if (debug) { 
		log("AuthFilter:Initializing filter");
	    }
	}
    }

    /**
     * Trả về chuỗi mô tả filter
     * @return chuỗi mô tả filter
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("AuthFilter()");
	StringBuffer sb = new StringBuffer("AuthFilter(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
