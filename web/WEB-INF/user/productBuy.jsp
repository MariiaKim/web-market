

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entities.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Selling product</title>
    </head>
    <body>
        <h1>Купить продукт</h1>
        <p>${info}</p>
        <div>
            <form action="productBuyAction" method="POST">
                <div>
                    <label>Название</label>  
                    <select name="productId">
                        <c:forEach var="product" items="${productList}" varStatus = "status">
                        <option value=${product.id}>${product.name} - ${product.price}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label>Количество</label>  
                    <input type="number" name="quantity"/>
                </div>
                <div>
                    <input type="submit" value="Купить">
                </div>
            </form>
        </div>
        <a href="index.jsp">На главную</a>
    </body>
</html>
