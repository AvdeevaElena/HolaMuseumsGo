package ru.eleavd.egb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;

@Stateless
public class DemonstrarMusEventbyTagBean {
 @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventbyTagBean.class);
    public List<MuseumsDTO> SeachPorTagParametr(String parameter) { 
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_Mus_Event());        
            psta.setString(1,parameter); 
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum = new MuseumsDTO();
                String name = rset.getString("NAME");
                museum.setT1_mus(name);
                museum.setT2_mus(name);
                Integer t2_id = rset.getInt("ID_EVENT");
                museum.setT1_id(t2_id);
                museum.setT2_id(t2_id); 
                String event = rset.getString("TITLE_EVENT");
                museum.setT1_event(event);
                museum.setT2_event(event);
                respuesta.add(museum);
            }           rset.close();
            psta.close();   }
        catch(Exception e) {  logger.error("Нет событий по даанному тегу: ", e);
        }
        finally {
            try {if (conn != null) {
                    conn.close();
                } } 
            catch (Exception ex) { logger.error("Error соединения с БД !!!", ex);}}       
        return  respuesta;
    }   }
