<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/index.css">
<link rel="stylesheet" href="${path}/css/summary.css">
<link rel="stylesheet" href="${path}/css/transferList.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/transferList.js"></script>
</head>

<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
		<h1>전체 데이터</h1>
		<div class="transfer-container">
			<div class="transfer-name">이름</div>
			<div class="transfer-fee">이적료</div>
			<div class="transfer-previous-team">이전팀</div>
			<div class="transfer-new-team">새팀</div>
			<div class="transfer-age-at-transfer">이적 당시 나이</div>
			<div class="transfer-position">포지션</div>
		</div>
		<c:forEach items="${transfers }" var="transfer">
			<div class="transfer-name">${transfer.player.player_name }</div>
			<div class="transfer-fee">${transfer.fee }</div>
			<div class="transfer-previous-team">${p_team.team_name }</div>
			<div class="transfer-new-team">${n_team.team_name }</div>
			<div class="transfer-age-at-transfer">${age }</div>
			<div class="transfer-position">${player_position }</div>
		</c:forEach>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>