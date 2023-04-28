<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/index.css">
<link rel="stylesheet" href="${path}/css/external.css">
<link rel="stylesheet" href="${path}/css/summary.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/youtube.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/summary.js"></script>

</head>

<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<main>
	<input type="hidden" id="loginUserId" value="${sessionScope.loginUserId}">
	<div id="summary">
		<h1>요약</h1>
		<jsp:include page="/layout/summary.jsp">
			<jsp:param name="category" value="역대 이적료 TOP 5" />
		</jsp:include>
		<jsp:include page="/layout/summary.jsp">
			<jsp:param name="category" value="올시즌 이적료 TOP 5" />
		</jsp:include>
		<%-- <jsp:include page="/layout/summary.jsp">
			<jsp:param name="category" value="포지션 별 이적료 TOP 5" />
		</jsp:include> --%>
	</div>
	<div id="external">
      <section class="siteLink">
	  </section>
         <!-- YOUTUBE VIDEO -->
	  <section class="youtube">
	      <div id="player"></div>
	  </section>
	</div>
</main>
<jsp:include page="/layout/footer.jsp"></jsp:include>
<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>