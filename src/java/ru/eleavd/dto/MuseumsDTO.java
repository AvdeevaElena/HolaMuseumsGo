package ru.eleavd.dto;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class MuseumsDTO implements Serializable { 
private static final String SELECT_ALL_MusNames = "Select NAME FROM MUSEUM ";   
private static final String SELECT_ALL_MusTypeEvents ="Select DISTINCT TYPE_EVENT FROM MUSEUM_EVENT";   
private static final String SELECT_LIKE_ID = "Select NAME, INFO_MUS, ADDRESS, SCHEDULER, INFO_TICKETS " +
                                                  "from MUSEUM Where ID_MUS= ?";
private static final String SELECT_LIKE_MUSNAME = "Select NAME, INFO_MUS, ADDRESS, SCHEDULER, INFO_TICKETS " +
                                                  "from MUSEUM Where NAME like ?";
private static final String SELECT_ALL_Tags ="SELECT TAG_TITLE FROM MUS_TAG"; 
private static final String SELECT_TOP_EVENTS = "SELECT * FROM (SELECT c.ID_EVENT, count (c.ID_EVENT) as \"COUNT \",e.TITLE_EVENT " +
"FROM MUSEUM_USERS_EVENTS_CARD c JOIN MUSEUM_EVENT e on c.ID_EVENT=e.ID_EVENT GROUP BY c.ID_EVENT,e.TITLE_EVENT) " +
"WHERE ROWNUM <5";
private static final String SELECT_ALL_MUS_Events="Select m.NAME, e.TITLE_EVENT,e.TYPE_EVENT, TO_CHAR(e.DATA_BEGIN,'fmDD MONTH YYYY')"
            + " as \"DATA_BEGIN\",TO_CHAR(e.DATA_LAST,'fmDD MONTH YYYY') as \"DATA_LAST\" " +
", ID_EVENT FROM MUSEUM_EVENT e, MUSEUM m WHERE m.ID_MUS =e.ID_MUS and m.Name like   ?   ORDER BY e.DATA_BEGIN";
private static final String SELECT_Mus_Event ="SELECT m.name,me.ID_EVENT, me.title_event FROM museum m " +
"JOIN museum_event me ON m.ID_MUS = me.ID_MUS JOIN event_tag et ON me.ID_EVENT = et.ID_EVENT " +
"JOIN mus_tag mt ON et.ID_TAG=mt.ID_TAG WHERE mt.TAG_TITLE in (?)   GROUP BY (m.name, me.ID_EVENT, me.title_event) "
 + " HAVING COUNT(m.name) > 0";
private static final String SELECT_Mus_Event_ny1And2Tags ="SELECT m.name,me.ID_EVENT, me.title_event FROM museum m " +
"JOIN museum_event me ON m.ID_MUS = me.ID_MUS JOIN event_tag et ON me.ID_EVENT = et.ID_EVENT " +
"JOIN mus_tag mt ON et.ID_TAG=mt.ID_TAG WHERE mt.TAG_TITLE in ( ?, ?) " +
"GROUP BY (m.name,me.ID_EVENT, me.title_event) HAVING COUNT(m.name) > 1";   
private static final String SELECT_Mus_Event_ny1And2And3Tags = "SELECT m.name,me.ID_EVENT, me.title_event " +
"FROM museum m JOIN museum_event me ON m.ID_MUS = me.ID_MUS JOIN event_tag et ON me.ID_EVENT = et.ID_EVENT " +
"JOIN mus_tag mt ON et.ID_TAG=mt.ID_TAG WHERE mt.TAG_TITLE in ( ?, ?, ?) " +
"GROUP BY (m.name, me.ID_EVENT,me.title_event) HAVING COUNT(m.name) > 2";
private static final String SELECT_Mus_Event_by1OR2Tags = "SELECT DISTINCT m.name,me.ID_EVENT, me.title_event " +
"FROM museum m JOIN museum_event me ON m.ID_MUS = me.ID_MUS JOIN event_tag et ON me.ID_EVENT = et.ID_EVENT " +
"JOIN mus_tag mt ON et.ID_TAG=mt.ID_TAG WHERE mt.TAG_TITLE in (?) " +
"or mt.TAG_TITLE in (?)";
private static final String SELECT_Mus_Event_by1Or2Or3Tags = "SELECT DISTINCT m.name,me.ID_EVENT, me.title_event " +
"FROM museum m JOIN museum_event me ON m.ID_MUS = me.ID_MUS JOIN event_tag et ON me.ID_EVENT = et.ID_EVENT " +
"JOIN mus_tag mt ON et.ID_TAG=mt.ID_TAG WHERE mt.TAG_TITLE in (?) " +
"or mt.TAG_TITLE in (?) or mt.TAG_TITLE in (?)";
private static final String SELECT_Mus_Event_byType="Select m.NAME, e.ID_EVENT, e.TITLE_EVENT, TO_CHAR(e.DATA_BEGIN,'fmDD MONTH YYYY') as \"DATA_BEGIN\", "
         + "TO_CHAR(e.DATA_LAST,'fmDD MONTH YYYY') as \"DATA_LAST\" " +
"FROM MUSEUM_EVENT e, MUSEUM m WHERE m.ID_MUS =e.ID_MUS and e.TYPE_EVENT like ? ORDER BY e.DATA_BEGIN"; 
private static final String SELECT_Mus_Event_byNameAndType = "Select m.NAME, e.ID_E,VENT, e.TYPE_EVENT, e.TITLE_EVENT, "
         + " TO_CHAR(e.DATA_BEGIN,'fmDD MONTH YYYY') as \"DATA_BEGIN\", TO_CHAR(e.DATA_LAST,'fmDD MONTH YYYY') as \"DATA_LAST\" " +
"FROM MUSEUM_EVENT e, MUSEUM m WHERE m.ID_MUS =e.ID_MUS and m.Name like  ?  and e.TYPE_EVENT like  ?   ORDER BY e.DATA_BEGIN";
 private static final String SELECT_Mus_Event_byDate="Select m.NAME, e.ID_EVENT, e.TITLE_EVENT, e.TYPE_EVENT, e.TIME_EVENT " +
"FROM MUSEUM m, MUSEUM_EVENT e " +
"WHERE m.ID_MUS =e.ID_MUS and TO_Date( ?,'yyyy/mm/dd') <= e.DATA_LAST " +
"and TO_Date( ?,'yyyy/mm/dd') >= e.DATA_BEGIN";
private static final String SELECT_EVENT_INFO ="Select TITLE_EVENT,m.NAME, TYPE_EVENT, INFO_EVENT, TO_CHAR(DATA_BEGIN,'fmDD MONTH YYYY') "
     + " as \"DATA_BEGIN\", TO_CHAR(DATA_LAST,'fmDD MONTH YYYY') as \"DATA_LAST\", " +
"TIME_EVENT, PRICE_EVENT, ID_EVENT FROM MUSEUM_EVENT e,MUSEUM m WHERE ID_EVENT= ?      and e.ID_MUS=m.ID_MUS";
private static final String SELECT_USER_AUTHORIZATION = "Select USER_LOGIN, USER_PASSWORD from MUSEUM_USER";
private static final String SELECT_USER_ID_AUTHORIZATION ="Select USER_ID from MUSEUM_USER where USER_LOGIN like   ?   " +
"and USER_PASSWORD like  ?    ";
private static final String INSERT_REGISTRATION="INSERT INTO MUSEUM_USER (USER_LOGIN, USER_PASSWORD, USER_EMAIL, USER_ID, CALL_DAYBEFORE) " +
"VALUES ( ?, ?, ?,MUSEUM_USER_SEQ.NEXTVAL, ?)";
private static final String SELECT_REGISTRATION_EMAIL = "SELECT USER_EMAIL From MUSEUM_USER WHERE USER_EMAIL like   ?    ";
private static final String INSERT_RECORD_USER_EVENT = "INSERT INTO MUSEUM_USERS_EVENTS_CARD " +
"(USER_ID, ID_EVENT,DATA_BEGIN) SELECT us.USER_ID, e.ID_EVENT, e.DATA_BEGIN FROM MUSEUM_USER us, MUSEUM_EVENT e " +
"WHERE us.USER_ID = ?    and e.ID_EVENT = (SELECT ee.ID_EVENT FROM MUSEUM_EVENT ee " +
"WHERE ee.DATA_BEGIN = (SELECT eee.DATA_BEGIN FROM MUSEUM_EVENT eee WHERE eee.ID_EVENT= ?  ))";
private static final String SELECT_USER_CART_INFO= "Select u.USER_LOGIN, e.TITLE_EVENT, TO_CHAR(c.DATA_BEGIN,'fmDD MONTH YYYY') "
     + "as \"DATA_BEGIN\", TO_CHAR(e.DATA_LAST,'fmDD MONTH YYYY') as \"DATA_LAST\" " +
"From MUSEUM_USERS_EVENTS_CARD c, MUSEUM_USER u, MUSEUM_EVENT e Where u.USER_ID =c.USER_ID " +
"and c.ID_EVENT=e.ID_EVENT and c.USER_ID = ?"; 
private static final String SELECT_CHECK_USER_CART ="Select card_id FROM MUSEUM_USERS_EVENTS_CARD " +
"WHERE USER_ID= ?     and       ID_EVENT= ?";
private static final String SYSDATE="SELECT  SYSDATE   FROM DUAL";
private static final String SELECT_MAILER_INFO="SELECT u.USER_LOGIN, u.USER_EMAIL, e.TITLE_EVENT,e.DATA_BEGIN,e.DATA_BEGIN - u.CALL_DAYBEFORE as \"CALL DATE REPORT\" " +
"FROM  MUSEUM_USER u JOIN MUSEUM_USERS_EVENTS_CARD c on u.USER_ID=c.USER_ID JOIN MUSEUM_USERS_INFO_TYPE_DR i " +
"on i.USER_ID=c.USER_ID JOIN  MUSEUM_EVENT e on e.ID_EVENT =c.ID_EVENT Where e.DATA_BEGIN - u.CALL_DAYBEFORE like SYSDATE";
private Date saydate;
private Integer id_mus;
private String  name;
private String  info_mus;
private String  address;
private String  scheduller;
private String  info_tickets;
private Integer idv_mus;
private String  mus_tag;
private String  mus_type_event;   
private String  mus_event;
private String  type_event;
private String  data_begin;
private String  data_last;
private Integer id_event;
private String t1_event;
private String t1_mus; 
private Number t1_id;
private String t2_event;
private String t2_mus; 
private Number t2_id;
private String name_bt;
private String title_event_bt;
private String data_begin_bt;
private String data_last_bt;
private Number id_bt;   
private String name_mt;
private String title_event_mt;
private String data_begin_mt;
private String data_last_mt;
private String type_mt;
private Number id_mt;    
private String name_d;
private String title_event_d;
private String time_begin_d;
private String type_d;
private Number id_d;    
private String title_event_top;
private Number count_top;
private Number id_top;
private String name_ev;
 private String title_event_ev;
private String data_begin_ev;
private String data_last_ev;
private String type_ev;
private String info_ev;
private String time_begin_ev;
private String price_ev;
private String user_login;
private String user_password;
private Integer user_id; 
   private String reg_email;
   private String reg_login;
   private String record_status;
    private String cart_user_login;
    private String cart_title_event;
    private String cart_data_begin;
    private String cart_data_last;
    private Integer day_before;
    private String login_m;
    private String email_m;
    private String title_event_m;
    private String data_begin_m;
    private String data_report;

    public Number getT1_id() {
        return t1_id;
    }
    public void setT1_id(Number t1_id) {
        this.t1_id = t1_id;
    }
    public Number getT2_id() {
        return t2_id;
    }
    public void setT2_id(Number t2_id) {
        this.t2_id = t2_id;
    }
     
    private Number user_card;
    public String getLogin_m() {
        return login_m;
    }
    public void setLogin_m(String login_m) {
        this.login_m = login_m;
    }
    public String getEmail_m() {
        return email_m;
    }
    public void setEmail_m(String email_m) {
        this.email_m = email_m;
    }
    public String getTitle_event_m() {
        return title_event_m;
    }
    public Number getId_bt() {
        return id_bt;
    }
    public void setId_bt(Number id_bt) {
        this.id_bt = id_bt;
    }
    public Number getId_mt() {
        return id_mt;
    }
    public void setId_mt(Number id_mt) {
        this.id_mt = id_mt;
    }

    public void setTitle_event_m(String title_event_m) {
        this.title_event_m = title_event_m;
    }
    public String getData_begin_m() {
        return data_begin_m;
    }
    public void setData_begin_m(String data_begin_m) {
        this.data_begin_m = data_begin_m;
    }
    public String getData_report() {
        return data_report;
    }
    public void setData_report(String data_report) {
        this.data_report = data_report;
    }
    public Number getId_d() {
        return id_d;
    }
    public void setId_d(Number id_d) {
        this.id_d = id_d;
    }
   
    public Date getSaydate() {
        return saydate;
    }
    public void setSaydate(Date saydate) {
        this.saydate = saydate;
    }
    public static String getSYSDATE() {
        return SYSDATE;
    }
    public static String getSELECT_MAILER_INFO() {
        return SELECT_MAILER_INFO;
    }
 public Integer getIdv_mus() {
        return idv_mus;
    }
    public void setIdv_mus(Integer idv_mus) {
        this.idv_mus = idv_mus;
    }
    public Integer getId_event() {
        return id_event;
    }
    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }
    public Integer getDay_before() {
        return day_before;
    }
    public void setDay_before(Integer day_before) {
        this.day_before = day_before;
    }
 public static String getSELECT_CHECK_USER_CART() {
        return SELECT_CHECK_USER_CART;
    }
public Number getUser_card() {
        return user_card;
    }
    public void setUser_card(Number user_card) {
        this.user_card = user_card;
    }
    public String getCart_user_login() {
        return cart_user_login;
    }
    public void setCart_user_login(String cart_user_login) {
        this.cart_user_login = cart_user_login;
    }
    public String getCart_title_event() {
        return cart_title_event;
    }
    public void setCart_title_event(String cart_title_event) {
        this.cart_title_event = cart_title_event;
    }
    public String getCart_data_begin() {
        return cart_data_begin;
    }
    public void setCart_data_begin(String cart_data_begin) {
        this.cart_data_begin = cart_data_begin;
    }
    public String getCart_data_last() {
        return cart_data_last;
    }
    public void setCart_data_last(String cart_data_last) {
        this.cart_data_last = cart_data_last;
    }
    public String getRecord_status() {
        return record_status;
    }
    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }
   public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getReg_email() {
        return reg_email;
    }
    public void setReg_email(String reg_email) {
        this.reg_email = reg_email;
    }
    public String getReg_login() {
        return reg_login;
    }
    public void setReg_login(String reg_login) {
        this.reg_login = reg_login;
    }
    public static String getINSERT_REGISTRATION() {
        return INSERT_REGISTRATION;
    }
   public static String getSELECT_REGISTRATION_EMAIL() {
        return SELECT_REGISTRATION_EMAIL;
    }
    public static String getINSERT_RECORD_USER_EVENT() {
        return INSERT_RECORD_USER_EVENT;
    }
public String getUser_login() {
        return user_login;
    }
    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }
    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    public static String getSELECT_USER_AUTHORIZATION() {
        return SELECT_USER_AUTHORIZATION;
    }
   public String getT2_event() {
        return t2_event;
    }
    public void setT2_event(String t2_event) {
        this.t2_event = t2_event;
    }
    public String getT2_mus() {
        return t2_mus;
    }
    public void setT2_mus(String t2_mus) {
        this.t2_mus = t2_mus;
    }
    public static String getSELECT_Mus_Event_by1OR2Tags() {
        return SELECT_Mus_Event_by1OR2Tags;
    }
    public static String getSELECT_Mus_Event_by1Or2Or3Tags() {
        return SELECT_Mus_Event_by1Or2Or3Tags;
    }
    public static String getSELECT_USER_ID_AUTHORIZATION() {
        return SELECT_USER_ID_AUTHORIZATION;
    }
    public static String getSELECT_Mus_Event_ny1And2Tags() {
        return SELECT_Mus_Event_ny1And2Tags;
    }
    public static String getSELECT_Mus_Event_ny1And2And3Tags() {
        return SELECT_Mus_Event_ny1And2And3Tags;
    }
 public String getName_ev() {
        return name_ev;
    }
    public void setName_ev(String name_ev) {
        this.name_ev = name_ev;
    }
    public String getTitle_event_ev() {
        return title_event_ev;
    }
    public void setTitle_event_ev(String title_event_ev) {
        this.title_event_ev = title_event_ev;
    }
    public String getData_begin_ev() {
        return data_begin_ev;
    }
    public void setData_begin_ev(String data_begin_ev) {
        this.data_begin_ev = data_begin_ev;
    }
    public String getData_last_ev() {
        return data_last_ev;
    }
    public void setData_last_ev(String data_last_ev) {
        this.data_last_ev = data_last_ev;
    }
    public String getType_ev() {
        return type_ev;
    }
    public void setType_ev(String type_ev) {
        this.type_ev = type_ev;
    }
    public String getInfo_ev() {
        return info_ev;
    }
    public void setInfo_ev(String info_ev) {
        this.info_ev = info_ev;
    }
    public String getTime_begin_ev() {
        return time_begin_ev;
    }
    public void setTime_begin_ev(String time_begin_ev) {
        this.time_begin_ev = time_begin_ev;
    }
    public String getPrice_ev() {
        return price_ev;
    }
    public void setPrice_ev(String price_ev) {
        this.price_ev = price_ev;
    }
    public static String getSELECT_USER_CART_INFO() {
        return SELECT_USER_CART_INFO;
    }
   public static String getSELECT_Mus_Event_byDate() {
        return SELECT_Mus_Event_byDate;
    }
    public static String getSELECT_TOP_EVENTS() {
        return SELECT_TOP_EVENTS;
    }
    public static String getSELECT_EVENT_INFO() {
        return SELECT_EVENT_INFO;
    }
     public static String getSELECT_Mus_Event_byNameAndType() {
        return SELECT_Mus_Event_byNameAndType;
    }
    public String getType_mt() {
        return type_mt;
    }
    public void setType_mt(String type_mt) {
        this.type_mt = type_mt;
    }
    public String getName_d() {
        return name_d;
    }
    public void setName_d(String name_d) {
        this.name_d = name_d;
    }
    public String getTitle_event_d() {
        return title_event_d;
    }
    public String getTitle_event_top() {
        return title_event_top;
    }
    public void setTitle_event_top(String title_event_top) {
        this.title_event_top = title_event_top;
    }
   public void setTitle_event_d(String title_event_d) {
        this.title_event_d = title_event_d;
    }
   public String getTime_begin_d() {
        return time_begin_d;
    }
    public void setTime_begin_d(String time_begin_d) {
        this.time_begin_d = time_begin_d;
    }
    public String getType_d() {
        return type_d;
    }
    public void setType_d(String type_d) {
        this.type_d = type_d;
    }
 public String getName_mt() {
        return name_mt;
    }
    public void setName_mt(String name_mt) {
        this.name_mt = name_mt;
    }
    public String getTitle_event_mt() {
        return title_event_mt;
    }
    public void setTitle_event_mt(String title_event_mt) {
        this.title_event_mt = title_event_mt;
    }
   public String getData_begin_mt() {
        return data_begin_mt;
    }
    public void setData_begin_mt(String data_begin_mt) {
        this.data_begin_mt = data_begin_mt;
    }
    public String getData_last_mt() {
        return data_last_mt;
    }
    public void setData_last_mt(String data_last_mt) {
        this.data_last_mt = data_last_mt;
    }
    public String getName_bt() {
        return name_bt;
    }
    public void setName_bt(String name_bt) {
        this.name_bt = name_bt;
    }
    public String getTitle_event_bt() {
        return title_event_bt;
    }
    public void setTitle_event_bt(String title_event_bt) {
        this.title_event_bt = title_event_bt;
    }
    public String getData_begin_bt() {
        return data_begin_bt;
    }
    public void setData_begin_bt(String data_begin_bt) {
        this.data_begin_bt = data_begin_bt;
    }
    public String getData_last_bt() {
        return data_last_bt;
    }
    public void setData_last_bt(String data_last_bt) {
        this.data_last_bt = data_last_bt;
    }
 public static String getSELECT_ALL_MusTypeEvents() {
        return SELECT_ALL_MusTypeEvents;
    }
    public String getMus_type_event() {
        return mus_type_event;
    }
    public void setMus_type_event(String mus_type_event) {
        this.mus_type_event = mus_type_event;
    }
    public static String getSELECT_Mus_Event_byType() {
        return SELECT_Mus_Event_byType;
    }
 public String getMus_event() {
        return mus_event;
    }
    public void setMus_event(String mus_event) {
        this.mus_event = mus_event;
    }
 public String getType_event() {
        return type_event;
    }
    public void setType_event(String type_event) {
        this.type_event = type_event;
    }
    public String getData_begin() {
        return data_begin;
    }
    public void setData_begin(String data_begin) {
        this.data_begin = data_begin;
    }
    public String getData_last() {
        return data_last;
    }
    public void setData_last(String data_last) {
        this.data_last = data_last;
    }

    public static String getSELECT_ALL_MUS_Events() {
        return SELECT_ALL_MUS_Events;
    }
    public String getT1_event() {
        return t1_event;
    }
    public void setT1_event(String t1_event) {
        this.t1_event = t1_event;
    }
    public String getT1_mus() {
        return t1_mus;
    }
    public void setT1_mus(String t1_mus) {
        this.t1_mus = t1_mus;
    }
    public Number getCount_top() {
        return count_top;
    }
    public void setCount_top(Number count_top) {
        this.count_top = count_top;
    }
    public Number getId_top() {
        return id_top;
    }
    public void setId_top(Number id_top) {
        this.id_top = id_top;
    }
    public static String getSELECT_Mus_Event() {
        return SELECT_Mus_Event;
    }
    public String getMus_tag() {
        return mus_tag;
    }
    public void setMus_tag(String mus_tag) {
        this.mus_tag = mus_tag;
    }
    public static String getSELECT_ALL_Tags() {
        return SELECT_ALL_Tags;
    }
    public static String getSELECT_LIKE_MUSNAME() {
        return SELECT_LIKE_MUSNAME;
    }
    public static String getSELECT_ALL_MusNames() {
        return SELECT_ALL_MusNames;
    }
    public static String getSELECT_LIKE_ID() {
        return SELECT_LIKE_ID;
    }
    public Integer getId_mus() {
        return id_mus;
    }
    public void setId_mus(Integer id_mus) {
        this.id_mus = id_mus;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInfo_mus() {
        return info_mus;
    }
    public void setInfo_mus(String info_mus) {
        this.info_mus = info_mus;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getScheduller() {
        return scheduller;
    }
    public void setScheduller(String scheduller) {
        this.scheduller = scheduller;
    }
    public String getInfo_tickets() {
        return info_tickets;
    }
    public void setInfo_tickets(String info_tickets) {
        this.info_tickets = info_tickets;
    }       
    public MuseumsDTO() {
    }  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id_mus != null ? this.id_mus.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MuseumsDTO other = (MuseumsDTO) obj;
        if (this.id_mus != other.id_mus && (this.id_mus == null || !this.id_mus.equals(other.id_mus))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "MuseumsDTO{" + "id_mus=" + id_mus + ", name=" + name + ", info_mus=" + info_mus + ", address=" + address + ", scheduller=" + scheduller +", info_tickets="+info_tickets +
                ", mus_event=" + mus_event +  ", type_event=" + type_event+", data_begin="+data_begin+", data_last="+data_last+ ", t1_event=" + t1_event +", t1_mus=" + t1_mus+", mus_type_event=" +mus_type_event+
                ", name_bt="+name_bt+", title_event_bt="+title_event_bt+", data_begin_bt="+data_begin_bt+", data_last_bt="+data_last_bt+", name_mt="+name_mt+", title_event_mt="+title_event_mt+", data_begin_mt="+data_begin_mt+
                ", data_last_mt="+data_last_mt+", type_mt="+type_mt+", name_d="+name_d+", title_event_d="+title_event_d+", time_begin_d="+time_begin_d+", type_d="+type_d+", title_event_top="+title_event_top+", count_top="+count_top+
                ", id_top="+id_top+", name_ev="+name_ev+", title_event_ev="+title_event_ev+", data_begin_ev="+data_begin_ev+", data_last_ev="+data_last_ev+", type_ev="+type_ev+", info_ev="+info_ev+", time_begin_ev="+time_begin_ev+
                ", price_ev="+price_ev+", t2_mus="+t2_mus+", t2_event="+t2_event+", reg_email="+reg_email+", reg_login="+reg_login+", record_status="+record_status+", cart_user_login="+cart_user_login+", cart_title_event"+cart_title_event+
                ", cart_data_begin="+cart_data_begin+", cart_data_last="+cart_data_last+", login_m="+login_m+" email_m="+email_m+" title_event_m="+title_event_m+" data_begin_m="+data_begin_m+" data_report="+data_report+'}';
    }  
}
