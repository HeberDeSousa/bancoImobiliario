<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bankrupt</title>
</head>
<body>
<h1>Estatísticas Bankrupt</h1>
<ul>
	<li>Partidas terminadas por time out: ${estatistica.timeout } </li>
	<li>Média de turnos por partida: <fmt:formatNumber maxFractionDigits="0" value="${estatistica.mediaTurnos }" /> </li>
	<li>
		Porcentagem de vitórias por comportamento dos jogadores:
		<ul>
			<li>Aleatório: <fmt:formatNumber maxFractionDigits="2" value="${estatistica.percAleatorio }" type="percent" /> </li>
			<li>Cauteloso: <fmt:formatNumber maxFractionDigits="2" value="${estatistica.percCauteloso }" type="percent" /> </li>
			<li>Exigente: <fmt:formatNumber maxFractionDigits="2" value="${estatistica.percExigente }" type="percent" /> </li>
			<li>Impulsivo: <fmt:formatNumber maxFractionDigits="2" value="${estatistica.percImpulsivo }" type="percent" /> </li>
		</ul> 
	</li>
	<li>Comportamento que mais vence: ${estatistica.vencedor }</li>
</ul>
<h2>Log das partidas</h2>
${estatistica.logPartidas }
</body>
</html>