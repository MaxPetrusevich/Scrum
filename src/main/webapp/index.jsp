<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <form action="main" method="get">
            <input type="hidden" required name="command" value="Add">
            <button class="w3-btn w3-green w3-round-large"
                    type="submit">
                Добавить пользователя
            </button>
        </form>
        <form action="main" method="get">
            <input type="hidden" required name="command" value="Select">
            <button class="w3-btn w3-light-blue w3-round-large"
                    type="submit">
                Список пользователей
            </button>
        </form>
    </div>
</div>
</body>
</html>