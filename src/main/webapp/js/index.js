//index.js


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
	error
}){
	$.ajax({
		url:url,
		method:method,
		dataType:dataType,
		beforeSend : function(){
			if(beforeSend)beforeSend();
	        $("#my-spinner").show();
	     },
	    data:data,
		success:function(res){
			//console.log('success',res)
			if(success)success(res);
			$("#my-spinner").hide();
		},
		error:function(err){
			console.warn('error',err)
			if(error)error(res);
			$("#my-spinner").hide();
		}
	});
};

// 초기 header에 country데이터 가저오기
callAjax({
	url:"transfer/country",
	method:"post",
	success: function(res){
		res.sort();
		const selectCountryElement = document.querySelector("#header-country");
		res.forEach((el,i) => {
			createElement('option', selectCountryElement, el,el);
		});
	}
});




// country 선택시  header에 leagues데이터 가저오기
$("#header-country").change(function(){
    const country = $(this).val();
    callAjax({
		url:"transfer/country/leagues",
		method:"post",
		data:{
			country
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
				createElement('option', selectLeagueElement, el, el);
			});
		},
	});
});

//league 선택시  header에 teams데이터 가저오기
$("#header-league").change(function(){
    const league = $(this).val();
    callAjax({
		url:"transfer/country/league/teams",
		method:"post",
		data:{
			league
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
				createElement('option', selectTeamElement, el, el);
			});
		},
	});
});

/* createElement : 태그 생성함수
 * params : tag(태그이름), appndElement(append할 대상), text(innerHTML값), value(value속성 값)
 * retrun : 없음
 */
function createElement (tag,appndElement, text, value){
	const newTag = document.createElement(tag);
	newTag.setAttribute('value',value);
	newTag.innerHTML = text;
	appndElement.appendChild(newTag);
}




