function callTransferList ({
	containerData = {}
}) {
	callAjax({
		url: getContextPath() + "/transfer/summary",
		data: {
			top5:false,
			...containerData
		},
		success:function(data) {
			let parentElement = $(".transfer-contents-in");
			if(data.length == 0){
				const noData = document.createElement("div");
				noData.innerText = "선수 정보가 없습니다.";
				noData.className = "grid-noData-style";
				parentElement.append(noData);
			}else {
				data.forEach(function(transfers, index) {
					if (index === 0) {
						parentElement = $(".transfer-contents-in");
					} else if (index === 1) {
						parentElement = $(".transfer-contents-out");
					}
					transfers.forEach(function(item) {
						const playerName = document.createElement("div");
					playerName.className = "transfer-name";
					
					const playerLink = document.createElement("a");
					playerLink.innerText = item.player.player_name;
					playerLink.href = getContextPath() + "/player/" + item.player.player_id;
					playerName.append(playerLink);
					
					const fee = document.createElement("div");
					fee.innerText = item.fee;
					fee.className = "transfer-fee";
					
					const previousTeam = document.createElement("div");
					previousTeam.innerText = item.previous_team.team_name;
					previousTeam.className = "transfer-previous-team";
					
					const newTeam = document.createElement("div");
					newTeam.innerText = item.new_team.team_name;
					newTeam.className = "transfer-new-team";

					const age = document.createElement("div");
					age.innerText = item.age;
					age.className = "transfer-age";
					
					const player_position = document.createElement("div");
					player_position.innerText = item.player_position;
					player_position.className = "transfer-position";
					
					const transfer_year = document.createElement("div");
					transfer_year.innerText = item.transfer_year;
					transfer_year.className = "transfer-year";
					
				  	parentElement.append(playerName);
				  	parentElement.append(fee);
				  	parentElement.append(previousTeam);
				  	parentElement.append(newTeam);
				  	parentElement.append(age);
				  	parentElement.append(player_position);
				  	parentElement.append(transfer_year);
					});
				});
			}
		}
	});
}

function getContainerDataFromParameters(year) {
	let data = {};
	let params = location.search.substring(location.search.indexOf("?") + 1).split("&");
	for (let i = 0; i < params.length; ++i) {
		let tmp = params[i].split("=");
		data[tmp[0]] = tmp[1];
	}
	data['year'] = year;
	return data;
}

callTransferList({
	containerData : getContainerDataFromParameters(2022)});