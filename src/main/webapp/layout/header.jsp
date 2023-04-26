<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<header>
	<div class="header-homelogo">
		<img alt="homeIcon" src="${path}/images/home.png">
	</div>
	<div class="header-dropdown-container">
		<div class="header-selecter-container">
			<%-- <div class="header-selecter-contents">
				<form class="header-selecter-form">
				  <select name="header-country" id="header-country" class="header-selecter-select">
				  	<option value="none" class="header-selecter-option">국가를 선택하세요.</option>
				  </select>
				  <span class="iconArrow"><img src="${path}/images/arrow-down.png" alt="arrow_down"></span>
				</form>
			</div> 
			<div class="header-selecter-sign sign1">
				<img class="next-arrow-Img selected" src="${path}/images/next-arrow.png" alt="next-arrow">
				<img class="next-arrow-selected-Img" src="${path}/images/next-arrow-selected.png" alt="next-arrow-selected">
			</div> --%>
			<div class="header-selecter-contents">
				<form class="header-selecter-form">
				  <select name="header-league" id="header-league" class="header-selecter-select">
				  	<option value="none" class="header-selecter-option">리그를 선택하세요.</option>
				  </select>
				  <span class="iconArrow"><img src="${path}/images/arrow-down.png" alt="arrow_down"></span>
				</form>
			</div> 
			<div class="header-selecter-sign sign2">
				<img class="next-arrow-Img selected" src="${path}/images/next-arrow.png" alt="next-arrow">
				<img class="next-arrow-selected-Img" src="${path}/images/next-arrow-selected.png" alt="next-arrow-selected">
			</div>
			<div class="header-selecter-contents">
				<form class="header-selecter-form">
				  <select name="header-team" id="header-team" class="header-selecter-select">
				  	<option value="none" class="header-selecter-option">팀을 선택하세요.</option>
				  </select>
				  <span class="iconArrow"><img src="${path}/images/arrow-down.png" alt="arrow_down"></span>
				</form>
			</div> 
			<div class="header-detail-btn detail-btn">
				<button>상세보기</button>
			</div>
		</div>
	</div>
	<div class="header-hover-container">
		<div class="header-hover-loginOut">
			<div class="login-container">
				<button class="login-button">
					<img alt="loginIcon" src="${path}/images/login.png">
					<div>Login</div>
				</button>
			</div>
			<div class="logout-container hidden">
				<button class="logout-button">Logout</button>
			</div>
		</div>
		<div class="header-hover-contents">
			<li class="header-item">
				<div class="item-name">이적정보</div>
				<div class="item-contents">
            		<div class="contents-menu">
						<ul class="inner">
							<li>시즌별 정보</li>
							<li>top 이적</li>
							<li>이적료</li>
						</ul>
					</div>
				</div>
			</li>
			<li class="header-item">
				<div class="item-name">팀정보</div>
				<div class="item-contents">
					<div class="contents-menu">
						<ul class="inner">
							<li>팀상세정보</li>
							<li>팀 검색</li>
						</ul>
					</div>
				</div>
			</li>
			<li class="header-item">
				<div class="item-name">선수정보</div>
				<div class="item-contents">
					<div class="contents-menu">
						<ul class="inner">
							<li>선수상세정보</li>
							<li>선수 검색</li>
						</ul>
					</div>
				</div>
			</li>
			<li class="header-item">
				<div class="item-name">나의 메뉴</div>
				<div class="item-contents">
					<div class="contents-menu">
						<ul class="inner">
							<li>My Team</li>
							<li>My Player</li>
						</ul>
					</div>
				</div>
			</li>
		</div>
	</div>
</header>