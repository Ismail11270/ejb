package pl.polsl.lab3;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns = {""}, filterName = "")
public class SecretFilter implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request)
                .getSession(false);

        if (session == null
                || session.getAttribute("LOGGED_IN") == null
                || ((Boolean) session.getAttribute("LOGGED_IN")) == false) {
            // redirect = open a new address and lose current request and response
            ((HttpServletResponse) response).
                    sendRedirect(((HttpServletRequest) request).getContextPath());
        } else {
            // continue the execution to the next filter or requested resource
            chain.doFilter(request, response);
        }
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
        
    }

}
