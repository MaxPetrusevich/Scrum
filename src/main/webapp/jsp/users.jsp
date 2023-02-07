<%@ page import="java.util.Set" %>
<%@ page import="personDao.bean.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.PersonDto" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<div style="margin-left:10px; margin-top:5px;">
    <div class="w3-container">
        <h2 class="title w3-text-black">Users</h2>
        <% List<PersonDto> users = (List<PersonDto>) request.getAttribute("users"); %>
        <%    if (users != null && !users.isEmpty()) { %>
        <table class="w3-table-all w3-hoverable">
            <tr><th>Id</th><th>First name</th><th>Last name</th><th></th></tr>
                <%  for (PersonDto user : users) { %>
        <tr><td><%=user.getId()%></td><td><%= user.getName() %></td><td><%= user.getSurname() %></td>
            <td><div class="w3-dropdown-hover">
            <button class="w3-button w3-tiny w3-padding-small">Edit</button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-tiny">
                <a href="/Person_war_exploded/update" class="w3-bar-item w3-button">Update</a>
                <a href="/Person_war_exploded/delete" class="w3-bar-item w3-button">Delete</a>
            </div>
        </div></td></tr>
        <%  } %>
        <%    } else out.println("<p>There are no users yet!</p>"); %>
        </table>
        <br>
        <div>
            <button class="w3-btn w3-green w3-round-large" onclick="location.href='/Person_war_exploded/add'">Add User</button>
        </div>
        <div>
            <br>
            <button class="w3-btn w3-red w3-round-large" onclick="location.href='/Person_war_exploded/delete'">Delete User</button>
        </div>
    </div>
</div>
</body>
</html>
