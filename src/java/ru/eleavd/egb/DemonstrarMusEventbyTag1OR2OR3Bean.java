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
public class DemonstrarMusEventbyTag1OR2OR3Bean {
@Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DemonstrarMusEventbyTag1OR2OR3Bean.class);
    public List<MuseumsDTO> SeachPor3OrTagsParametr(String parameter1, String parameter2, String parameter3) {   
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); // !! ноль не нужен был
        try {       
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_Mus_Event_by1Or2Or3Tags());        
            psta.setString(1,parameter1); 
            psta.setString(2,parameter2);
            psta.setString(3,parameter3);
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum = new MuseumsDTO();
                String name = rset.getString("NAME");
                museum.setT2_mus(name);
                Integer t2_id = rset.getInt("ID_EVENT");
                museum.setT2_id(t2_id);
                String event = rset.getString("TITLE_EVENT");
                museum.setT2_event(event);
                respuesta.add(museum);
            }            
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error("Событий с такими тэгами нет  ", e);
        }
        finally {
            try {if (conn != null) {
                    conn.close(); }} 
            catch (Exception ex) { logger.error("Error в соединении с БД !!!", ex);}}       
        return  respuesta;
    }     }
