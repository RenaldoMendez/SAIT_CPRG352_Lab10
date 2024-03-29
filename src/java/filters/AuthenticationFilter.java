package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // before we can use the HttpServletRequest or HttpServletResponse methods
       // we must cast ServletRequest and ServletResponse objects as the correct type
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String email = (String)session.getAttribute("email");
        
        //if there is no email session object then the user is not logged in
        //cant sendRedirect because it is an httpservlet response, so you must cast it
        if (email == null){
            //cast the response object as an HttpServletResponse
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("login");
            return;
        }
        //any code before chain.doFilter 
        // will be executed before the servlet
        chain.doFilter(request, response);
        //any code after chain.doFilter 
        // will be executed after the servlet
    }

    
    // the init and destroy methods are not needed in this case but they must be here becasue of the interface
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
