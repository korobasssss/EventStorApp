<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Event Store</title>
    <style>
        body {
            margin: 0;
            font-size: 1.2em
        }

        .root {
            width: 100vw;
            height: 100vh;
            background-color: #1E1E1E;

            display: grid;
            align-items: center;
            justify-items: center;
        }

        .main {
            width: 600px;
            height: 400px;
            background-color: #fff;
            border-radius: 20px;

        }

        .form {
            margin: 5%;
            height: 85%;
            display: grid;
            grid-template-rows: 20% 40% 10% 20%;
            row-gap: 3%;
        }

        h1 {
            display: grid;
            align-self: center;
            justify-self: center;

        }

        .input_section {
            display: grid;
            grid-template-rows: repeat(2, 1fr);
            row-gap: 10%;
        }

        .input_section input {
            border: none;
            border-radius: 15px;
            background-color: #EBE9E9;
            font-size: 1.2rem;

            padding: 3%;
        }


        .input_section input:focus {
            outline: none;
        }

        .register {
            display: grid;
            align-self: center;
            justify-self: center;
            grid-template-columns: repeat(2, 1fr);
            width: 53%;

            font-size: 0.9em;
            text-align: center;
        }

        .form a{
            color: #3E0A67;
            text-decoration: none;
        }

        .form button {
            border: none;
            background-color: #5D2B84;
            color: #fff;

            border-radius: 10px;
            font-size: 1.5em;
        }

        .form button a{
            color: #fff;
            text-decoration: none;
        }

        .form button:focus {
            outline: none;
        }
    </style>
</head>
<body>
<section class="root">
    <main class="main">
        <form action="event" method="post" class="form" name="form">
            <h1>Войдите в аккаунт</h1>
            <section class="input_section">
                <input name="login" placeholder="Логин">
                <input name="password" placeholder="Пароль">
            </section>
            <section class="register">
                Ещё нет аккаунта?
                <a href="WEB-INF/register.jsp">Зарегистрироваться</a>
            </section>
            <button type="submit">
                <a>Войти</a>
            </button>
        </form>
    </main>
</section>
</body>
</html>
