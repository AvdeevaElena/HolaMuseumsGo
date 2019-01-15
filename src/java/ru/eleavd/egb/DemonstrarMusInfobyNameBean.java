package ru.eleavd.egb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
@Stateless
public class DemonstrarMusInfobyNameBean {
    @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(MuseumBean.class);
    public List<MuseumsDTO> SeachPorMuseumName(String parameter) {      
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>();   
        try {   
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_LIKE_MUSNAME());        
            psta.setString(1,"%"+parameter+"%"); 
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum = new MuseumsDTO();
                String name = rset.getString("name");
                museum.setName(name);
                String info_mus = rset.getString("info_mus");
                museum.setInfo_mus(info_mus);
                String address = rset.getString("address");
                museum.setAddress(address);
                String scheduller = rset.getString("scheduler"); 
                museum.setScheduller(scheduller);
                String info_tickets = rset.getString("info_tickets");
                museum.setInfo_tickets(info_tickets);   
                respuesta.add(museum);
            }             
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error("Error al buscar alumnos por nombre like: ", e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (Exception ex) { logger.error("Error al cerrar la conexion !!!", ex);}
        }          
        return  respuesta;
    }      
}
