<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    List<String> pList = (List<String>) request.getAttribute("pList");
    List<String> nList = (List<String>) request.getAttribute("nList");
    List<String> aList = (List<String>) request.getAttribute("aList");
    boolean showpaybt = (boolean) request.getAttribute("showpaybt");
    String message = (String) request.getAttribute("message");
    String eID = (String) request.getAttribute("eID");
    String dorsal = (String) request.getAttribute("dorsal");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Run 4 Fun</title>
    <link rel="stylesheet" type="text/css" href="../static/css/style.css" />>
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
        <h2>Minhas Inscrições</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Nome do Evento</th>
                    <th>Dorsal</th>
                    <th>Nome</th>
                    <th>Género</th>
                    <th>Escalão</th>
                    <th>Pagamento</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty page}">
                        <tr>
                            <td colspan="6" align="center">Sem Inscrições</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${pList}" varStatus="loop">
                            <tr>
                                <td>${nList[loop.index]}</td>
                                <td>${p.getDorsal()}</td>
                                <td>${p.getNomeAtleta()}</td>
                                <td>${p.getGenero()}</td>
                                <td>${p.getEscalao()}</td>
                                <td>
                                    <c:if test="${p.paystatus}">
                                        Confirmado
                                    </c:if>
                                    <c:if test="${not p.paystatus}">
                                        <a href="${pageContext.request.contextPath}/register?idEvento=${p.geteID()}&amount=${aList[loop.index]}&EventoDorsal=${p.getDorsal()}">
                                            <button class="btn btn-primary">Pagar</button>
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <c:if test="${not empty message}">
            <div class="msg">${message}</div>
            <c:if test="${showpaybt}">
                <a href="${pageContext.request.contextPath}/register?idEvento=${p.geteID()}&EventoDorsal=${p.getDorsal()}">
                    <button class="btn btn-success">Efetuar Pagamento</button>
                </a>
            </c:if>
        </c:if>
    </main>
    <footer>
    </footer>
</body>

</html>
