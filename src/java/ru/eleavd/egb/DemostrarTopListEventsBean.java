package ru.eleavd.egb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
import javax.ejb.Stateful;

@Stateful
public class DemostrarTopListEventsBean {
     @Resource(name = "dataSource")
private DataSource dataSource;  
private static final Logger logger = Logger.getLogger(DemostrarTopListEventsBean.class);  
    public List<MuseumsDTO> demonsrarTopListEvents() { 
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); // !! ноль не нужен был 
        try {
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_TOP_EVENTS());        
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum_top = new MuseumsDTO(); 
                String event_top = rset.getString("TITLE_EVENT");
                museum_top.setTitle_event_top(event_top);
                Integer id_top = rset.getInt("ID_EVENT");
                museum_top.setId_top(id_top);  
                respuesta.add(museum_top);
               }         
          rset.close();
          psta.close();   
            }
        catch(Exception e) { logger.error("Error : ", e);}
        finally {
                   try {
                        if (conn != null) { conn.close(); }             
                        } 
                    catch (Exception ex) {
                logger.error("Error в соединении с БД", ex);}
                }       
        return  respuesta;
    }   
}
