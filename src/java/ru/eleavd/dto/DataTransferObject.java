package ru.eleavd.dto;
import java.util.List;
import javax.servlet.http.HttpSession;

public class DataTransferObject { 

    private List<MuseumsDTO> listOfMuseumsNames = null;        
    private List<MuseumsDTO> listOfMuseums = null;
    private List<MuseumsDTO> listOfMuseumFullInfobyName =null;  
    private List<MuseumsDTO> listOfTOPTitle = null;
    private List<MuseumsDTO> listOfTagsTitle = null;
    private  List<MuseumsDTO> listOfMuseumFullEventsbyName = null;
    private  List<MuseumsDTO> ListOfEventMus_tag1 = null;
    private  List<MuseumsDTO> ListOfEventMus_tag2OR = null;              
    private List<MuseumsDTO> listOfEventsTypeTitle = null;
    private List<MuseumsDTO> ListOfEventsTitle = null;
    private List<MuseumsDTO> listOfMuseumFullEventsbyType = null;
    private List<MuseumsDTO> listOfMuseumFullEventsbyDate = null;
    private List<MuseumsDTO> listOfMuseumFullEventsbyTypeANDMus = null;
    private List<MuseumsDTO> ListOfEventsInfo = null;
    private List<MuseumsDTO> ListOfUserReg = null;
    private List<MuseumsDTO> ListOfUserFirstReg = null; 
    private List<MuseumsDTO> ListOfUserRecordEvent = null;
    private List<MuseumsDTO> ListOfCartUserEvent = null;
    private List<MuseumsDTO> ListOfDailyMail = null;

    public static DataTransferObject get(HttpSession session) { 
        DataTransferObject bean = (DataTransferObject) session.getAttribute("dto");
        if (bean == null) { 
            bean = new DataTransferObject(); 
            session.setAttribute("dto", bean); 
        } 
        return bean; 
    }
    public List<MuseumsDTO> getListOfDailyMail() {
        return ListOfDailyMail;
    }
    public void setListOfDailyMail(List<MuseumsDTO> ListOfDailyMail) {
        this.ListOfDailyMail = ListOfDailyMail;
    }
    public List<MuseumsDTO> getListOfCartUserEvent() {
        return ListOfCartUserEvent;
    }
    public void setListOfCartUserEvent(List<MuseumsDTO> ListOfCartUserEvent) {
        this.ListOfCartUserEvent = ListOfCartUserEvent;
    }
    public List<MuseumsDTO> getListOfUserFirstReg() {
        return ListOfUserFirstReg;
    }
    public void setListOfUserFirstReg(List<MuseumsDTO> ListOfUserFirstReg) {
        this.ListOfUserFirstReg = ListOfUserFirstReg;
    }
    public List<MuseumsDTO> getListOfUserRecordEvent() {
        return ListOfUserRecordEvent;
    }
    public void setListOfUserRecordEvent(List<MuseumsDTO> ListOfUserRecordEvent) {
        this.ListOfUserRecordEvent = ListOfUserRecordEvent;
    }  
    public List<MuseumsDTO> getListOfUserReg() {
        return ListOfUserReg;
    }
    public void setListOfUserReg(List<MuseumsDTO> ListOfUserReg) {
        this.ListOfUserReg = ListOfUserReg;
    }
     public List<MuseumsDTO> getListOfEventMus_tag1() {
        return ListOfEventMus_tag1;
    }
    public void setListOfEventMus_tag1(List<MuseumsDTO> ListOfEventMus_tag1) {
        this.ListOfEventMus_tag1 = ListOfEventMus_tag1;
    }
    public List<MuseumsDTO> getListOfEventMus_tag2OR() {
        return ListOfEventMus_tag2OR;
    }
    public void setListOfEventMus_tag2OR(List<MuseumsDTO> ListOfEventMus_tag2OR) {
        this.ListOfEventMus_tag2OR = ListOfEventMus_tag2OR;
    }
    public List<MuseumsDTO> getListOfEventsInfo() {
        return ListOfEventsInfo;
    }
    public void setListOfEventsInfo(List<MuseumsDTO> ListOfEventsInfo) {
        this.ListOfEventsInfo = ListOfEventsInfo;
    }
    public List<MuseumsDTO> getListOfTOPTitle() {
        return listOfTOPTitle;
    }
    public void setListOfTOPTitle(List<MuseumsDTO> listOfTOPTitle) {
        this.listOfTOPTitle = listOfTOPTitle;
    }
    public List<MuseumsDTO> getListOfMuseumFullEventsbyDate() {
        return listOfMuseumFullEventsbyDate;
    }
    public void setListOfMuseumFullEventsbyDate(List<MuseumsDTO> listOfMuseumFullEventsbyDate) {
        this.listOfMuseumFullEventsbyDate = listOfMuseumFullEventsbyDate;
    }
    public List<MuseumsDTO> getListOfMuseumFullEventsbyTypeANDMus() {
        return listOfMuseumFullEventsbyTypeANDMus;
    }
    public void setListOfMuseumFullEventsbyTypeANDMus(List<MuseumsDTO> listOfMuseumFullEventsbyTypeANDMus) {
        this.listOfMuseumFullEventsbyTypeANDMus = listOfMuseumFullEventsbyTypeANDMus;
    }
    public void setListOfMuseumFullEventsbyType(List<MuseumsDTO> listOfMuseumFullEventsbyType) {
        this.listOfMuseumFullEventsbyType = listOfMuseumFullEventsbyType;
    }
    public List<MuseumsDTO> getListOfMuseumFullEventsbyType() {
        return listOfMuseumFullEventsbyType;
    }
public List<MuseumsDTO> getListOfEventsTypeTitle() {
        return listOfEventsTypeTitle;
    }
    public void setListOfEventsTypeTitle(List<MuseumsDTO> listOfEventsTypeTitle) {
        this.listOfEventsTypeTitle = listOfEventsTypeTitle;
    }
 public List<MuseumsDTO> getListOfMuseumFullEventsbyName() {
        return listOfMuseumFullEventsbyName;
    }
    public void setListOfMuseumFullEventsbyName(List<MuseumsDTO> listOfMuseumFullEventsbyName) {
        this.listOfMuseumFullEventsbyName = listOfMuseumFullEventsbyName;
    }
public List<MuseumsDTO> getListOfEventsTitle() {
        return ListOfEventsTitle;
    }
    public void setListOfEventsTitle(List<MuseumsDTO> ListOfEventsTitle) {
        this.ListOfEventsTitle = ListOfEventsTitle;
    }
   public List<MuseumsDTO> getListOfTagsTitle() {
        return listOfTagsTitle;
    }
    public void setListOfTagsTitle(List<MuseumsDTO> listOfTagsTitle) {
        this.listOfTagsTitle = listOfTagsTitle;
    }
    public List<MuseumsDTO> getListOfMuseumsNames() {
        return listOfMuseumsNames;
    }
    public void setListOfMuseumsNames(List<MuseumsDTO> listOfMuseumsNames) {
        this.listOfMuseumsNames = listOfMuseumsNames;
    }
  public List<MuseumsDTO> getListOfMuseumFullInfobyName() {
        return listOfMuseumFullInfobyName;
    }
    public void setListOfMuseumFullInfobyName(List<MuseumsDTO> listOfMuseumFullInfobyName) {
        this.listOfMuseumFullInfobyName = listOfMuseumFullInfobyName;
    }
 public List<MuseumsDTO> getListOfMuseums() { 
        return listOfMuseums; 
    } 
    public void setListOfMuseums(List<MuseumsDTO> listOfMuseums) { 
        this.listOfMuseums = listOfMuseums; 
    } 
}
