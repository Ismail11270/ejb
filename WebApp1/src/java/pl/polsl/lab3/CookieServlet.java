package pl.polsl.lab3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Cookie cookie = null;
            // may be null if no cookies defined yet
            Cookie[] cookies = request.getCookies();
            String name = request.getParameter("name");

            if (cookies != null) {
                for (Cookie c : cookies) {
                    // name is unique, non-empty, non-null, does not contain spaces and other special chars
                    if (c.getName().equals(name)) {
                        cookie = c;
                        break;
                    }
                }
            }
            if (cookie == null) {
                cookie = new Cookie(name, "1");
            } else {
                cookie.setValue(
                        Integer.toString(
                                Integer.parseInt(cookie.getValue())
                                + 1));
            }
            // adds cookie to the response - it will become the part of getCookies() array on next request
            response.addCookie(cookie);
            out.println(name + " " + cookie.getValue());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
