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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
import ru.eleavd.egb.RecordEventBean;
import javax.servlet.http.HttpSession;
import ru.eleavd.dto.DataTransferObject;
@WebServlet(name = "RecordtoEventServlet", urlPatterns = {"/RecordtoEventServlet"})
public class RecordtoEventServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventsbyNameServlet.class);
    @EJB
    private RecordEventBean museumBean; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        DataTransferObject dto = DataTransferObject.get(session); // объект DTO, "корзина", несущая личные объекты пользователя
        DataTransferObject dto_go = DataTransferObject.get(session);
        List<MuseumsDTO> record_event = dto_go.getListOfEventsInfo(); 
        String rec_event =record_event.get(0).getTitle_event_ev(); // Не трогать!!!!!
        System.out.println("ЧТО ТО в СЕРВЛЕТЕ ");
        System.out.println("\n"+record_event.get(0).getTitle_event_ev());
        int rec_idv_event=record_event.get(0).getIdv_mus();
        System.out.println("\n rec_idv_event="+rec_idv_event);
         List<MuseumsDTO> record_user = dto_go.getListOfUserReg();
         int rec_user =record_user.get(0).getUser_id(); // Не трогать!!!!!
         System.out.println("ЧТО ТО в СЕРВЛЕТЕ 2");
         System.out.println("\n rec_user="+rec_user);
         List<MuseumsDTO> user_record = new ArrayList<MuseumsDTO>();
      try {
            if (rec_idv_event >0 && rec_user > 0 ) {
                user_record = museumBean.recordToEvent(rec_user,rec_idv_event); 
               if (!user_record.isEmpty()) {
                   dto.setListOfUserRecordEvent(user_record); 
                } else {
                    session.setAttribute("error", " Данные не получены : " + rec_idv_event+ " "+rec_user );
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
    
static public String encode(String src, String defpage ,String codepage)
 {
  String answer="";
         try
         {answer= new String(src.getBytes(defpage), codepage);}
         //тут бывают ошибки если указаной кодировки не существует
         catch (Throwable e){answer=src;}
  return answer;
 }     
}
