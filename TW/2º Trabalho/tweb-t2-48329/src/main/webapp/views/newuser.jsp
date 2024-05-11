<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Run 4 Fun</title>
    <link rel="stylesheet" type="text/css" href="../static/css/style.css" />
</head>

<body>
    <header>
        <a class="logo" href="${pageContext.request.contextPath}/index">
            <img src="../static/images/logo.png" alt="logo" style="width: 50px; height: auto;">
        </a>
        <nav>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/index">Home</a>
                    <a href="${pageContext.request.contextPath}/login">Login</a>
                    <c:if test="${pageContext.request.userPrincipal!=null}">
                        <c:if test="${isAdmin}">
                            <a test="${isAdmin}" href="${pageContext.request.contextPath}/admin">Página de Admin</a>
                        </c:if>
                    </c:if>
                </li>
            </ul>
        </nav>
    </header>
    <main>
        <div id="input-box">
            <h1>${title}</h1>
            <h2>${message}</h2>

            <form class="form" id="form-input" method="POST" action="/register">
                <div class="user-input">
                    <label for="username">Username:</label>
                    <input type="text" name="username" placeholder="Username"><br>

                    <label for="password">Password:</label>
                    <input type="password" name="password" placeholder="Password"><br>

                    <label for="email1">Email:</label>
                    <input type="text" name="email1" placeholder="Email"><br>

                    <label for="email2">Confirm Email:</label>
                    <input type="text" name="email2" placeholder="Confirm Email"><br>

                    <span style="color: red;">${error}</span>

                    <input type="submit" value="Registrar"><br>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </div>
            </form>
        </div>
    </main>
    <footer>
    </footer>
</body>

</html>
