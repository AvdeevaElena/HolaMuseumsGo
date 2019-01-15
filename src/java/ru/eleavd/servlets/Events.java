package ru.eleavd.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ru.eleavd.dto.DataTransferObject;
import ru.eleavd.dto.MuseumsDTO;
import ru.eleavd.egb.EventBean;
public class Events extends HttpServlet {
 @EJB
    private EventBean museumBean;
    private static final Logger logger = Logger.getLogger(Events.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String event = request.getParameter("event");
        Integer event_int =Integer.parseInt(event);
        System.out.println(event);
        DataTransferObject dto = DataTransferObject.get(session); 
        List<MuseumsDTO> museum_ev = new ArrayList<MuseumsDTO>();
        try {
            if (event_int > 0)   
            {
                museum_ev = museumBean.SeachPorEvent(event_int); 
                if (!museum_ev.isEmpty()) {
                    dto.setListOfEventsInfo(museum_ev); 
                } else {
                    session.setAttribute("error", " НЕТ такого события : " + event);
                }
            } else {
                session.setAttribute("error", "Не введено!!!"
                        + "");
            }
        } catch (Exception e) {
            logger.error("Error в сервлете ", e);
        }
        String url = response.encodeRedirectURL("Events.jsp");
        response.sendRedirect(url);
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