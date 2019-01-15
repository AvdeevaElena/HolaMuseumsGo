package ru.eleavd.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
import ru.eleavd.egb.RegistrationBean;
import javax.servlet.http.HttpSession;
import ru.eleavd.dto.DataTransferObject;
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);
    @EJB
    private RegistrationBean museumBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
HttpSession session = request.getSession();
        DataTransferObject dto = DataTransferObject.get(session); 
        List<MuseumsDTO> user_regfirst= new ArrayList<MuseumsDTO>();
        request.setCharacterEncoding("UTF-8");
        String reg_login = (String)request.getParameter("login");
        String reg_pas1= (String)request.getParameter("password");
        String reg_pas2 = (String)request.getParameter("password2");
        String reg_email = (String)request.getParameter("email");
        String day_b = (String)request.getParameter("day_before");
        Integer day_before= Integer.parseInt(day_b);
        try {
              if(reg_login !="null" && reg_pas1 !="null" && reg_pas2 !="null" && reg_email !="null")
              {
                  if (reg_pas1.equals(reg_pas2) ){
                    user_regfirst = museumBean.firstRegistration(reg_login,reg_pas1, reg_email,day_before);
                      if(!user_regfirst.isEmpty()) 
                             {
                                dto.setListOfUserFirstReg(user_regfirst); // сохраняем коллекцию найденных музеев в ДТО хранящийся в пользовательской сессии
                            } else {
                    session.setAttribute("error", "Не удалось зарегистрироваться ");}
                  
                     } else {session.setAttribute("error", "Пароли не совпадают!!!"  + ""); }
              
              } else { session.setAttribute("error", "Не все поля заполнены !!!");}      
        } catch (Exception e) {
            logger.error("Error в Сервлете: ", e);
        }
        String url = response.encodeRedirectURL("Registration.jsp");
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
