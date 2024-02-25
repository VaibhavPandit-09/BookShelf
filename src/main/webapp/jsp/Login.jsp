<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        .container {
            max-width: 500px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type=text], input[type=email], input[type=password], .button {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }
        .button:hover {
            background-color: #45a049;
        }
        .register-button {
            background-color: #008CBA; /* Different color for register */
        }
        .register-button:hover {
            background-color: #005f6a;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Create User</h2>
    <form action="LoginUser" method="POST">
        <label for="username">Username/Email:</label>
        <input type="text" id="username" name="username" required>

        <label for="pwd">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" class="button" value="Login">
    </form>
    <!-- Register Button -->
    <a href="/bookshelf/register" class="button register-button">Register</a>
</div>

</body>
</html>
