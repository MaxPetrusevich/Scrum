<%@ page import="personDao.bean.Person" %>
<%@ page import="dto.PersonDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container" style="margin-left:10px; width:400px;">
    <h3 class="title w3-text-black">
        <span>Обновить данные пользователя</span>
    </h3>
    <form action="main" method="post">
        <input type = "hidden" required name = "userId" value= ${requestScope.userId}>
        <br>
        <input class="w3-input w3-border" required name="firstName"  value="${requestScope.firstName}">
        <br>
        <input class="w3-input w3-border" required name="lastName" value="${requestScope.lastName}">
        <br>
        <input type="hidden" required name = "command" value = "Update">
        <button class="w3-button w3-round btn-block w3-green" type="submit" >Изменить</button>
    </form>
    <form action="main" method = "get">
        <input type = "hidden" required name = "command" value="Select">
        <button class="w3-btn w3-light-blue w3-round-large" >Список пользователей</button>
    </form>
</div>
</body>
</html>