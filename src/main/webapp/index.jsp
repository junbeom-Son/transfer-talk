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
<link rel="stylesheet" href="${path}/css/summary.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/summary.js"></script>
</head>

<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<main>
	<h1>요약</h1>
		<jsp:include page="/layout/summary.jsp">
			<jsp:param name="category" value="역대 이적료 TOP 5" />
		</jsp:include>
		<jsp:include page="/layout/summary.jsp">
			<jsp:param name="category" value="올시즌 이적료 TOP 5" />
		</jsp:include>
		<jsp:include page="/layout/summary.jsp">
			<jsp:param name="category" value="포지션 별 이적료 TOP 5" />
		</jsp:include>
	</main>
<jsp:include page="/layout/footer.jsp"></jsp:include>
</body>

</html>