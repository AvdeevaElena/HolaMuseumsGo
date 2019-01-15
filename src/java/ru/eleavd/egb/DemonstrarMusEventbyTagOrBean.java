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
public class DemonstrarMusEventbyTagOrBean {
@Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventbyTagOrBean.class);
    public List<MuseumsDTO> SeachPorTagOrParametr(String parameter) {  
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
                museum.setT2_mus(name);
                String event = rset.getString("TITLE_EVENT");
                museum.setT2_event(event);
                respuesta.add(museum);
            }         
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error("Нет событий по даанному тегу: ", e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (Exception ex) { logger.error("Error соединения с БД!!!", ex);}
        }       
        return  respuesta;
    }   
}
