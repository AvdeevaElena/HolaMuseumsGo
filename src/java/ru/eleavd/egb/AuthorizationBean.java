package ru.eleavd.egb;
import javax.ejb.Stateful;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;

@Stateful
public class AuthorizationBean {
     @Resource(name = "dataSource")
    private DataSource dataSource;
private static final Logger logger = Logger.getLogger( AuthorizationBean.class);  
    public List<MuseumsDTO> SeachUserID(String parameter1, String parameter2) { 
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_USER_ID_AUTHORIZATION());        
            psta.setString(1,"%"+parameter1+"%"); 
            psta.setString(2,"%"+parameter2+"%");
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {  
                MuseumsDTO user = new MuseumsDTO();
                Integer user_id = rset.getInt("USER_ID");
                user.setUser_id(user_id);
                respuesta.add(user);
               }         
          rset.close();
          psta.close();   
            }
        catch(Exception e) { logger.error("Error, пользователь не найден ", e);}
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
