package ru.eleavd.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ru.eleavd.dto.DataTransferObject;
import ru.eleavd.dto.MuseumsDTO;
import ru.eleavd.egb.DemonstrarEvemtsTypeANDMusBean;
import ru.eleavd.egb.MailBean;
import ru.eleavd.egb.Mailer;

@WebServlet(name = "DailyMailServlet", urlPatterns = {"/DailyMailServlet"})
public class DailyMailServlet extends HttpServlet {
    @EJB
    private MailBean museumBean;
    private String fromEmail="holamuseum@gmail.com";
    private Mailer mail_from_today;
    private static final Logger logger = Logger.getLogger(DailyMailServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DataTransferObject dto = DataTransferObject.get(session); 
       // DataTransferObject dto_mail = DataTransferObject.get(session);
        List<MuseumsDTO> daily_mail = dto.getListOfDailyMail(); 
        List<MuseumsDTO> museum_mail = new ArrayList<MuseumsDTO>();
        request.setCharacterEncoding("UTF-8");
        try {
                museum_mail = museumBean.SeachMailer(); 
                if (!museum_mail.isEmpty()) {
                    dto.setListOfDailyMail(museum_mail); 
                String subject = daily_mail.get(0).getTitle_event_m(); 
                String text =daily_mail.get(0).getLogin_m();
                String toEmail =daily_mail.get(0).getEmail_m();
                Boolean was_sand=mail_from_today.send(subject, text, fromEmail, toEmail);
                } else {
                    session.setAttribute("error", "Рассылки сегодня нет ");
                }
            }
      catch (Exception e) {

            logger.error("Error в сервлете : ", e);
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
