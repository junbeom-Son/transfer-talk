const transferYearSelector = document.querySelector("#transfer-year");
optionTagCreateFunction(makeYearArray(),transferYearSelector);

function makeYearArray(){
	const array = [];
	const start = 1992;
	const end = new Date().getFullYear();
	for(i=start; i<end; i++){
		array.push(i)
	}
	return array;
}

function callTransferList({
	containerData = {}
}) {
	return callAjax({
		url: getContextPath() + "/transfer/summary",
		data: {
			top5: false,
			...containerData
		},
		async: false,
		beforeSend: () => {
			const transferIn =  document.querySelector(".transfer-contents-in");
			while(transferIn.children.length > 8){
				transferIn.removeChild(transferIn.lastChild);
			}
			const transferOut = document.querySelector(".transfer-contents-out");
			while(transferOut.children.length > 8){
				transferOut.removeChild(transferOut.lastChild);
			}
		},
		success: function(data) {
			console.log(data);
			const transferDivElements = [$(".transfer-contents-in"),$(".transfer-contents-out")];
			data.forEach(function(transfers, index) {
				if(transfers.length == 0){ //값이 없을 때
					const noData = document.createElement("div");
					noData.innerText = "선수 정보가 없습니다.";
					noData.className = "transfer-noData-style";
					transferDivElements[index].append(noData);	
				}
				transfers.forEach(function(item) {
					const playerName = document.createElement("div");
					playerName.className = "transfer-name";

					const playerLink = document.createElement("a");
					playerLink.innerText = item.player.player_name;
					playerLink.href = getContextPath() + "/player/detail?playerId=" + item.player.player_id;
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

					transferDivElements[index].append(playerName);
					transferDivElements[index].append(fee);
					transferDivElements[index].append(previousTeam);
					transferDivElements[index].append(newTeam);
					transferDivElements[index].append(age);
					transferDivElements[index].append(player_position);
					transferDivElements[index].append(transfer_year);
				});
			});
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

let transfers = callTransferList({
		containerData: getContainerDataFromParameters(2022)
});

$("#transfer-in-show-btn").click(function() {
	$(".transfer-contents-out").hide();
	$(".transfer-contents-in").show();
});

$("#transfer-out-show-btn").click(function() {
	$(".transfer-contents-in").hide();
	$(".transfer-contents-out").show();
	
});

$("#transfer-all-show-btn").click(function() {
	$(".transfer-contents-in").hide();
	$(".transfer-contents-out").hide();
	$(".transfer-contents-in").show();
	$(".transfer-contents-out").show();
	
});

$("#transfer-year").change(function(){
	const selectYear = $(this).val() == 'none' ? 2022 : $(this).val();
	transfers = callTransferList({
		containerData: getContainerDataFromParameters(selectYear)
	});
})