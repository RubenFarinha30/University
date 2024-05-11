<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Run 4 Fun</title>
		<link rel="stylesheet" type="text/css" href="../static/css/style.css" />
		<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
		<script>
			$(document).ready(function() {
					function filterTable() {
					var nameFilter = $('#nameFilter').val(); 
					var escalaoFilter = $('#escalaoFilter').val();

					$('#runnersTable tbody tr').each(function () {
						var name = $(this).find('td:nth-child(1)').text();
						var escalao = $(this).find('td:nth-child(4)').text();

						if (name.includes(nameFilter) && escalao.includes(escalaoFilter)) {
							$(this).show();
						} else {
							$(this).hide();
						}
					});
				}
				$('#nameFilter').on('input', filterTable);
				$('#escalaoFilter').on('input', filterTable);
			});
		</script>
	</head>

	<body>
		<header>
			<a class="logo" href="${pageContext.request.contextPath}/index">
				<img src="../static/images/logo.png" alt="logo" style="width: 50px; height: auto;">

			</a>
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}/index">Home</a>

						<c:if test="${pageContext.request.userPrincipal== null}">
							<a href="${pageContext.request.contextPath}/newuser">Register</a>
							<a href="${pageContext.request.contextPath}/login">Login</a>
						</c:if>
						<c:if test="${pageContext.request.userPrincipal!=null}">
							<a href="${pageContext.request.contextPath}/logout">Log Out</a>
							
							<c:if test="${isAdmin}">
								<a test="${isAdmin}" href="${pageContext.request.contextPath}/admin">Página de Admin</a>
							</c:if>
							<c:if test="${isUser}">
								<a test="${isUser}" href="${pageContext.request.contextPath}/userpage">Minhas inscrições</a>
							</c:if>
						</c:if>
					</li>
				</ul>
			</nav>
		</header>
        <main>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
            <div id="inscrever">
                <p style="display: none;">${idEvento}</p>
				<h2>${ename}</h2>

				<c:if test="${eDate}">
					<a href="${pageContext.request.contextPath}/register?event=${idEvento}">
                    <button onclick>inscrever</button>
                </a>
				</c:if>

            </div>
            
            <div id="inputdetails">
				<label for="nameFilter">Filtrar por Nome:</label>
			<input type="text" id="nameFilter">
			
			<label for="escalaoFilter">Filtrar por Escalão:</label>
			<input type="text" id="escalaoFilter">
			</div>
        
			<table border="1">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Dorsal</th>
						<th>Género</th>
						<th>Escalão</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
           		<c:when test="${empty pList}">
					<tr>
						<td colspan="5" align="center">Sem atletas Inscritos</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="atleta" items="${pList}">
						<tr>
							<td>${atleta.nomeAtleta}</td>
							<td>${atleta.getDorsal()}</td>
							<td>${atleta.genero}</td>
							<td>${atleta.escalao}</td>
							<td>
								<c:if test="${atleta.paystatus}">
									Confirmado
								</c:if>
								<c:if test="${not atleta.paystatus}">
									Não Confirmado
								</c:if>
							</td>
						</tr>
					</c:forEach>
           		</c:otherwise>
        		</c:choose>
				</tbody>
			</table>
			
			<table border="1" id="runnersTable">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Dorsal</th>
						<th>Género</th>
						<th>Escalão</th>
						<th>Tempo Total(Minutos)</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
           		<c:when test="${empty tList}">
					<tr>
						<td colspan="5" align="center">Sem atletas Inscritos ou Sem atletas para essa Pesquisa</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="p" items="${tList}">
						<tr>
							<td>${p.nomeAtleta}</td>
							<td>${p.getDorsal()}</td>
							<td>${p.genero}</td>
							<td>${p.escalao}</td>
							<c:choose>
								<c:when test="${p.ttime ne 0}">
									<td>${p.ttime}</td>
								</c:when>
								<c:otherwise>
									<td>N/A</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<h2>Verificar Estado  do Atleta</h2>
			<form action="/detailsPage" method="get">
				<label for="athleteName">Número do Dorsal:</label>
				<input type="hidden" name="idEvento" value="${idEvento}">
				<input type="text" name="Dorsal">
				<button type="submit">Verificar Situação</button>
			</form>	
			<p>${statusMessage}</p>		
        </main>

	</body>

	</html>