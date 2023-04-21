//index.js
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
		},
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
});

//header에 자세히보기 버튼
$(".header-detail-btn").click(function(){
	const leagueName = $(document.querySelector("#header-league")).val() ==="none" ? null : $(document.querySelector("#header-league")).val();
	const teamName = $(document.querySelector("#header-team")).val() === "none" ? null : $(document.querySelector("#header-team")).val();
	location.href=getContextPath() +"/transfer/summary?league=" + leagueName+'&team='+teamName+'&top5='+false;
});



/* callAjax : ajax 호출함수
 * params url, method, dataType, data(object타입), beforeSend(callback함수), success(callback함수), error(callback함수)
 * retrun : 없음
 */
function callAjax({
	url,
	method="get",
	dataType="json",
	data,
	beforeSend,
	success,
	error,
	loadingStart = true,
	loadingEnd =true
}){
	$.ajax({
		url:url,
		method:method,
		dataType:dataType,
		beforeSend : function(){
			if(beforeSend)beforeSend();
	        if(loadingStart) $("#my-spinner").show();
	     },
	    data:data,
		success:function(res){
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


function getContextPath() {
  var hostIndex = location.href.indexOf( location.host ) + location.host.length;
  return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}

