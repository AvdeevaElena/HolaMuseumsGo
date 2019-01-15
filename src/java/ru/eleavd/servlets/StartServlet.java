
package ru.eleavd.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
import ru.eleavd.egb.DemostrarMusFulListNamesBean;
import ru.eleavd.egb.DemostrarFulListTagsBean;
import ru.eleavd.egb.DemostrarFulListTypesEventsBean;
import ru.eleavd.egb.DemostrarTopListEventsBean;
import javax.servlet.http.HttpSession;
import ru.eleavd.dto.DataTransferObject;


public class StartServlet extends HttpServlet {
        private static final Logger logger = Logger.getLogger(StartServlet.class);
    @EJB
    private DemostrarMusFulListNamesBean museumBean;
     @EJB
    private DemostrarFulListTagsBean museumBeantags; 
     @EJB
    private DemostrarFulListTypesEventsBean museumBeanevents; 
      @EJB
    private DemostrarTopListEventsBean museumBeantop;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        HttpSession session = request.getSession();
        DataTransferObject dto = DataTransferObject.get(session); // объект DTO, "корзина", несущая личные объекты пользователя

        List<MuseumsDTO> museums = new ArrayList<MuseumsDTO>();
        List<MuseumsDTO> tags = new ArrayList<MuseumsDTO>();
        List<MuseumsDTO> types = new ArrayList<MuseumsDTO>();
        List<MuseumsDTO> top = new ArrayList<MuseumsDTO>();
        try {
top=museumBeantop.demonsrarTopListEvents();
museums = museumBean.demonsrarFullListMusNames(); 
tags = museumBeantags.demonsrarFullListTags();
types =museumBeanevents.demonsrarFullListTypeEvents();
dto.setListOfTOPTitle(top);
dto.setListOfMuseumsNames(museums); // сохраняем коллекцию найденных музеев в ДТО хранящийся в пользовательской сессии
dto.setListOfTagsTitle(tags);
dto.setListOfEventsTypeTitle(types);
        } catch (Exception e) {
            logger.error("Error en ejecucion del Servlet: ", e);
        }
        String url = response.encodeRedirectURL("StartMuseums.jsp");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}