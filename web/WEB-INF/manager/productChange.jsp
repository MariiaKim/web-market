<%-- 
    Document   : sell
    Created on : Dec 2, 2020, 9:21:35 AM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change product</title>
    </head>
    <body>
        <h1>Изменить продукт</h1>
        <p>${info}</p>
        <div>
            <form action="productChangeAction" method="POST">
                <div>
                    <label>Продукт</label>
                    <select name="productId">
                        <c:forEach var="product" items="${productList}" varStatus = "status">
                        <option value=${product.id}>${product.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label>Новое название</label>
                    <input type="text" name="name"/>
                </div>
                <div>
                    <label>Новая цена</label>
                    <input type="number" name="price"/>
                </div>
                <div>
                    <label>Новое количество</label>
                    <input type="number" name="quantity"/>
                </div>
                <div>
                    <input type="submit" value="Изменить">
                </div>
            </form>
        </div>
        <a href="index.jsp">На главную</a>
    </body>
</html>
