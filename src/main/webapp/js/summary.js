const callSummary = function ({
	containerData = {},
	containerIndex = 0,
	loadingStart = true, //23.04.25 jin 추가
	loadingEnd = true //23.04.25 jin 추가
}) {
	callAjax({
		url: "transfer/summary",
		data: {
			top5:true,
			...containerData
		},
		loadingStart: loadingStart,
		loadingEnd : loadingEnd,
		beforeSend:function(){
			const parent =$(".summary-contents").get(containerIndex);
			while(parent.children.length > 6){
				parent.removeChild(parent.lastChild);
			}
		},
		success:function(data) {
			const parentElement = $(".summary-contents");
			if(data.length == 0){
				const noData = document.createElement("div");
				noData.innerText = "선수 정보가 없습니다.";
				noData.className = "grid-noData-style";
				parentElement.get(containerIndex).append(noData);
			}else {
				data.forEach(function(item,i) {
					const playerRank = document.createElement("div");
					playerRank.innerText = i+1;
					playerRank.className = "summary-rank";
					const playerName = document.createElement("div");
					const playerLink = document.createElement("a");
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
					
					parentElement.get(containerIndex).append(playerRank);
				  	parentElement.get(containerIndex).append(playerName);
				  	parentElement.get(containerIndex).append(fee);
				  	parentElement.get(containerIndex).append(previousTeam);
				  	parentElement.get(containerIndex).append(newTeam);
				  	parentElement.get(containerIndex).append(age);
				});
			}
		}
	});
}

function callTotalSummary (){
	const array = [
		{f:callSummary, data:{loadingStart:false, loadingEnd:false}},
		{f:callSummary, data:{containerData:{'year':'2022'},containerIndex:1, loadingStart:false}}
	];
	return array;
}
function callLeagueSummary (){
	const leagueName = $(this).val();
	const array = [
		{f:callSummary, data:{ containerData:{'league':leagueName}, loadingStart:false, loadingEnd:false}},
		{f:callSummary, data:{ containerData:{'year':'2022','league':leagueName}, containerIndex : 1, loadingStart:false}}
	];
	return array;
}
function callTeamSummary (){
	const teamName = $(this).val();
	const array = [
		{f:callSummary, data:{ containerData:{team:teamName}, loadingEnd:false}},
		{f:callSummary, data:{ containerData:{year:'2022', team:teamName}, containerIndex:1, loadingStart:false}}
	];
	return array;
}

//다수의 ajax가 실행되고 화면이 보여지도록 refactoring : 23.04.25 jin
/*promiseAjax([callCountry(), ...callTotalSummary() ]);*/
promiseAjax([...callTotalSummary() ]);