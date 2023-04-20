//index.js

// 초기 header에 country데이터 가저오기
$.ajax({
	url:"transfer/country",
	method:"post",
	dataType:"json",
	beforeSend : function(){
        $("#my-spinner").show();
     },
	success:function(res){
		//console.log('success',res)
		res.sort();
		const selectCountryElement = document.querySelector("#header-country");
		res.forEach((el,i) => {
			createElement('option', selectCountryElement, el,el);
		});
		$("#my-spinner").hide();
	},
	error:function(err){
		//console.warn('error',err)
		$("#my-spinner").hide();
	}
});

// country 선택시  header에 leagues데이터 가저오기
$("#header-country").change(function(){
    const country = $(this).val();
    $.ajax({
		url:"transfer/country/leagues",
		method:"post",
		data:{
			country
		},
		dataType:"json",
		beforeSend : function(){
			 const selectLeagueElement = document.querySelector("#header-league");
			 const selectTeamElement = document.querySelector("#header-team");
			 while (selectLeagueElement.children.length > 1) {
			    selectLeagueElement.removeChild(selectLeagueElement.lastChild);
			 }
			 while (selectTeamElement.children.length > 1) {
			    selectTeamElement.removeChild(selectTeamElement.lastChild);
			 }
	        $("#my-spinner").show();
	     },
		success:function(res){
			//console.log('success',res)
			res.sort();
			const selectLeagueElement = document.querySelector("#header-league");
			res.forEach((el,i) => {
				createElement('option', selectLeagueElement, el, el);
			});
			$("#my-spinner").hide();
		},
		error:function(err){
			//console.warn('error',err)
			$("#my-spinner").hide();
		}
	});
});

//league 선택시  header에 teams데이터 가저오기
$("#header-league").change(function(){
    const league = $(this).val();
    $.ajax({
		url:"transfer/country/league/teams",
		method:"post",
		data:{
			league
		},
		dataType:"json",
		beforeSend : function(){
			const selectTeamElement = document.querySelector("#header-team");
			 while (selectTeamElement.children.length > 1) {
			    selectTeamElement.removeChild(selectTeamElement.lastChild);
			 }
	        $("#my-spinner").show();
	     },
		success:function(res){
			//console.log('success',res)
			res.sort();
			const selectTeamElement = document.querySelector("#header-team");
			res.forEach((el,i) => {
				createElement('option', selectTeamElement, el, el);
			});
			$("#my-spinner").hide();
		},
		error:function(err){
			//console.warn('error',err)
			$("#my-spinner").hide();
		}
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




