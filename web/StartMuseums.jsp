<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="ru.eleavd.dto.DataTransferObject" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="Style/style.css"
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <form name="firstform" action="StartServlet" method="POST">   
 <br>          <br>    
            <h1 id="topeventstext">Популярные события </h1>   
     <c:if test="${not empty dto.listOfTOPTitle}">
        <ul id="listtop" type="I">
            <c:forEach  var="museum_top" items="${dto.listOfTOPTitle}">     
<li>
 <a href="http://localhost:8080/HolaMuseumsGo/events?event=${museum_top.id_top}"
       arget="_parent">${museum_top.title_event_top}</a></li>
            </c:forEach>
                </ul>  
    </c:if>
         <c:if test="${not empty error}">
                     <li>${error}</li>
                <c:remove scope="session" var="error"/>
    </c:if>         
    <br>      
   <br>
 <h1 id="hmuseum">Музеи</h1> 
<table title="СПИСОК ВСЕХ МУЗЕЕВ" border="1" width="1" cellspacing="1" 
       cellpadding="1" >
   <tr>
    <td>
     <select id ="listmus" multiple size="15" name="museams[]">
      <c:forEach var="museum" items="${dto.listOfMuseumsNames}">
        <option>${museum.name}</option> 
     </c:forEach>
      </select>  
      </td>
  </tr>
 </table>             
    <h1 id="hevents">Виды Мероприятий </h1>    
    <select id="listevents" name="types[]"multiple="multiple" size="12">
        <c:forEach var="type" items="${dto.listOfEventsTypeTitle}">     
                            <option>${type.mus_type_event}</option>
                    </c:forEach>
    </select>
    <h1 id="htags">Тэги </h1>
    <select id="listtags" name="tags[]" multiple="multiple" size="15">
        <c:forEach var="tags" items="${dto.listOfTagsTitle}">     
                            <option>${tags.mus_tag}</option>
                  </c:forEach>
    </select>
    <h1 id="halltogether"> 
      <br>               Не более трех тэгов.
      <br>               Kомбинация из тэгов обобщенно харктеризует событие.  
      <br>               Примеры: 
      <br>                     взрослые И картины 
      <br>                     взрослые И картины И современное искусство 
    </h1> 
            
      <h1 id="hfragmentarno"> 
      <br>                Не более трех тэгов.
      <br>                Комбинация из тэгов фрагментарно харктеризует событие.  
      <br>                Примеры: 
      <br>                взрослые ИЛИ картины 
      <br>                взрослые ИЛИ картины ИЛИ современное искусство 
           </h1>             
  <input id="binfomus" type="submit" value="Информация о музее" name="Отправить1" formaction="DemonstrarMusInfobyNameServlet" formmethod="post" />
  <input id="bmusevents" type="submit" value="События в музеи" name="Отправить2" formaction="DemonstrarMusEventsbyNameServlet" formmethod="post" />       
  <input id="balltogether" type="submit" value="По тэгам обобщенно" name="Отправить3" formaction="DemonstrarEvemtsNameMusbyTagServlet" formmethod="post" />                                                                   
  <input id="bfragmentarno"  type="submit" value="По тэгам фрагментарно" name="bfragmentarno"  formaction="DemonstrarEvemtsNameMusbyTagOrServlet" formmethod="post" />     
  <input id="bmuscityevents" type="submit" value="События в музеях" name="Отправить4" formaction="DemonstrarEvemtsNameMusbyTypeServlet" formmethod="post" />    
  
  </form>      
    <br>      
           <br>       
<form action="DemonstrarMusEventsbyNameServlet" method="POST">
    <c:if test="${not empty dto.listOfMuseumFullInfobyName}">
        <ul id="demomusevents" type="I">
            <c:forEach var="museum" items="${dto.listOfMuseumFullEventsbyName}">
                    <li><a href="http://localhost:8080/HolaMuseumsGo/events?event=${museum.id_event}" target="_parent">${museum.mus_event}</a><li>
                    ${museum.name}<br>
                    ${museum.type_event}<br>
         Начало:    ${museum.data_begin}<br>
      Окончание:    ${museum.data_last}<br>
                    
            </c:forEach>
                </ul>  
    </c:if>
         <c:if test="${not empty error}">
                     <li>${error}</li>
                <c:remove scope="session" var="error"/>
        </c:if>       
 </form>        
   <br>      
           <br>        
<form action="DemonstrarMusInfobyNameServlet" method="POST">  
     <c:if test="${not empty dto.listOfMuseumFullInfobyName}">
           <ul id="demoinfomus" type="1">
                 <c:forEach var="museum" items="${dto.listOfMuseumFullInfobyName}">
                    <li>${museum.name}</li>
                        ${museum.info_mus}<br>
                        <br>
              Адрес:    ${museum.address}<br>
                        <br>
       Режим работы:    ${museum.scheduller}<br>
                        <br>
  Стоимость билетов:    ${museum.info_tickets}<br>
                </c:forEach>
           </ul>
    </c:if>
    <c:if test="${not empty error}">
                        <li>${error}</li>
                        <c:remove scope="session" var="error"/>
    </c:if>   
</form>       
 <form id="demoand" action="DemonstrarEvemtsNameMusbyTagServlet" method="POST">
    <c:if test="${not empty dto.listOfEventMus_tag1}">
        <ul type="I">
            <c:forEach var="museum_t" items="${dto.listOfEventMus_tag1}">
                <li><a href="http://localhost:8080/HolaMuseumsGo/events?event=${museum_t.t1_id}" target="_parent">${museum_t.t1_event}</a></li> 
                    ${museum_t.t1_mus}  
            </c:forEach>                                                 
    </c:if>                               
    <c:if test="${not empty error}">
                        <li>${error}</li>
                        <c:remove scope="session" var="error"/>
    </c:if>  
 </form>          
 <form id="demoor" action="DemonstrarEvemtsNameMusbyTagOrServlet" method="POST">
      <c:if test="${not empty dto.listOfEventMus_tag2OR}">
        <ul type="I">
            <c:forEach var="museum_tor" items="${dto.listOfEventMus_tag2OR}">
                <li><a href="http://localhost:8080/HolaMuseumsGo/events?event=${museum_tor.t2_id}" target="_parent">${museum_tor.t2_event}</a></li> 
                    ${museum_tor.t2_mus}  
            </c:forEach>                                                    
    </c:if>                             
    <c:if test="${not empty error}">
                        <li>${error}</li>
                        <c:remove scope="session" var="error"/>
    </c:if>  
 </form> 
<br>      
   <br> 
 <form action="DemonstrarEvemtsNameMusbyTypeServlet" method="POST">
     <c:if test="${not empty dto.listOfMuseumFullEventsbyType}">
        <ul id="demotypeevents" type="I">
            <c:forEach var="museum" items="${dto.listOfMuseumFullEventsbyType}">
                    <li><a href="http://localhost:8080/HolaMuseumsGo/events?event=${museum.id_bt}"  target="_parent">${museum.title_event_bt}</a></li>
                    ${museum.name_bt}<br>
     Начало:        ${museum.data_begin_bt}<br>
     Окончание:     ${museum.data_last_bt}<br>
            </c:forEach>
                </ul>  
    </c:if>
         <c:if test="${not empty error}">
                     <li>${error}</li>
                <c:remove scope="session" var="error"/>
        </c:if>       
 </form>  
<br>      
   <br>  
 <form action="DemonstrarEvemtsTypeANDMusServlet" method="POST">
     <c:if test="${not empty dto.listOfMuseumFullEventsbyTypeANDMus}">
        <ul id="demotypeandmus" type="I">
            <c:forEach var="museum" items="${dto.listOfMuseumFullEventsbyTypeANDMus}">
                    <li><a href="http://localhost:8080/HolaMuseumsGo/events?event=${museum.id_mt}"  target="_parent">${museum.title_event_mt}</a></li>
                    ${museum.name_mt} &nbsp;
                    ${museum.type_mt}<br>
        Начало:     ${museum.data_begin_mt}<br>
        Окончание:  ${museum.data_last_mt}<br>
            </c:forEach>
                </ul>  
    </c:if>
         <c:if test="${not empty error}">
                     <li>${error}</li>
                <c:remove scope="session" var="error"/>
        </c:if>       
 </form>  
<br>      
   <br>  
 <form action="DemonstrarMusEventsbyDateServlet" method="POST">
     <c:if test="${not empty dto.listOfMuseumFullEventsbyDate}">
        <ul id="demodate" type="I">
            <c:forEach var="museum" items="${dto.listOfMuseumFullEventsbyDate}">
                    <li><a href="http://localhost:8080/HolaMuseumsGo/events?event=${museum.id_d}"  target="_parent">${museum.title_event_d}</a></li>
                    ${museum.name_d} &nbsp;
                    ${museum.type_d}<br>
                    ${museum.time_begin_d}<br>
                   
            </c:forEach>
                </ul>  
    </c:if>
         <c:if test="${not empty error}">
                     <li>${error}</li>
                <c:remove scope="session" var="error"/>
        </c:if> 
                     
 </form>       
  <br>      
     <br>  
   <form action="DemonstrarMusEventsbyDateServlet" method="POST">

    <h1 id="hdate"> Дата посещения</h1>  
   <p> <input id="calendar" type="date" name="calendar" value="2016-06-25"
              
    max="2016-12-31" min="2016-01-01">       
   <input id="bcalendar" type="submit" value="Календарь" name="ОтправитьK" formaction="DemonstrarMusEventsbyDateServlet" formmethod="post" />
    <br>      
            <br> 
   </p>            
</form>
    </body>
</html>
