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
public class DemonstrarMusEventsbyNameBean {

    @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventsbyNameBean.class);
    public List<MuseumsDTO> SeachEventsPorMuseumName(String parameter) {
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {    
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_ALL_MUS_Events());        
            psta.setString(1,"%"+parameter+"%"); 
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum_e = new MuseumsDTO(); 
                String mus_name = rset.getString("NAME");
                museum_e.setName(mus_name);
                String mus_event = rset.getString("TITLE_EVENT");
                museum_e.setMus_event(mus_event);
                String type_event = rset.getString("TYPE_EVENT");
                museum_e.setType_event(type_event);
                String data_begin = (rset.getString("DATA_BEGIN")).toString();
                museum_e.setData_begin(data_begin);
                String  data_last = (rset.getString("DATA_LAST")).toString(); 
                museum_e.setData_last(data_last);
                Integer id_event= rset.getInt("ID_EVENT");
                museum_e.setId_event(id_event); 
                respuesta.add(museum_e);
            }         
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error(" Нет событий в музеи ", e);
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
