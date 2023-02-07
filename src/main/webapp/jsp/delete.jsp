<%@ page import="personDao.bean.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container" style="margin-left:10px; width:400px;">
    <h3 class="title w3-text-black">
        <span>Delete user</span>
    </h3>
    <form action="delete" method="post">
        <input class="w3-input w3-border" required name="Id" placeholder="Enter Id">
        <button class="w3-button w3-round btn-block w3-green" type="submit">Submit</button>
    </form>
    <div>
        <button class="w3-btn w3-light-blue w3-round-large" onclick="location.href='/Person_war_exploded/users'">Users</button>
    </div>
</div>
</body>
</html>
