

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <div>Добавить заказчика</div>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Home</a><br>
    <form action="registration" method="POST">
        Имя: <input type="text" name="firstname" value="${firstname}"><br>
        Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
        Телефон: <input type="text" name="phone" value="${phone}"><br>
        Логин: <input type="text" name="login" value="${login}"><br>
        Пароль: <input type="password" name="password" value="${password}"><br>
        <input type="submit" name="submit" value="Добавить читателя">
    </form>
  