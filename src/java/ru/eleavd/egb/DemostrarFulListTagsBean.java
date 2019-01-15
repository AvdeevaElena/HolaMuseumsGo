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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateful
public class DemostrarFulListTagsBean {
     @Resource(name = "dataSource")
private DataSource dataSource;
private static final Logger logger = Logger.getLogger(DemostrarFulListTagsBean.class);  
    public List<MuseumsDTO> demonsrarFullListTags() { 
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); // !! ноль не нужен был 
        try {
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_ALL_Tags());        
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum = new MuseumsDTO();
                String tag = rset.getString("tag_title");
                museum.setMus_tag(tag);
                respuesta.add(museum);
               }         
          rset.close();
          psta.close();   
            }
        catch(Exception e) { logger.error("Error al buscar alumnos por nombre like: ", e);}
        finally {
                   try {
                        if (conn != null) { conn.close(); }             
                        } 
                    catch (Exception ex) {
                logger.error("Error al cerrar la conexion !!!", ex);}
                }       
        return  respuesta;
    }   
}
