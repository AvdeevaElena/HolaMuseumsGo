
package ru.eleavd.egb;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;

@Stateless
public class RegistrationBean {
     @Resource(name = "dataSource")
    private DataSource dataSource;

private static final Logger logger = Logger.getLogger( AuthorizationBean.class);  
  
    public List<MuseumsDTO> firstRegistration(String parameter1, String parameter2, String parameter3, Integer parameter4) throws SQLException {

        Connection conn = null;      
        PreparedStatement psta_reg = null;    
        PreparedStatement psta= null;
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {
            conn = dataSource.getConnection();
            psta= conn.prepareStatement(MuseumsDTO.getSELECT_REGISTRATION_EMAIL());
            psta.setString(1,"%"+parameter3+"%");
            ResultSet rset = psta.executeQuery();
            if (rset.getRow()==0)
             {
                     psta_reg = conn.prepareStatement(MuseumsDTO.getINSERT_REGISTRATION());
                     psta_reg.setString(1,parameter1);
                     psta_reg.setString(2,parameter2);
                     psta_reg.setString(3,parameter3);
                     psta_reg.setInt(4,parameter4);
                      ResultSet rset_reg = psta_reg.executeQuery();
             
                       while (rset_reg.next() ) {
                          MuseumsDTO user_try_reg = new MuseumsDTO();
                          String user_login =parameter1;
                          user_try_reg.setReg_login(user_login);
                        respuesta.add(user_try_reg);
                       }
             rset_reg.close();
             psta_reg.close();
             }
               else {   
                         while (rset.next()) {
                             
                        String user_email = rset.getString("USER_EMAIL");    
                        String report_emaail =user_email+" уже зарегистрирована!";
                        MuseumsDTO user_try_reg = new MuseumsDTO();
                        user_try_reg.setReg_email(report_emaail);
                        respuesta.add(user_try_reg);} 
            }         
          rset.close();
          psta.close();   
            }
        catch(Exception e) { logger.error("Error, не удалось зарегистрироваться ", e);}
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
