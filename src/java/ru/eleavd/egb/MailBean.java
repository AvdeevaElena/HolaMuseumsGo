package ru.eleavd.egb;
import java.sql.Connection;
import javax.ejb.Stateful;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
import java.util.logging.Level;
import ru.eleavd.dto.DataTransferObject;
@Stateful
public class MailBean {
@Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(EventBean.class);
    public List<MuseumsDTO> SeachMailer() {      
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {    
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_MAILER_INFO());        
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum = new MuseumsDTO();
                String login = rset.getString("USER_LOGIN");
                museum.setLogin_m(login);
                String email = rset.getString("USER_EMAIL");
                museum.setEmail_m(email);
                String info = rset.getString("TITLE_EVENT");
                museum.setTitle_event_m(info);
                String data_begin = rset.getString("DATA_BEGIN");
                museum.setData_begin_m(data_begin);
                String  data_report =  rset.getString("CALL DATE REPORT"); 
                museum.setData_report(data_report);
                respuesta.add(museum);
            }
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error("ERROR, писем не найдено: ", e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (Exception ex) { logger.error("Error в соединении с БД !!!", ex);}
        }       
        return  respuesta;
    }      
}
