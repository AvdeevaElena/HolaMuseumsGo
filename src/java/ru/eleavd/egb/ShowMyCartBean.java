package ru.eleavd.egb;
import javax.ejb.Stateful;
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

@Stateful
public class ShowMyCartBean {
  @Resource(name = "dataSource")
    private DataSource dataSource;
 
private static final Logger logger = Logger.getLogger( AuthorizationBean.class);  
    public List<MuseumsDTO> showCart(int parameter) throws SQLException {
        Connection conn = null;        
        PreparedStatement psta= null;
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try {
            conn = dataSource.getConnection();
            psta= conn.prepareStatement(MuseumsDTO.getSELECT_USER_CART_INFO());
            psta.setInt(1,parameter);
            ResultSet rset = psta.executeQuery();
                while (rset.next()) {                
                MuseumsDTO cart = new MuseumsDTO();
                String user_login = rset.getString("USER_LOGIN");
                cart.setCart_user_login(user_login);
                String title_event = rset.getString("TITLE_EVENT");
                cart.setCart_title_event(title_event);
                String data_begin = rset.getString("DATA_BEGIN");
                cart.setCart_data_begin(data_begin);
                String  data_last =  rset.getString("DATA_LAST"); 
                cart.setCart_data_last(data_last);
                respuesta.add(cart);
            }         
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error("ERROR, Не удалось  демонстрировать карту событий пользователя: ", e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (Exception ex) { logger.error("Error в соединении с БД !!!", ex);}
        }       
        return  respuesta;
    }     
}
