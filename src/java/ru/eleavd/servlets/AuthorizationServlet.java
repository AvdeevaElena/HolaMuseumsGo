
package ru.eleavd.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
import ru.eleavd.egb.AuthorizationBean;
import javax.servlet.http.HttpSession;
import ru.eleavd.dto.DataTransferObject;

public class AuthorizationServlet extends HttpServlet {
     private static final Logger logger = Logger.getLogger(AuthorizationServlet.class);
    @EJB
    private AuthorizationBean museumBean;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
 HttpSession session = request.getSession();
        DataTransferObject dto = DataTransferObject.get(session); 
        List<MuseumsDTO> user_reg = new ArrayList<MuseumsDTO>();
        String user_login =(String)request.getParameter("login");
        String user_password =(String)request.getParameter("password");
        try {
            if (user_login !="null" && user_password !="null"  ) {
                user_reg = museumBean.SeachUserID(user_login, user_password); 
                if (!user_reg.isEmpty()) {
                    dto.setListOfUserReg(user_reg);
                } else {
                    session.setAttribute("error", " Login и/или пароль введены неверно : " + user_login);
                }
            } else {
                session.setAttribute("error", "Не введены данные!!!"
                        + "");
            }
        } catch (Exception e) {
            logger.error("Error в Сервлете: ", e);
        }
        String url = response.encodeRedirectURL("Events.jsp");
        response.sendRedirect(url);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
