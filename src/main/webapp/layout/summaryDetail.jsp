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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/summaryDetail.js"></script>
</head>
<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
	<div class="summary">
		<div class="summary-container">
			<div class="summary-title">
				<div class="summary-category"></div>
			</div>	
			<div class="summary-contents">
				<div class="summary-rank table-title">순위</div>
				<div class="summary-name table-title">이름</div>
				<div class="summary-fee table-title">이적료</div>
				<div class="summary-previous-team table-title">이전팀</div>
				<div class="summary-new-team table-title">새팀</div>
				<div class="summary-age table-title">나이</div>
			</div>
		</div>
	</div>
	<div class="summary-detail-btn">
		<button class="prev-btn">이전</button>
		<button class="next-btn">다음</button>
	</div>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>
</html>