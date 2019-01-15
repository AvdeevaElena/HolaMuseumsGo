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
public class DemonstrarMusEventsbyTypeBean { 
    @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventsbyTypeBean.class);
    public List<MuseumsDTO> SeachEventsPorTypeEvent(String parameter) {     
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>();  
        try {
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_Mus_Event_byType());        
            psta.setString(1,"%"+parameter+"%"); 
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum_et = new MuseumsDTO();
                String mus_name = rset.getString("NAME");
                museum_et.setName_bt(mus_name);
                Integer id_bt = rset.getInt("ID_EVENT");
                museum_et.setId_bt(id_bt);
                String mus_event = rset.getString("TITLE_EVENT");
                museum_et.setTitle_event_bt(mus_event);
                String data_begin = (rset.getString("DATA_BEGIN")).toString();
                museum_et.setData_begin_bt(data_begin);
                String  data_last = (rset.getString("DATA_LAST")).toString(); 
                museum_et.setData_last_bt(data_last); 
                respuesta.add(museum_et);
            }         
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error(" Нет событий в музеях данного типа", e);
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
