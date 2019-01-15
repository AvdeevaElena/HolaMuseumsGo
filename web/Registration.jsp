<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="ru.eleavd.dto.DataTransferObject" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Регистрация</h1>   
<form  method="POST">
<table>
<tr>   
<td>Логин<font color="red">*</font>:</td>
<td><input type="text" size="20" name="login"></td>
</tr>
<tr>
<td>Пароль<font color="red">*</font>:</td>
<td><input type="password" size="20" maxlength="20" name="password"></td>
</tr>
<tr>
<td>Подтверждения пароля<font color="red">*</font>:</td>
<td><input type="password" size="20" maxlength="20" name="password2"></td>
</tr>
<tr>
<td>E-mail<font color="red">*</font>:</td>
<td><input type="text" size="20" name="email"></td>
</tr>
<td>Кол-во дней<font color="red">**</font>:</td>
<td><input type="text" size="20" name="day_before"></td>
</tr>
<tr>
<td></td>
<td colspan="2"><input type="submit" value="Зарегистроваться" name="submit" formaction="RegistrationServlet" formmethod="post">
</td>
</tr>
</table>
</form>
 <br>Поля со значком <font color="red">*</font> обязательны для заполнения       
 <br>Поля со значком <font color="red">**</font> не обязательны для заполнения,
 укажите за сколько дней вы бы хотели получать сообщение на эл. почту о начале мероприятия
 <form action="RegistrationServlet" method="POST">
 <br>
 <form>
<input type="button" value="Назад" onClick='location.href="http://localhost:8080/HolaMuseumsGo/Events.jsp"'>
</form>  

<br>
<br>    
     
 <c:if test="${not empty dto.listOfUserFirstReg}">
            <c:forEach var="user_regfirst" items="${dto.listOfUserFirstReg}">
                    ${user_regfirst.reg_email}  <br>
                    ${user_regfirst.reg_login} 
            </c:forEach>                                                  
    </c:if>                               
    <c:if test="${not empty error}">
                        <li>${error}</li>
                        <c:remove scope="session" var="error"/>
    </c:if>  
                        
                     
                        
 </form>  
   </body>  
</html>
