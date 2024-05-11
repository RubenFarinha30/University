<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <c:if test="${pageContext.request.userPrincipal!=null}">
                        <a href="${pageContext.request.contextPath}/logout">Log Out</a>
                    </c:if>
                </li>
            </ul>
        </nav>
    </header>

    <main>
        <div id="input-box">
            <h2>Inscrição no evento: ${event}</h2>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
            <form class="form" action="/registerAtleta" method="get">
                <input type="hidden" name="Evento" value="${Evento}">
                <input type="hidden" name='username' value='${username}'>
                
                <label for="nome">Nome:</label>
                <input class="input-text" type='text' name='nome' value='' required>

                <label for="nif">NIF:</label>
                <input class="input-text" type='text' name='nif' value='' required>

                <p class="input-label">Género</p>
                <div class="radio-group">
                    <div class="radio">
                        <input type="radio" checked name="genero" value="m">
                        <label class="radio-label">Masculino</label>
                    </div>
                    <div class="radio">
                        <input type="radio" name="genero" value="f">
                        <label class="radio-label">Feminino</label>
                    </div>
                </div>

                <p class="input-label">Escalão</p>
                <div class="radio-group">
                    <c:forEach var="escalao" items="${escaloes}">
                        <div class="radio">
                            <input type="radio" name="escalao" value="${escalao.value}" ${escalao.checked}>
                            <label class="radio-label">${escalao.label}</label>
                        </div>
                    </c:forEach>
                </div>

                <input type="submit" value="inscrever"><br>
            </form>
        </div>
    </main>

</body>

</html>
