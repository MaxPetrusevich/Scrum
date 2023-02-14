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
            <input type="hidden" required name="command" value="LoginAuto">
            <button class="w3-btn w3-green w3-round-large"
                    type="submit">
                Авторизация
            </button>
        </form>
        <form action="main" method="get">
            <input type="hidden" required name="command" value="LoginReg">
            <button class="w3-btn w3-light-blue w3-round-large"
                    type="submit">
                Регистрация
            </button>
        </form>
    </div>
</div>
</body>
</html>