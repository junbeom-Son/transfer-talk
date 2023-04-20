<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	
	<link rel="stylesheet" href="${path}/css/index.css">
	<script defer src="${path}/js/index.js"></script>
</head>

<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
		<div>메인내용</div>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
</body>

</html>