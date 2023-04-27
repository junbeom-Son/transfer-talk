<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="${path}/css/index.css">
<link rel="stylesheet" href="${path}/css/playerInfo.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/playerInfo.js"></script>
</head>

<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
		<div id="playerInfo">
			<c:set var="pre_img" value="${transfers[0].previous_team.team_img_src}"></c:set>
			<c:set var="new_img" value="${transfers[0].new_team.team_img_src}"></c:set>
			<c:set var="no_img" value="${path}/images/defaultTeam.webp"></c:set>
			<div class="playerInfo-container">
				<div id="player_name">이름 : ${player.player_name}</div>
				<div id="player_age">${transfers[0].age }</div>
				<div id="picture_player"></div>
				<div id="previousteam">${transfers[0].previous_team.team_name }</div>
				<div id="picture_previousteam">
					<img src="${pre_img==null?no_img:pre_img}" alt="전이미지">
				</div>
				<div id="newteam">${transfers[0].new_team.team_name }</div>
				<div id="picture_newteam">
					<img src="${transfers[0].new_team.team_img_src}">
				</div>
				<div id="position">${transfers[0].player_position }</div>
				<div id="picture_arrow">
					<img src="../images/arrow-right.png">
				</div>
				<div id="fee">${transfers[0].fee }</div>
			</div>
			<i class="fas fa-star starImg " style="font-size: 48px; color: yellow; -webkit-text-stroke: 2px gray;"></i> 
			<i class="fas fa-star starImg hidden" style="font-size: 48px; color: white; -webkit-text-stroke: 2px gray;"></i>
		</div>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>