//** 처음 시작 시 호출------------------------------------------------------------------------------------------------------------------------------------------------
// 초기 header에 country데이터 가저오기
let callSummary;
callAjax({
	url:getContextPath() + "/transfer/country",
	method:"post",
	success: function(res){
		res.sort();
		const selectCountryElement = document.querySelector("#header-country");
		res.forEach((el,i) => {
			createElement({
				tag:'option', 
				appndElement:selectCountryElement, 
				innerText:el, 
				value:el
			});
		});
	}
});



//** 이벤트 발생시 처리하는 함수------------------------------------------------------------------------------------------------------------------------------------------------
// scroll 위치가 변할 때 작업 함수
window.addEventListener('scroll', function(){
	const loginOutButton = document.querySelectorAll(".loginOut");
	loginOutButton.forEach((divEl,i)=>{
		if(!divEl.classList.contains('hidden')){
			if(window.scrollY >= 200){
				divEl.classList.add('scrollHidden');
			}else{
				divEl.classList.remove('scrollHidden');
			}
			
		}
	});
});

// country 선택시  header에 leagues데이터 가저오기
$("#header-country").change(function(){
    const countryName = $(this).val();
    callAjax({
		url:getContextPath() +"/transfer/country/leagues",
		method:"post",
		data:{
			country : countryName
		},
		beforeSend : function(){
			 const selectLeagueElement = document.querySelector("#header-league");
			 const selectTeamElement = document.querySelector("#header-team");
			 while (selectLeagueElement.children.length > 1) {
			    selectLeagueElement.removeChild(selectLeagueElement.lastChild);
			 }
			 while (selectTeamElement.children.length > 1) {
			    selectTeamElement.removeChild(selectTeamElement.lastChild);
			 }
		},
		success: function(res){
			res.sort();
			const selectLeagueElement = document.querySelector("#header-league");
			res.forEach((el,i) => {
				createElement({
					tag:'option', 
					appndElement:selectLeagueElement, 
					innerText:el, 
					value:el
				});
			});
			$(".header-selecter-sign.sign1 > img").each((index,item)=>{
				if(index === 0) $(item).removeClass('selected');
				else  $(item).addClass('selected');
			});
			$(".header-selecter-sign.sign2 > img").each((index,item)=>{
				if(index === 1) $(item).removeClass('selected');
				else  $(item).addClass('selected');
			});
		}
	});
});

//league 선택시  header에 teams데이터 가저오기
$("#header-league").change(function(){
    const leagueName = $(this).val();
		callAjax({
			url:getContextPath() +"/transfer/country/league/teams",
			method:"post",
			data:{
				league : leagueName
			},
			beforeSend : function(){
				 const selectTeamElement = document.querySelector("#header-team");
				 while (selectTeamElement.children.length > 1) {
				    selectTeamElement.removeChild(selectTeamElement.lastChild);
				 }
			},
			success: function(res){
				res.sort();
				const selectTeamElement = document.querySelector("#header-team");
				res.forEach((el,i) => {
					createElement({
						tag:'option', 
						appndElement:selectTeamElement, 
						innerText:el, 
						value:el
					});
				});
				$(".header-selecter-sign.sign1 > img").each((index,item)=>{
					if(index === 1) $(item).removeClass('selected');
					else  $(item).addClass('selected');
				});
				$(".header-selecter-sign.sign2 > img").each((index,item)=>{
					if(index === 0) $(item).removeClass('selected');
					else  $(item).addClass('selected');
				});
			}
		});

		if(callSummary){
			callSummary({
				containerData:{
					'league':leagueName
				}
			});
			callSummary({
				containerData:{
					'year':'2022',
					'league':leagueName
				},
				containerIndex : 1 
			});
		}
		
});

//league 선택시  header에 teams데이터 가저오기
$("#header-team").change(function(){
	if(callSummary){
    const teamName = $(this).val();
		callSummary({
			containerData:{
				team:teamName
			}
		});
		callSummary({
			containerData:{
				year:'2022',
				team:teamName
			},
			containerIndex : 1 
		});
	}
	$(".header-selecter-sign.sign2 > img").each((index,item)=>{
		if(index === 1) $(item).removeClass('selected');
		else  $(item).addClass('selected');
	});
});

//header에 자세히보기 버튼클릭 시 함수
$(".header-detail-btn").click(function(){
	const leagueName = $(document.querySelector("#header-league")).val() ==="none" ? null : $(document.querySelector("#header-league")).val();
	const teamName = $(document.querySelector("#header-team")).val() === "none" ? null : $(document.querySelector("#header-team")).val();
	let url = getContextPath() + "/transfer/transferList?"
	let needAndChar = false;
	if (leagueName !== null) {
		url += "league=" + leagueName;
		needAndChar = true;
	}
	if (teamName !== null) {
		if (needAndChar) {
			url += "&";
		}
		url += "team=" + teamName;
		needAndChar = true;
	}
	if (needAndChar) {
		url += "&";
	}
	url += "top5=false";
	location.href=url;
});

//home버튼 클릭 시 함수
$(".header-homelogo").click(() => location.href = getContextPath());

//login버튼 클릭 시 함수
$(".login-container").click(function(){
	console.log('login버튼 클릭 --> 코딩필요');
});

//logout버튼 클릭 시 함수
$(".logout-container").click(function(){
	console.log('logout버튼 클릭 --> 코딩필요');
});




//** 생성한 함수 ------------------------------------------------------------------------------------------------------------------------------------------------

/* callAjax : ajax 호출함수
 * params url, method, dataType, data(object타입), beforeSend(callback함수), success(callback함수), error(callback함수)
 * retrun : 없음
 */
function callAjax({
	url,
	method="get",
	dataType="json",
	data,
	async = true,
	beforeSend,
	success,
	error,
	loadingStart = true,
	loadingEnd =true
}){
	let result;
	$.ajax({
		url:url,
		method:method,
		dataType:dataType,
		async:async,
		beforeSend : function(){
			if(beforeSend)beforeSend();
	        if(loadingStart) $("#my-spinner").show();
	     },
	    data:data,
		success:function(res){
			result = res;
			//console.log('success',res)
			if(success)success(res);
			if(loadingEnd) $("#my-spinner").hide();
		},
		error:function(err){
			console.warn('error',err)
			if(error)error(res);
			if(loadingEnd) $("#my-spinner").hide();
		}
	});
	return result;
};


/* createElement : 태그 생성함수
 * params : tag(태그이름), appndElement(append할 대상), text(innerHTML값), value(value속성 값)
 * retrun : 없음
 */
function createElement ({
	tag ='div', 
	appndElement, 
	innerText,
	value
}){
	const newTag = document.createElement(tag);
	newTag.setAttribute('value',value);
	newTag.innerHTML = innerText;
	appndElement.appendChild(newTag);
}

/* getContextPath : ContextPath 구하는 함수
 * retrun : 없음
 */
function getContextPath() {
  var hostIndex = location.href.indexOf( location.host ) + location.host.length;
  return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
} 

