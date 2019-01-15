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
public class DemonstrarEvemtsTypeANDMusBean {
       @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DemonstrarEvemtsTypeANDMusBean.class);
    public List<MuseumsDTO> SeachEventsPorMusANDType(String parameter1,String parameter2) {      
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>();  
        try {        
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_Mus_Event_byNameAndType());        
            psta.setString(1,"%"+parameter1+"%");
            psta.setString(2,"%"+parameter2+"%"); 
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum_mt = new MuseumsDTO();
                String mus_name = rset.getString("NAME");
                museum_mt.setName_mt(mus_name);
                Integer id_mt = rset.getInt("ID_EVENT");
                museum_mt.setId_mt(id_mt);
                String mus_type_event = rset.getString("TYPE_EVENT");
                museum_mt.setType_mt(mus_type_event);
                String mus_event = rset.getString("TITLE_EVENT");
                museum_mt.setTitle_event_mt(mus_event);
                String data_begin = (rset.getString("DATA_BEGIN")).toString();
                museum_mt.setData_begin_mt(data_begin);
                String  data_last = (rset.getString("DATA_LAST")).toString(); 
                museum_mt.setData_last_mt(data_last);
                respuesta.add(museum_mt);
            }            
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error(" Нет событий в музеи данного типа", e);
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
