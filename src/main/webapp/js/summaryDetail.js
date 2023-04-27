
let playerDatas;
const onePageListCount = 20;
let currentPage= 1;
const {title:summaryTitleText, index:summaryIndex} = getContainerDataFromParameters(); 
const totalSummaryDetailArray = [
		{f:callSummaryDetail, data:{}},
		{f:callSummaryDetail, data:{containerData:{'year':'2022'},containerIndex:1}}
];
const detailDatas = totalSummaryDetailArray[Number(summaryIndex)];
console.log(summaryTitleText,summaryIndex);
//시작
document.querySelector(".summary-category").innerHTML = summaryTitleText;
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
				const parentElement = $(".summary-contents");
				if(data.length == 0){
					const noData = document.createElement("div");
					noData.innerText = "선수 정보가 없습니다.";
					noData.className = "grid-noData-style";
					parentElement.get(containerIndex).append(noData);
				}else {
					playerDatas = data;
					btnAction('start',playerDatas);
					$(".summary-detail-btn").show();
				}
		}
	});
}

$('.prev-btn').click(()=>btnAction('prev',playerDatas));
$('.next-btn').click(()=>btnAction('next',playerDatas));

function btnAction(type, playerInfo){	
		//기존 데이터 지우기
		const classElement = document.querySelector(".summary-contents");
		 while (classElement.children.length > 6) {
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
				const playerRank = document.createElement("div");
				playerRank.innerText = i+1;
				playerRank.className = "summary-rank";
				const playerName = document.createElement("div");
				const playerLink = document.createElement("a");
				if(!item.player) console.log(item) 
				playerLink.innerText = item.player.player_name;
				playerLink.href = PATH + "/player/" + item.player.player_id;
				playerName.append(playerLink);
				playerName.className = "summary-name";
				const fee = document.createElement("div");
				fee.innerText = item.fee;
				fee.className = "summary-fee";
				const previousTeam = document.createElement("div");
				previousTeam.innerText = item.previous_team.team_name;
				previousTeam.className = "summary-previous-team";
				const newTeam = document.createElement("div");
				newTeam.innerText = item.new_team.team_name;
				newTeam.className = "summary-new-team"
				const age = document.createElement("div");
				age.innerText = item.age;
				age.className = "summary-age";
				
				classElement.appendChild(playerRank);
				classElement.appendChild(playerName);
				classElement.appendChild(fee);
				classElement.appendChild(previousTeam);
				classElement.appendChild(newTeam);
				classElement.appendChild(age);
				
				//parentElement.append(playerRank);
			  	//parentElement.append(playerName);
			  	//parentElement.append(fee);
			  	//parentElement.append(previousTeam);
			  	//parentElement.append(newTeam);
			  	//parentElement.append(age);
			}
		});
}	