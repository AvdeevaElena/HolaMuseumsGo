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
import ru.eleavd.egb.DemonstrarMusEventsbyDateBean;
import javax.servlet.http.HttpSession;
import ru.eleavd.dto.DataTransferObject;

@WebServlet(name = "DemonstrarMusEventsbyDateServlet", urlPatterns = {"/DemonstrarMusEventsbyDateServlet"})
public class DemonstrarMusEventsbyDateServlet extends HttpServlet {
@EJB
    private DemonstrarMusEventsbyDateBean museumBean;
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventsbyDateServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DataTransferObject dto = DataTransferObject.get(session); // объект DTO, "корзина", несущая личные объекты пользователя
        List<MuseumsDTO> museum_mt = new ArrayList<>();  
        request.setCharacterEncoding("UTF-8");
        String mus_date_st =request.getParameter("calendar");
        String mus_date =mus_date_st;
        try {  
            if (mus_date !=null) {
               museum_mt = museumBean.SeachEventsPorDate(mus_date); 
                if (!museum_mt.isEmpty()) {
                    dto.setListOfMuseumFullEventsbyDate(museum_mt); 
                } else {
                    session.setAttribute("error", "Данного типа события в музеи нет : " + mus_date_st);
                }
            } else {
                session.setAttribute("error", "Не введено!!!"
                        + "");
            }
      }
           catch (Exception e) {
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
         catch (Throwable e){answer=src;}
  return answer;
 }
}
