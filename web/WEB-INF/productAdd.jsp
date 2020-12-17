<%-- 
    Document   : productAdd
    Created on : 17.12.2020, 14:46:11
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Добавить продуктов</h1>
        <p>${info}</p>
        <div>
            
                <form action="productAddAction" method="POST">
                    <div>
                        <label>Название</label>  
                        <input type="text" name="name"/> 
                    </div>
                    <div>
                        <label>Цена</label>  
                        <input type="number" name="price"/>
                    </div>
                    <div>
                        <label>Количество</label>  
                        <input type="number" name="quantity"/>
                    </div>
                    <div> 
                        <input type="submit" value="Добавить">
                    </div>
                </form>
            
        </div>
        <a href="index.jsp">На главную</a>
    </body>
</html>
