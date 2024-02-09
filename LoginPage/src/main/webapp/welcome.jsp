<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            background: url("images/4.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            overflow: hidden;
            margin: 0;
            padding: 0;
            font-family: "Poppins", sans-serif;
        }

        .welcome-container {
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: #fff;
        }

        h1 {
            font-size: 3em;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.2em;
            margin-bottom: 30px;
        }

        .emoji {
            font-size: 2em;
        }

        .logout-btn {
            background-color: #fff;
            color: #000;
            padding: 10px 20px;
            font-size: 1em;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1>Welcome!</h1>
        <p>You have successfully done your web technology assignment one. 🎉<br>Go and have a sip... ☕</p>
        <a href="StudentRegistration.jsp" class="logout-btn">Log Out</a>
    </div>
</body>
</html>
