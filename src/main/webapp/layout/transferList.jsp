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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/index.css">
<link rel="stylesheet" href="${path}/css/summary.css">
<link rel="stylesheet" href="${path}/css/transferList.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/transferList.js"></script>

</head>

<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
		<h1>${title != null ? title : '전체데이터'}</h1>
		<div class="transfer-menu">
			<div class="transfer-menu-btn">
				<button id="transfer-in-show-btn" class="btn-info">in</button>
				<button id="transfer-out-show-btn" class="btn-info">out</button>
				<button id="transfer-all-show-btn" class="btn-info">in & out</button>
			</div>
			<div class="transfer-menu-select">
				<select name="transfer-year" id="transfer-year" class="transfer-year-select">
				  	<option value="none" class="transfer-year-selecter-option">연도</option>
				</select>
			</div>
		</div>
		
		<div class="transfer-contents-in">
			<h2 class="in-out-header">in</h2>
			<div class="transfer-contents-title">
				<div class="transfer-name transfer-title">이름</div>
				<div class="transfer-fee transfer-title">이적료</div>
				<div class="transfer-previous-team transfer-title">이전팀</div>
				<div class="transfer-new-team transfer-title">새팀</div>
				<div class="transfer-age-at-transfer transfer-title">이적 당시 나이</div>
				<div class="transfer-position transfer-title">포지션</div>
				<div class="transfer-year transfer-title">이적 년도</div>
			</div>	
		</div>
		<div class="transfer-contents-out">
			<h2 class="in-out-header">out</h2>
			<div class="transfer-contents-title">
				<div class="transfer-name transfer-title">이름</div>
				<div class="transfer-fee transfer-title">이적료</div>
				<div class="transfer-previous-team transfer-title">이전팀</div>
				<div class="transfer-new-team transfer-title">새팀</div>
				<div class="transfer-age-at-transfer transfer-title">이적 당시 나이</div>
				<div class="transfer-position transfer-title">포지션</div>
				<div class="transfer-year transfer-title">이적 년도</div>
			</div>
		</div>

	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>