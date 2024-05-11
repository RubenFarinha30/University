<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                    <c:if test="${pageContext.request.userPrincipal== null}">
                        <a href="${pageContext.request.contextPath}/newuser">Register</a>
                    </c:if>
                    <c:if test="${pageContext.request.userPrincipal!=null}">
                        <a href="${pageContext.request.contextPath}/logout">Log Out</a>
                        <c:if test="${isAdmin}">
                            <a href="${pageContext.request.contextPath}/admin">Página de Admin</a>
                        </c:if>
                    </c:if>
                </li>
            </ul>
        </nav>
    </header>

    <div id="input-box">
        <h2>Login</h2>

        <div class="error">${error}</div>
        <div class="msg">${msg}</div>

        <form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
            <div class="form-input">
                <label for="username">Nome de Utilizador</label>
                <input id="username" type='text' name='username' placeholder='Nome de Utilizador'>

                <label for="password">Palavra-passe</label>
                <input id="password" type='password' name='password' placeholder="Palavra-passe"/>

                <input name="submit" type="submit" value="Login" />
                <p class="click">Se ainda não tem uma conta e gostava de criar,
                    <a href="<c:url value='/newuser'/>">por favor clique aqui</a>
                </p>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
</body>
</html>
