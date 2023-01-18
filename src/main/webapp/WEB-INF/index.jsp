<%--
  Created by IntelliJ IDEA.
  User: 20190433
  Date: 18/01/2023
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {border: 3px solid #f1f1f1;}

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .container {
            padding: 16px;
        }



    </style>
</head>
<body>

<h2>Login Form</h2>

<form action="${pageContext.request.contextPath}/action_page.php" method="post">

    <div class="container">
        <label for="loginField"><b>Login :</b></label>
        <input type="text" placeholder="Enter Username" id="loginField" name ="loginField" required>

        <label for="pwdField"><b>Password :</b></label>
        <input type="password" placeholder="Enter Password" id="pwdField" name="pwdField" required>

        <button type="submit">Submit</button>
    </div>
</form>

</body>
</html>
