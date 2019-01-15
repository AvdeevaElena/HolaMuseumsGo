
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
import ru.eleavd.egb.DemonstrarMusEventbyTagBean;
import javax.servlet.http.HttpSession;
import ru.eleavd.dto.DataTransferObject;
import ru.eleavd.egb.DemonstrarMusEventbyTag1AND2Bean;
import ru.eleavd.egb.DemonstrarMusEventbyTag1AND2AND3Bean; 
@WebServlet(name = "DemonstrarEvemtsNameMusbyTagServlet", urlPatterns = {"/DemonstrarEvemtsNameMusbyTagServlet"})
public class DemonstrarEvemtsNameMusbyTagServlet extends HttpServlet {
    @EJB
    private DemonstrarMusEventbyTagBean museumBean;
    @EJB
    private DemonstrarMusEventbyTag1AND2Bean museumBean2;
    @EJB
    private DemonstrarMusEventbyTag1AND2AND3Bean museumBean3;
    private static final Logger logger = Logger.getLogger(DemonstrarEvemtsNameMusbyTagServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DataTransferObject dto = DataTransferObject.get(session); // объект DTO, "корзина", несущая личные объекты пользователя
        List<MuseumsDTO> museum_t = new ArrayList<MuseumsDTO>();
        request.setCharacterEncoding("UTF-8");
        String [] tags=request.getParameterValues("tags[]");
        Integer count =tags.length;
       try{ 
           if  (count !=0) {
              switch(count) {
                  case 1:  
                           String tag_1 =tags[0];
                            museum_t = museumBean.SeachPorTagParametr(tag_1); 
                            if (!museum_t.isEmpty()) {
                            dto.setListOfEventMus_tag1(museum_t);  }
                            else session.setAttribute("error", "События с данным тэгом отсутствуют :"+tag_1  );break; 
                  case 2:   
                            tag_1 =tags[0];
                            String tag_2 =tags[1];
                            museum_t = museumBean2.SeachPor2AndTagsParametr(tag_1, tag_2); 
                            if (!museum_t.isEmpty()) {
                            dto.setListOfEventMus_tag1(museum_t);  }
                            else session.setAttribute("error", "События с комбинацией из данных тэгов отсутствуют :"+tag_1+", "+tag_2  );break; 
                  case 3:  
                            tag_1 =tags[0];
                            tag_2 =tags[1];
                            String tag_3 =tags[2];
                          museum_t = museumBean3.SeachPor3AndTagsParametr(tag_1, tag_2, tag_3); 
                           if (!museum_t.isEmpty()) {
                            dto.setListOfEventMus_tag1(museum_t);  }
                           else session.setAttribute("error", "События с комбинацией из данных тэгов отсутствуют :"+tag_1+", "+tag_2+", "+tag_3 );break; 
                                   
                  default: session.setAttribute("error", "Можно выюорать не более трех тэгов : "); 
                           break;
               }
            }
           else session.setAttribute ("error","Ниодного тэга не выбрано");
       }
       catch (Exception e) { logger.error("Ошибка в Сервлете: ", e);}
       
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
         //тут бывают ошибки если указаной кодировки не существует
         catch (Throwable e){answer=src;}
  return answer;
 }
}

    


