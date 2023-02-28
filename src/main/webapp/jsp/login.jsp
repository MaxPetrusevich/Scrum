<%--
  Created by IntelliJ IDEA.
  User: lomic
  Date: 13.02.2023
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class = "regular-progression">
<head>
    <title>Log-In</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="jsp/style.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<c:choose>
<c:when test="${requestScope.get('action') == 'Reg'}">
<div class="w3-container" style="margin-left:10px; width:400px;">
    <form action="main" method="post" >
        <input type="hidden" required name="command" value="LoginReg">
        <input class="w3-input w3-border" style="margin-top:40px; height: 40px; width: 400px;"  required name="Name" placeholder="Введите имя">
        <input class="w3-input w3-border"  style="margin-top:40px; height: 40px; width: 400px;"required name="Email" placeholder="Введите E-Mail">
        <input class="w3-input w3-border"  type="password" style="margin-top:40px; height: 40px; width: 400px;"required name="Password" placeholder="Веедите пароль">
        <button class="w3-button w3-round btn-block w3-green" type="submit">Регистрация</button>
    </form>
    </c:when>
    <c:otherwise>
        <form action="main" method="post">
            <input type="hidden" required name="command" value="LoginAuto">
            <input class="w3-input w3-border"  style="margin-top:40px; height: 40px; width: 400px;"  required name="Email" placeholder="Введите E-Mail">
            <input class="w3-input w3-border" type="password" style="margin-top:40px; height: 40px; width: 400px;"  required name="Password" placeholder="Веедите пароль">
            <button class="w3-button w3-round btn-block w3-green" type="submit">Войти</button>
        </form>
    </c:otherwise>
    </c:choose>
    <button class="w3-btn w3-light-blue w3-round-large" onclick='location.href="index.jsp"'>
        Вернуться на главную
    </button>
</div>
</body>
</html>
