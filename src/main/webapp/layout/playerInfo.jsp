<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/index.css">
<link rel="stylesheet" href="${path}/css/plyerInfo.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
</head>

<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<main>
<div class="playerInfo-container">
	<div id="player_name">선수이름</div>
	<div id="player_age">이적 당시 나이</div>
	<div id="player_picture">선수사진</div>
	<div id="previousteam">이전팀</div>
	<div id="previousteam_picture">이전팀 사진</div>	
	<div id="newteam">새팀</div>
	<div id="newteam_picture">새팀 사진</div>
	<div id="position">포지션</div>
	<div id="position_picture">포지션 사진</div>
	<div id="fee">이적료</div>	
</div>
</main>
<jsp:include page="/layout/footer.jsp"></jsp:include>
<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>