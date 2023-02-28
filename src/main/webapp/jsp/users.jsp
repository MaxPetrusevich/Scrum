<%@ page import="java.util.Set" %>
<%@ page import="bean.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.PersonDto" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="regular-progression">
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="jsp/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body>

<div class="regular-progression">
    <div class="w3-container regular-progression">
        <c:choose>
            <c:when test="${requestScope.status == 'Auto'}">
                <h2 class="title w3-text-black" style="color:white;">Список пользователей</h2>
                <c:choose>
                    <c:when test="${requestScope.role == 'Admin'}">
                        <c:choose>
                            <c:when test="${requestScope.users.size() > 0}">
                                <div class="w3-bar regular-progression">
                                    <div style="display: inline-block">
                                        <span>Сортировать по: </span>
                                    </div>
                                    <div style="display: inline-block">
                                        <form action="main" method="post">

                                            <input type="hidden" required name="status" value="${requestScope.status}">
                                            <input type="hidden" required name="role" value="${requestScope.role}">
                                            <input type="hidden" name="recordsPerPage"
                                                   value="${requestScope.recordsPerPage}">
                                            <input type="hidden" name="currentPage" value="${requestScope.currentPage}">
                                            <input type="hidden" name="field" value="name">
                                            <button class="w3-button w3-circle w3-teal" type="submit" name="command"
                                                    value="order">имени
                                            </button>

                                        </form>
                                    </div>
                                    <div style="display: inline-block">
                                        <form action="main" method="post">

                                            <input type="hidden" required name="status" value="${requestScope.status}">
                                            <input type="hidden" required name="role" value="${requestScope.role}">
                                            <input type="hidden" name="recordsPerPage"
                                                   value="${requestScope.recordsPerPage}">
                                            <input type="hidden" name="currentPage" value="${requestScope.currentPage}">
                                            <input type="hidden" name="field" value="surname">
                                            <button class="w3-button w3-circle w3-teal" type="submit" name="command"
                                                    value="order">фамилии
                                            </button>

                                        </form>
                                    </div>
                                </div>
                                <div class="regular-progression">
                                    <table class="w3-table-all w3-hoverable">
                                        <tr class="regular-progression">
                                            <th class="regular-progression" style= "color: white">Id</th>
                                            <th class="regular-progression" style= "color: white">Имя</th>
                                            <th class="regular-progression" style= "color: white">Фамилия</th>
                                        </tr>
                                        <c:forEach var="user" items="${requestScope.users}" varStatus="status">
                                            <tr class="regular-progression">
                                                <td class="regular-progression" style="color: white">${status.count}</td>
                                                <td class="regular-progression" style="color: white">${user.name}</td>
                                                <td class="regular-progression" style="color: white">${user.surname}</td>

                                                <td class="regular-progression">
                                                    <form action="main" method="get">
                                                        <input type="hidden" required name="command" value="Update">
                                                        <input type="hidden" required name="userId" value= ${user.id}>
                                                        <input type="hidden" required name="firstName"
                                                               value= ${user.name}>

                                                        <input type="hidden" required name="status"
                                                               value="${requestScope.status}">
                                                        <input type="hidden" required name="role"
                                                               value="${requestScope.role}">
                                                        <input type="hidden" name="recordsPerPage"
                                                               value="${requestScope.recordsPerPage}">
                                                        <input type="hidden" name="currentPage"
                                                               value="${requestScope.currentPage}">
                                                        <input type="hidden" name="field"
                                                               value="${requestScope.field}">
                                                        <input type="hidden" required name="lastName"
                                                               value= ${user.surname}>
                                                        <button type="submit" class="w3-button w3-circle w3-teal">
                                                            Редактировать
                                                        </button>
                                                    </form>
                                                </td>
                                                <td class="regular-progression">
                                                    <form action="main" method="post">

                                                        <input type="hidden" required name="status"
                                                               value="${requestScope.status}">
                                                        <input type="hidden" required name="role"
                                                               value="${requestScope.role}">
                                                        <input type="hidden" name="recordsPerPage"
                                                               value="${requestScope.recordsPerPage}">
                                                        <input type="hidden" name="currentPage"
                                                               value="${requestScope.currentPage}">
                                                        <input type="hidden" name="field"
                                                               value="${requestScope.field}">
                                                        <input type="hidden" required name="userId" value= ${user.id}>
                                                        <input type="hidden" required name="command" value="Delete">
                                                        <button type="submit" class="w3-button w3-circle w3-teal">
                                                            Удалить
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                                <div class="w3-bar regular-progression">
                                    <div style="display: inline-block">
                                        <form action="main" method="post">
                                            <c:if test="${currentPage!=1}">
                                                <input type="hidden" required name="status"
                                                       value="${requestScope.status}">
                                                <input type="hidden" required name="role" value="${requestScope.role}">
                                                <input type="hidden" name="recordsPerPage" value=${recordsPerPage}>
                                                <input type="hidden" name="currentPage" value=${currentPage-1}>
                                                <input type="hidden" name="field" value="${requestScope.field}">
                                                <input type="hidden" name="command" value="Select">
                                                <button class="w3-btn w3-green w3-round-short" type="submit">Назад
                                                </button>

                                            </c:if>
                                        </form>
                                    </div>
                                    <div style="display: inline-block; color: white;">
                                        <c:forEach begin="1" end="${countPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">

                                                            ${i} <span class="sr-only" style="color: white">(current)</span>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </div>
                                    <div style="display: inline-block">

                                        <form action="main" method="post">
                                            <c:if test="${currentPage lt countPages}">
                                                <input type="hidden" required name="status"
                                                       value="${requestScope.status}">
                                                <input type="hidden" required name="role" value="${requestScope.role}">
                                                <input type="hidden" name="recordsPerPage" value=${recordsPerPage}>
                                                <input type="hidden" name="currentPage" value=${currentPage+1}>
                                                <input type="hidden" name="field" value="${requestScope.field}">
                                                <input type="hidden" name="command" value="Select">
                                                <button class="w3-btn w3-light-blue w3-round-large" type="submit">
                                                    Вперед
                                                </button>
                                            </c:if>
                                        </form>
                                    </div>

                                </div>

                            </c:when>
                            <c:otherwise>
                                <div class="emptyList">
                                    <h2 style="color: white;">Список пользователей пуст!</h2>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div style="align-content: center" class="regular-progression">
                            <form action="main" method="get">
                                <input type="hidden" required name="command" value="Add">

                                <input type="hidden" required name="status" value="${requestScope.status}">
                                <input type="hidden" required name="role" value="${requestScope.role}">
                                <input type="hidden" name="recordsPerPage" value=${requestScope.recordsPerPage}>
                                <input type="hidden" name="currentPage" value=${requestScope.currentPage}>
                                <input type="hidden" name="field" value="${requestScope.field}">
                                <button class="w3-btn w3-green w3-round-large">
                                    Добавить пользователя
                                </button>
                            </form>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <c:choose>
                            <c:when test="${requestScope.users.size() > 0}">
                                <div class="w3-bar regular-progression">
                                    <div style="display: inline-block">
                                        <span>Сортировать по: </span>
                                    </div>
                                    <div style="display: inline-block">
                                        <form action="main" method="post">

                                            <input type="hidden" required name="status" value="${requestScope.status}">
                                            <input type="hidden" required name="role" value="${requestScope.role}">
                                            <input type="hidden" name="recordsPerPage"
                                                   value="${requestScope.recordsPerPage}">
                                            <input type="hidden" name="currentPage" value="${requestScope.currentPage}">
                                            <input type="hidden" name="field" value="name">
                                            <button class="w3-button w3-circle w3-teal" type="submit" name="command"
                                                    value="order">имени
                                            </button>
                                        </form>
                                    </div>
                                    <div style="display: inline-block">
                                        <form action="main" method="post">
                                            <input type="hidden" required name="status" value="${requestScope.status}">
                                            <input type="hidden" required name="role" value="${requestScope.role}">
                                            <input type="hidden" name="recordsPerPage"
                                                   value="${requestScope.recordsPerPage}">
                                            <input type="hidden" name="currentPage" value="${requestScope.currentPage}">
                                            <input type="hidden" name="field" value="surname">
                                            <button class="w3-button w3-circle w3-teal" type="submit" name="command"
                                                    value="order">фамилии
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                <table class="w3-table-all w3-hoverable regular-progression">
                                    <tr class="regular-progression">
                                        <th class="regular-progression" style= "color: white">Id</th>
                                        <th class="regular-progression" style= "color: white">Имя</th>
                                        <th class="regular-progression" style= "color: white">Фамилия</th>
                                    </tr>
                                    <c:forEach var="user" items="${requestScope.users}" varStatus="status">
                                        <tr class="regular-progression">
                                            <td class="regular-progression" style="color: white">${status.count}</td>
                                            <td class="regular-progression" style="color: white">${user.name}</td>
                                            <td class="regular-progression" style="color: white">${user.surname}</td>

                                        </tr>
                                    </c:forEach>
                                </table>

                                <div class="w3-bar regular-progression">
                                    <div style="display: inline-block">
                                        <form action="main" method="post">
                                            <c:if test="${currentPage!=1}">
                                                <input type="hidden" required name="status"
                                                       value="${requestScope.status}">
                                                <input type="hidden" required name="role"
                                                       value="${requestScope.role}">
                                                <input type="hidden" name="recordsPerPage" value=${recordsPerPage}>
                                                <input type="hidden" name="currentPage" value=${currentPage-1}>
                                                <input type="hidden" name="field" value="${requestScope.field}">
                                                <input type="hidden" name="command" value="Select">
                                                <button class="w3-button w3-circle w3-teal" type="submit">Назад
                                                </button>


                                            </c:if>
                                        </form>
                                    </div>
                                    <div style="display: inline-block; color: white;">
                                        <c:forEach begin="1" end="${countPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">

                                                    ${i} <span class="sr-only" style="color: white">(current)</span>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </div>
                                    <div style="display: inline-block">
                                        <form action="main" method="post">
                                            <c:if test="${currentPage lt countPages}">
                                                <input type="hidden" required name="status"
                                                       value="${requestScope.status}">
                                                <input type="hidden" required name="role" value="${requestScope.role}">
                                                <input type="hidden" name="recordsPerPage" value=${recordsPerPage}>
                                                <input type="hidden" name="currentPage" value=${currentPage+1}>
                                                <input type="hidden" name="field" value="${requestScope.field}">
                                                <input type="hidden" name="command" value="Select">
                                                <button class="w3-button w3-circle w3-teal" type="submit">Вперед
                                                </button>
                                            </c:if>
                                        </form>
                                    </div>
                                </div>

                            </c:when>
                            <c:otherwise>
                                <div class="emptyList">
                                    <h2 style="color: white">Список пользователей пуст!</h2>
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
