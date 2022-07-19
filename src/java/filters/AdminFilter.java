package filters;

import dataaccess.UserDB;
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
import models.User;

public class AdminFilter implements Filter {

    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        
        String userEnteredEmail = (String) session.getAttribute("email");
        
        
        UserDB userdb = new UserDB();
        User user = userdb.get(userEnteredEmail);
        
        if(!user.getRole().getRoleId().equals(1)){
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendRedirect("notes");
            return;
        }
         /*       
        String adminEmail = "cprg352+admin@gmail.com";
        if(!userEnteredEmail.equalsIgnoreCase(adminEmail)){
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendRedirect("notes");
            return;
        }*/
        
        chain.doFilter(request, response);
        
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    @Override
    public void destroy() {
        
    }
    
}
