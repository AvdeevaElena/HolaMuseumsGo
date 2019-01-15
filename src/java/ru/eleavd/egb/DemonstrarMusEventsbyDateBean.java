package ru.eleavd.egb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;

@Stateless
public class DemonstrarMusEventsbyDateBean {
 @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventsbyDateBean.class);
    public List<MuseumsDTO> SeachEventsPorDate(String parameter1) { 
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); // !! ноль не нужен был
        try {
            conn = dataSource.getConnection();
            String parameter2=parameter1;
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_Mus_Event_byDate());        
            psta.setString(1,parameter1); 
            psta.setString(2,parameter2);
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum_d = new MuseumsDTO();  
                String mus_name = rset.getString("NAME");
                museum_d.setName_d(mus_name);
                Integer id_d = rset.getInt("ID_EVENT");       
                museum_d.setId_d(id_d);        
                String mus_event = rset.getString("TITLE_EVENT");
                museum_d.setTitle_event_d(mus_event);
                String type_event = rset.getString("TYPE_EVENT");
                museum_d.setType_d(type_event);
                String time_begin =rset.getString("TIME_EVENT");
                museum_d.setTime_begin_d(time_begin);
                respuesta.add(museum_d);
            }         
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error(" Нет событий указанную дату ", e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (Exception ex) { logger.error("Нет соединения с базой !!!", ex);}
        }       
        return  respuesta;
    }   

   
}
