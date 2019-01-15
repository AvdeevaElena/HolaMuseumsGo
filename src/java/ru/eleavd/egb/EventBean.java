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
public class EventBean {
    @Resource(name = "dataSource")
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(EventBean.class);
    public List<MuseumsDTO> SeachPorEvent(Integer parameter) {    
        Connection conn = null;      
        PreparedStatement psta = null;        
        List<MuseumsDTO> respuesta = new ArrayList<MuseumsDTO>(); 
        try { 
            conn = dataSource.getConnection();
            psta = conn.prepareStatement(MuseumsDTO.getSELECT_EVENT_INFO());        
            psta.setInt(1,parameter); 
            ResultSet rset = psta.executeQuery();
            while (rset.next()) {                
                MuseumsDTO museum_ev = new MuseumsDTO();
                String title = rset.getString("TITLE_EVENT");
                museum_ev.setTitle_event_ev(title);
                String name = rset.getString("NAME");
                museum_ev.setName_ev(name);
                String info = rset.getString("INFO_EVENT");
                museum_ev.setInfo_ev(info);
                String data_begin = rset.getString("DATA_BEGIN");
                museum_ev.setData_begin_ev(data_begin);
                String  data_last =  rset.getString("DATA_LAST"); 
                museum_ev.setData_last_ev(data_last);
                String time = rset.getString("TIME_EVENT");
                museum_ev.setTime_begin_ev(time);
                String price = rset.getString("PRICE_EVENT");
                museum_ev.setPrice_ev(price);
                Integer id_event= rset.getInt("ID_EVENT");
                museum_ev.setIdv_mus(id_event);
                respuesta.add(museum_ev);
            }         
            rset.close();
            psta.close();   
        }
        catch(Exception e) {  logger.error("ERROR, Событие не найдено: ", e);
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