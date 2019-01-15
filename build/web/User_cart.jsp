
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="ru.eleavd.dto.DataTransferObject" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Моя записная книжка</h1>
        <form formaction="UserEventCartServlet" formmethod="post"  method="POST">
            <c:if test="${not empty dto.listOfCartUserEvent}">
                <a var="user" items="${dto.listOfCartUserEvent}">
                    ${user.cart_user_login}</a>
                <c:forEach var="user_cart" items="${dto.listOfCartUserEvent}">
              <li><a href="http://localhost:8080/HolaMuseumsGo/About_Event?event=${user_cart.cart_title_event}"  target="_parent">${user_cart.cart_title_event}</a></li>   
                 ${user_cart.cart_data_begin} <br> 
                     ${user_cart.cart_data_last} <br> 
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
 <form>
<input type="button" value="Назад" onClick='location.href="http://localhost:8080/HolaMuseumsGo/Events.jsp"'>
</form>
        
        
    </body>
</html>
