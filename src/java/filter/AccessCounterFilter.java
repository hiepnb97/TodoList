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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AccessCounterFilter class để đếm số lượt truy cập cho mỗi URL trong ứng dụng
 * Filter này sẽ theo dõi và đếm số lần mỗi URL được truy cập
 * Sử dụng ConcurrentHashMap để đảm bảo thread-safe khi nhiều request cùng truy cập
 * @author hiepn
 */
@WebFilter(filterName="AccessCounterFilter", urlPatterns={"/*"})
public class AccessCounterFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AccessCounterFilter() {
    } 

    /**
     * Map lưu trữ số lượt truy cập cho mỗi URL
     * Sử dụng ConcurrentHashMap và AtomicInteger để đảm bảo thread-safe
     */
    private static final Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();
    
    /**
     * Phương thức chính của filter để xử lý và đếm lượt truy cập
     * Tăng số lượt truy cập cho URL hiện tại và lưu vào request attribute
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     * @throws IOException if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Tăng số lượt truy cập cho trang hiện tại
        String path = httpRequest.getRequestURI();
        counter.computeIfAbsent(path, k -> new AtomicInteger(0)).incrementAndGet();
        
        request.setAttribute("visitCount", counter.get(path));
        
	chain.doFilter(request, response);
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
		log("AccessCounterFilter:Initializing filter");
	    }
	}
    }

    /**
     * Trả về chuỗi mô tả filter
     * @return chuỗi mô tả filter
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("AccessCounterFilter()");
	StringBuffer sb = new StringBuffer("AccessCounterFilter(");
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
