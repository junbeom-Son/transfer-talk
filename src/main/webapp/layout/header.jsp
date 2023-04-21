<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>

	<div class="header-dropdown-container">
		<div class="header-selecter-container">
				<div class="header-selecter-contents header-selecter-title">My Team</div>
				<div class="header-selecter-contents header-selecter-form">
					<form>
					  <select name="header-country" id="header-country">
					  	<option value="none">국가를 선택하세요.</option>
					  </select>
					</form>
				</div> 
				<div class="header-selecter-contents header-selecter-sign">>></div>
				<div class="header-selecter-contents header-selecter-form">
					<form>
					  <select name="header-league" id="header-league">
					  	<option value="none">리그를 선택하세요.</option>
					  </select>
					</form>
				</div> 
				<div class="header-selecter-contents header-selecter-sign">>></div>
				<div class="header-selecter-contents header-selecter-form">
					<form>
					  <select name="header-team" id="header-team">
					  	<option value="none">팀을 선택하세요.</option>
					  </select>
					</form>
				</div> 
		</div>
	</div>
	<div class="header-hover-container">
		<div class="header-left-contents">
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
		</div>
		<!-- <li class="login-container">
			<div class="item-name">로그인</div>
			<div class="item-contents">
				로그인 관련 div
			</div>
		</li> -->
	</div>
	
</header>