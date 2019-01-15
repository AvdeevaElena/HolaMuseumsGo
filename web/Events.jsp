<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="ru.eleavd.dto.DataTransferObject" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Подробности о мероприятии</h1>
    <form action="Events" method="POST">
        <c:if test="${not empty dto.listOfEventsInfo}">
        <ul type="I">
            <c:forEach var="museum_ev" items="${dto.listOfEventsInfo}">
                 <li>${museum_ev.title_event_ev}<br>  </li> 
                    ${museum_ev.name_ev}<br>    
                    ${museum_ev.data_begin_ev}<br>
                    ${museum_ev.data_last_ev}<br>
                    ${museum_ev.type_ev}<br>
                    ${museum_ev.info_ev}<br>
                    ${museum_ev.time_begin_ev}<br>
                    ${museum_ev.price_ev}<br>
                   
                    
            </c:forEach>
                </ul>  
    </c:if>
         <c:if test="${not empty error}">
                     <li>${error}</li>
                <c:remove scope="session" var="error"/>
        </c:if>       
    <br>
<br>   
     <br>
<br>  
<input type="submit" value="Я пойду!" formaction="RecordtoEventServlet" formmethod="post"/>
<table>
<tr>
<td>Логин:</td>
<td><input type="text" name="login"></td>
</tr>
<tr>
<td>Пароль:</td>
<td><input type="password" name="password"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Войти" name="submit" formaction="AuthorizationServlet" formmethod="post"> </td>
</tr>
</table>
 <input type="submit" value="Рассылка почты" formaction="DailyMailServlet" formmethod="post"/>
    <a href="http://localhost:8080/HolaMuseumsGo/Registration.jsp">Регистрация</a> 
                        
    <br>
<br> 
<form formaction="UserEventCartServlet" formmethod="post"  method="POST">
<input type="submit" value="Мои события" formaction="UserEventCartServlet" formmethod="post"/>
</form>
 <br>
<br>
 <form>
<input type="button" value="Назад" onClick='location.href="http://localhost:8080/HolaMuseumsGo/StartMuseums.jsp"'>
</form>  

<br>
<br>  
<c:if test="${not empty dto.listOfDailyMail}">
            <c:forEach var="user_mail" items="${dto.listOfDailyMail}">
                  ${user_mail.login_m}</li>
                  ${user_mail.email_m}</li>
                  ${user_mail.data_begin_m}</li>
                  ${user_mail.data_report}</li>
            </c:forEach>               
                                          
    </c:if>                             
    <c:if test="${not empty error}">
                        <li>${error}</li>
                        <c:remove scope="session" var="error"/>
    </c:if>                          
    <br>
<br>   
     <br>
<br>                        
 </form> 
    <br>
<br>   
     <br>
<br>        <form formaction="RecordtoEventServlet" formmethod="post"  method="POST">
    <c:if test="${not empty dto.listOfUserRecordEvent}">
            <c:forEach var="us_record" items="${dto.listOfUserRecordEvent}">
                    ${us_record.record_status}   
            </c:forEach>               
                                          
    </c:if>                             
    <c:if test="${not empty error}">
                        <li>${error}</li>
                        <c:remove scope="session" var="error"/>
    </c:if>                                          
  </form>  

    <br>
<br>   
     <br>
<br>  
 </body>
</html>
