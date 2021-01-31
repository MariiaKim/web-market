    <%-- 
    Document   : index
    Created on : Nov 27, 2020, 9:14:26 AM
    Author     : pupil
--%>
<%@page import="entities.User"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JKTVR19_WEBMarket</title>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
    </head>
    <body>
        <h1>Магазин</h1>
        <%
            if(request.getSession().getAttribute("user") != null){
                User user = (User)request.getSession().getAttribute("user");
                request.setAttribute("name", user.getLogin());
                %>
                <p>Вы вошли как ${name}</p>
                <%
            }else{
                %>
                <p>Вы не выполнили вход</p>   
                <%
            }
        %>
        <br>
        <ul>
            <li><a href="login">Вход</a> / <a href="logout">Выход</a></li>
            <li><a href="registration">Регистрация</a></li>
            <li><a href="productList">Список продуктов</a></li>
            <li><a href="productBuy">Купить</a></li>
            <li><a href="productChange">Изменить</a></li>
            <li><a href="productAdd">Добавить</a></li>
        </ul>
</html>

    
