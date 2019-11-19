package pl.polsl.lab3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// If you use annotation, then you don't need the mapping in web.xml
@WebServlet(name = "CalculatorServlet", urlPatterns = {"/CalculatorServlet"})
public class CalculatorServlet extends HttpServlet {

    // EJB injection is possible in servlets
    @EJB
    private CalculatorBean calculatorBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // may return null values for GET request
            String arg1 = request.getParameter("arg1");
            String arg2 = request.getParameter("arg2");

            if (arg1 != null && arg2 != null
                    && arg1.matches("-?[0-9]+")
                    && arg2.matches("-?[0-9]+")) {
                out.println(String.format("%s + %s = %d<br/>",
                        arg1, arg2,
                        calculatorBean.add(Integer.parseInt(arg1),
                                Integer.parseInt(arg2))));
            }
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
