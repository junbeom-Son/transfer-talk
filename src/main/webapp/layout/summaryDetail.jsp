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
<link rel="stylesheet" href="${path}/css/summaryDetail.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.4/gsap.min.js" integrity="sha512-f8mwTB+Bs8a5c46DEm7HQLcJuHMBaH/UFlcgyetMqqkvTcYg4g5VXsYR71b3qC82lZytjNYvBj2pf0VekA9/FQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/summaryDetail.js"></script>
</head>
<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
	<div class="summaryDetail">
		<div class="summaryDetail-container">
			<div class="summaryDetail-title">
				<div class="summaryDetail-category"></div>
				<div class="summaryDetail-btn">
					<button class="prev-btn btn-info">이전</button>
					<button class="next-btn btn-info">다음</button>
				</div>
			</div>	
			<div class="summaryDetail-contents">
				<div class="summaryDetail-contents-title">
					<div class="summaryDetail-rank table-title">순위</div>
					<div class="summaryDetail-name table-title">이름</div>
					<div class="summaryDetail-fee table-title">이적료</div>
					<div class="summaryDetail-previous-team table-title">이전팀</div>
					<div class="summaryDetail-new-team table-title">새팀</div>
					<div class="summaryDetail-age table-title">나이</div>
				</div>
			</div>
		</div>
	</div>
	<button class="top-btn">top</button>
	<button class="down-btn">down</button>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>
</html>