<%--
  Created by IntelliJ IDEA.
  User: Administator
  Date: 29.12.2023
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section class="root">
    <main class="main">
        <form action="registration" method="post" class="form" name="form">
            <h1>Регистрация</h1>
            <section class="input_section">
                <input name="name" placeholder="Имя">
                <input name="login" placeholder="Логин">
                <input name="password" type="password" placeholder="Пароль">
            </section>
            <button type="submit">
                <a href="../index.jsp">Зарегистрироваться</a>
            </button>
        </form>
    </main>
</section>
</body>
</html>
