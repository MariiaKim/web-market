

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список товара</title>
  </head>
  <body>
    <h1>Возврат товара:</h1>
    <form action="returnProduct" method="POST">
        <select name="historyId">
          <option value="">Выберите возвращаемый товар</option>
              <c:forEach var="deal" items="${listByProduct}" varStatus="status">
                  <option value="${deal.id}">Смартфон "${deal.product.name}" заказал ${deal.user.firstname} ${deal.user.lastname}</option>
              </c:forEach>
        </select>
      <br><br>
      <input type="submit" value="Вернуть товар">
    </form>
  </body>
</html>
