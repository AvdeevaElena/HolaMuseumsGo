
package ru.eleavd.egb;

import java.sql.Connection;
import javax.ejb.Stateful;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.eleavd.dto.MuseumsDTO;
import java.util.logging.Level;
import ru.eleavd.dto.DataTransferObject;
@Stateful
public class RecordEventBean {
    @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(RecordEventBean.class);
    public List<MuseumsDTO> recordToEvent(int parameter1,int parameter2 ) {   
        Connection conn = null;      
        PreparedStatement psta = null; 
        PreparedStatement psta_cheack = null;  
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        MuseumsDTO user_record = new MuseumsDTO();
        try {
            conn = dataSource.getConnection();
            psta_cheack= conn.prepareStatement(MuseumsDTO.getSELECT_CHECK_USER_CART());
            psta_cheack.setInt(1,parameter1);
            psta_cheack.setInt(2,parameter2); 
            ResultSet rset_cheack = psta_cheack.executeQuery();
            int row = rset_cheack.getRow();     
            if (row!=0)
            {
            psta = conn.prepareStatement(MuseumsDTO.getINSERT_RECORD_USER_EVENT());        
            psta.setInt(1,parameter1);
            psta.setInt(2,parameter2);  
            ResultSet rset = psta.executeQuery();
     
            while (rset.next()) {                
                String record_ok ="Вы записвны на мероприятие";
                user_record.setRecord_status(record_ok);
                respuesta.add(user_record);
            }         
            rset.close();
            psta.close();   
        }
            else {
              while (rset_cheack.next()) {         
                        Integer user_cart = rset_cheack.getInt("CARD_ID");    
                        String record_ok ="Вы уже записывались на  данное мероприятие, проверьте свою карту";
                        user_record.setRecord_status(record_ok);
                        respuesta.add(user_record);
              }                 
            }
         rset_cheack.close();
         psta_cheack.close();     
        }    
        catch(Exception e) {  logger.error("Не  удалось записаться ", e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (Exception ex) { logger.error("проблемы соединения с БД !!!", ex);}
        }       
        return  respuesta;
    }   
}
