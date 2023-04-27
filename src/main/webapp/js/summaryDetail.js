
let playerDatas;
const onePageListCount = 100;
let currentPage= 1;
const {title:summaryTitleText, index:summaryIndex} = getContainerDataFromParameters(); 
const totalSummaryDetailArray = [
		{f:callSummaryDetail, data:{}},
		{f:callSummaryDetail, data:{containerData:{'year':'2022'},containerIndex:1}}
];
const detailDatas = totalSummaryDetailArray[Number(summaryIndex)];
console.log(summaryTitleText,summaryIndex);
//시작
document.querySelector(".summaryDetail-category").innerHTML = summaryTitleText;
promiseAjax([detailDatas]);
$(".summary-detail-btn").hide();


function getContainerDataFromParameters() {
	let data = {};
	let params = location.search.substring(location.search.indexOf("?") + 1).split("&");
	for (let i = 0; i < params.length; ++i) {
		let tmp = params[i].split("=");
		console.log(decodeURIComponent(tmp))
		console.log(tmp)
		const val =  decodeURIComponent(tmp[1]).replace(" TOP 5","");
		data[tmp[0]] = val;
	}
	return data;
}

function callSummaryDetail ({
	containerData = {},
	containerIndex = 0,
}) {
	callAjax({
		url: PATH +"/transfer/summary",
		data: {
			top5:false,
			isSummary:true,
			...containerData
		},
		loadingStart: true,
		loadingEnd : true,
		success:function(data) {
				const parentElement = $(".summaryDetail-contents");
				if(data.length == 0){
					const noData = document.createElement("div");
					noData.innerText = "선수 정보가 없습니다.";
					noData.className = "grid-noData-style";
					parentElement.get(containerIndex).append(noData);
				}else {
					playerDatas = data;
					btnAction('start',playerDatas);
					$(".summaryDetail-btn").show();
				}
		}
	});
}

$('.prev-btn').click(()=>btnAction('prev',playerDatas));
$('.next-btn').click(()=>btnAction('next',playerDatas));

function btnAction(type, playerInfo){	
		//기존 데이터 지우기
		const classElement = document.querySelector(".summaryDetail-contents");
		 while (classElement.children.length > 1) {
			    classElement.removeChild(classElement.lastChild);
		 }
		if(type==='start'){ //처음 start 
			currentPage = 1;
		}else if(type=='prev' && currentPage>1){ // 이전버튼, 1이하불가
			currentPage --;
		}else if(type=='next' && playerInfo.length > onePageListCount * (currentPage)){ // 다음버튼, 데이터의 마지막 이후 불가
			currentPage++;
		}
		playerInfo.forEach((item,i) => {	
			if(onePageListCount * (currentPage) > i && (currentPage-1)*onePageListCount <= i){
				const contentEl =document.createElement("div");
	            classElement.appendChild(contentEl);
	            contentEl.className="summaryDetail-contents-content"
				contentEl.setAttribute ("value",item.player.player_id);
				
				const playerRank = document.createElement("div");
				playerRank.innerText = i+1;
				playerRank.className = "summaryDetail-rank";
				const playerName = document.createElement("div");
				playerName.innerText = item.player.player_name;
				playerName.className = "summaryDetail-name";
				const fee = document.createElement("div");
				fee.innerText = item.fee;
				fee.className = "summaryDetail-fee";
				const previousTeam = document.createElement("div");
				previousTeam.innerText = item.previous_team.team_name;
				previousTeam.className = "summaryDetail-previous-team";
				const newTeam = document.createElement("div");
				newTeam.innerText = item.new_team.team_name;
				newTeam.className = "summaryDetail-new-team"
				const age = document.createElement("div");
				age.innerText = item.age;
				age.className = "summaryDetail-age";
				
				contentEl.appendChild(playerRank);
				contentEl.appendChild(playerName);
				contentEl.appendChild(fee);
				contentEl.appendChild(previousTeam);
				contentEl.appendChild(newTeam);
				contentEl.appendChild(age);
				
				contentEl.addEventListener("click",function(){
						location.href = PATH +"/player/detail?playerId="+ this.getAttribute("value");
				});
			}
		});
		document.querySelector(".summaryDetail").setAttribute("height","100%");
}	