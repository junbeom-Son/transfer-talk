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
<link rel="stylesheet" href="${path}/css/playerInfo.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
</head>

<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<main>
<div class="playerInfo-container">
	<div id="player_name">${player.player_name}</div>
	<div id="player_age">${transfers[0].age }</div>
	<div id="player_picture">
	<img src="https://img.a.transfermarkt.technology/portrait/header/427568-1681828000.jpg?lm=1">
	</div>
	<div id="previousteam">${transfers[0].previous_team.team_name }</div>
	<div id="previousteam_picture">
	<img src="https://tmssl.akamaized.net/images/wappen/head/11.png?lm=1489787850">
	</div>	
	<div id="newteam">${transfers[0].new_team.team_name }</div>
	<div id="newteam_picture">
	<img src="https://tmssl.akamaized.net/images/wappen/head/281.png?lm=1467356331">
	</div>
	<div id="position">${transfers[0].player_position }</div>
	<div id="position_picture">포지션 사진</div>
	<div id="fee">${transfers[0].fee }</div>	
</div>
</main>
<jsp:include page="/layout/footer.jsp"></jsp:include>
<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>