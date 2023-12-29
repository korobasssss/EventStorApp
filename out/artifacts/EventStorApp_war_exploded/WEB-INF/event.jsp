<%--
  Created by IntelliJ IDEA.
  User: Administator
  Date: 29.12.2023
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event</title>
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
            padding: 50px;
            width: 1000px;
            height: 800px;
            background-color: #fff;
            border-radius: 20px;

            display: grid;
            grid-template-rows: 100px auto;

        }

        .main header {
            border-bottom: 1px solid #3E0A67;

            display: grid;
            justify-items: center;
            align-items: center;
            font-size: 1.5em;

            grid-template-rows: 15% auto;
        }

        .main header h1 {
            margin: 0;
        }

        .main header section {
            width: 20%;
            font-size: 0.7em;

            display: grid;
            grid-template-columns: 60% 30%;
            column-gap: 15px;
            justify-self: end;
        }

        .main header section button {
            padding: 0;
            border: none;
            background-color: transparent;
        }

        .main header section button a {
            color: #3E0A67;
            text-decoration: none;
        }

        .section {
            display: grid;
            grid-template-columns: 30% 65%;
            column-gap: 5%;
        }

        .new_task {
            padding: 20px;
            border-right: 1px solid #3E0A67;
        }

        .new_task form {
            height: 100px;
            display: grid;
            grid-template-rows: 25% auto 30%;
            row-gap: 5%;
        }

        .new_task form input {
            border: none;
            border-radius: 5px;
            background-color: #EBE9E9;

            padding: 3%;
        }
        .new_task form input:focus {
            outline: none;
        }

        .new_task form button {
            border: 1px solid #5D2B84;
            background-color: transparent;
            color: #5D2B84;
            font-size: 0.9em;

            border-radius: 5px;
        }
        .new_task form button:focus {
            outline: none;
        }

        ul {
            width: 80%;

            display: grid;
            justify-self: center;
            row-gap: 30px;
            padding: 0;
            list-style-type: none;
        }
    </style>
</head>
<body>
<section class="root">
    <main class="main">
        <header>
            <section>
                <button><a href="../index.jsp">Удалить аккаунт</a></button>
                <button><strong><a href="/">ВЫЙТИ</a></strong></button>
            </section>
            <h1>Задачи</h1>
        </header>
        <section class="section">
            <section class="new_task">
                <form>
                    <label>Создать новую задачу</label>
                    <input type="text" placeholder="Напиши сюда задачу...">
                    <button type="submit">Создать</button>
                </form>
            </section>
            <section class="todolist">
                <ul>
                    <li>
                        <label><input type="checkbox" name="task">grGVRAGGRGga</label>
                    </li>
                    <li>
                        <label><input type="checkbox">grGVRAGGRGga</label>
                    </li>
                    <li>
                        <label><input type="checkbox" name="task">grGVRAGGRGga</label>
                    </li>
                    <li>
                        <label><input type="checkbox">grGVRAGGRGga</label>
                    </li>
                    <li>
                        <label><input type="checkbox" name="task">grGVRAGGRGga</label>
                    </li>
                    <li>
                        <label><input type="checkbox">grGVRAGGRGga</label>
                    </li>
                </ul>
            </section>
        </section>
    </main>
</section>
</body>
</html>
