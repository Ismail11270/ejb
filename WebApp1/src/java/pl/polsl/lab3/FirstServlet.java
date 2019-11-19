package pl.polsl.lab3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name = "FirstServlet", urlPatterns = {"/SecondServlet"})
public class FirstServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

//            Enumeration<String> names = request.getParameterNames();
//            
//            while(names.hasMoreElements()) {
//                String name = names.nextElement();
//                out.println(name + " " + request.getParameter(name) + "<br/>");
//            }
            String name = request.getParameter("name");
            String age = request.getParameter("age");

//            if("admin".equals(name)) {
//                HttpSession session = request.getSession();
//                session.setAttribute("LOGGED_IN", true);
//            }
//          
            // gets access to global parameters
            out.println(getServletContext().getInitParameter("msg")
                    + " " + request.getRequestURI()
                    + "<br/>");
            if (name == null || name.isEmpty()) { // name.length() == 0 // name.equals("")
                // attribute is destroyed after the request is finished
                request.setAttribute("msgType", "msg1");
                // redirect usually modifying the response
                request.getRequestDispatcher("/SecondServlet")
                        .include(request, response);
                // redirects to a standard error page
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
//                        "Name should not be empty");
//                return;
            }
            if (age == null || !age.matches("[0-9]+")) {
                request.setAttribute("msgType", "msg2");
                request.getRequestDispatcher("/SecondServlet")
                        .include(request, response);
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
//                        "Age should be an integer");
//                response.sendRedirect("http://google.com");
//                return;
            }
            // redirect after which we usually do not intend to return to the current servlet
//            request.getRequestDispatcher("/SecondServlet").
//                    forward(request, response);

//            System.out.println("I'm here");
            out.println(name + " " + age + "<br/>");
//            request.getRequestDispatcher("/SecondServlet").
//                    forward(request, response);
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
