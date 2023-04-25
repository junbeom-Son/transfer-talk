


callSummary = function ({
	containerData = {},
	containerIndex = 0,
	
}) {
	callAjax({
		url: "transfer/summary",
		data: {
			top5:true,
			...containerData
		},
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
					playerLink.href = playerLink.href = getContextPath() + "/player/detail?playerId=" + item.player.player_id;
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


callSummary({});
callSummary({
	containerData:{'year':'2022'}, 
	containerIndex:1
});