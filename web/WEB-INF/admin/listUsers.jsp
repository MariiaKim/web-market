


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список заказчиков</title>
  </head>
  <body>
    <h1>Список заказчиков:</h1>
        <ol>
          <c:forEach var="user" items="${listUsers}" varStatus="status">
                <li>
                    ${user.firstname}. ${user.lastname}. ${user.phone}
                </li>
          </c:forEach>
        </ol>
    
  </body>
</html>
