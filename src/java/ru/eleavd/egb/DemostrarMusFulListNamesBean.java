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
import javax.ejb.Stateless;

@Stateless
public class DemostrarMusFulListNamesBean {
 @Resource(name = "dataSource")
    private DataSource dataSource;
private static final Logger logger = Logger.getLogger(MuseumBean.class);   
public List<MuseumsDTO> demonsrarFullListMusNames() {  
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_ALL_MusNames());        
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum = new MuseumsDTO();
                String name = rset.getString("name");
                museum.setName(name);
                respuesta.add(museum);
               }         
          rset.close();
          psta.close();   
            }
        catch(Exception e) { logger.error("Error  ", e);}
        finally {
                   try {
                        if (conn != null) { conn.close(); }             
                        } 
                    catch (Exception ex) {
                logger.error("Error соединения с БД !!!", ex);}
                }       
        return  respuesta;
    }   
}
