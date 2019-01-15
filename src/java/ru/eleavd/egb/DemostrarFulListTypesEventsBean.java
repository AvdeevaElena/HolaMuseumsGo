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
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
@Stateless
public class DemostrarFulListTypesEventsBean {
    @Resource(name = "dataSource")
private DataSource dataSource;
private static final Logger logger = Logger.getLogger(DemostrarFulListTypesEventsBean.class);  
public List<MuseumsDTO> demonsrarFullListTypeEvents() {
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_ALL_MusTypeEvents());        
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO event_type = new MuseumsDTO();
                String type_event = rset.getString("TYPE_EVENT");
                event_type.setMus_type_event(type_event);              
                respuesta.add(event_type);
               }         
          rset.close();
          psta.close();   
            }
        catch(Exception e) { logger.error("не возможно найти вид события: ", e);}
        finally {
                   try {
                        if (conn != null) { conn.close(); }             
                        } 
                    catch (Exception ex) {
                logger.error("Error in connection !!!", ex);}
                }       
        return  respuesta;
    }   
}
