<%@ page import="java.util.Set" %>
<%@ page import="bean.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.PersonDto" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<div style="margin-left:10px; margin-top:5px;">
<div class="w3-container">
<c:choose>
    <c:when test="${requestScope.status == 'Auto'}">
        <h2 class="title w3-text-black">Список пользователей</h2>
        <c:choose>
            <c:when test="${requestScope.role == 'Admin'}">
                <c:choose>
                    <c:when test="${requestScope.users.size() > 0}">
                        <table class="w3-table-all w3-hoverable">
                            <tr>
                                <th>Id</th>
                                <th>Имя</th>
                                <th>Фамилия</th>
                            </tr>
                            <c:forEach var="user" items="${requestScope.users}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${user.name}</td>
                                    <td>${user.surname}</td>

                                    <td>
                                        <form action="main" method="get">
                                            <input type="hidden" required name="command" value="Update">
                                            <input type="hidden" required name="userId" value= ${user.id}>
                                            <input type="hidden" required name="firstName" value= ${user.name}>
                                            <input type="hidden" required name="lastName"
                                                   value= ${user.surname}>
                                            <button type="submit" class="w3-button w3-circle w3-teal">
                                                Редактировать
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="main" method="post">
                                            <input type="hidden" required name="userId" value= ${user.id}>
                                            <input type="hidden" required name="command" value="Delete">
                                            <button type="submit" class="w3-button w3-circle w3-teal">Удалить
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="emptyList">
                            <h2>Список пользователей пуст!</h2>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div style="align-content: center">
                    <form action="main" method="get">
                        <input type="hidden" required name="command" value="Add">
                        <button class="w3-btn w3-green w3-round-large">
                            Добавить пользователя
                        </button>
                    </form>
                </div>
            </c:when>

            <c:otherwise>
                <c:choose>
                    <c:when test="${requestScope.users.size() > 0}">
                        <table class="w3-table-all w3-hoverable">
                            <tr>
                                <th>Id</th>
                                <th>Имя</th>
                                <th>Фамилия</th>
                            </tr>
                            <c:forEach var="user" items="${requestScope.users}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${user.name}</td>
                                    <td>${user.surname}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="emptyList">
                            <h2>Список пользователей пуст!</h2>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>

        </c:choose>

        <button class="w3-btn w3-light-blue w3-round-large" onclick='location.href="index.jsp"'>
        Выйти
        </button>
        <br>
    </c:when>
    <c:otherwise>
        <h2>Неверный логин или пароль</h2>
        <button class="w3-btn w3-light-blue w3-round-large" onclick='location.href="index.jsp"'>
            Вернуться на главную
        </button>
    </c:otherwise>
</c:choose>
        </div>
        </div>
        </body>
        </html>
