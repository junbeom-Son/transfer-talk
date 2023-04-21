
callSummary({});
callSummary({
	containerData:{'year':'2022'}, 
	containerIndex:1
});


function callSummary({
	containerData = {},
	containerIndex = 0,
	
}) {
	callAjax({
		url: "transfer/summary",
		data: containerData,
		beforeSend:function(){
			const parent =$(".summary-container").get(containerIndex);
			while(parent.children.length > 6){
				parent.removeChild(parent.lastChild);
			}
		},
		success:function(data) {
			data.forEach(function(item) {
				let playerName = document.createElement("div")
				playerName.innerText = item.player.player_name;
				playerName.className = "summary-name";
				let fee = document.createElement("div")
				fee.innerText = item.fee;
				fee.className = "summary-fee";
				let previousTeam = document.createElement("div")
				previousTeam.innerText = item.previous_team.team_name;
				previousTeam.className = "summary-previous-team";
				let newTeam = document.createElement("div")
				newTeam.innerText = item.new_team.team_name;
				newTeam.className = "summary-new-team"
				let age = document.createElement("div");
				age.innerText = item.age;
				age.className = "summary-age";
				
				$(".summary-container").get(containerIndex).append(playerName);
				$(".summary-container").get(containerIndex).append(fee);
				$(".summary-container").get(containerIndex).append(previousTeam);
				$(".summary-container").get(containerIndex).append(newTeam);
				$(".summary-container").get(containerIndex).append(age);
			});
		}
	});
}


