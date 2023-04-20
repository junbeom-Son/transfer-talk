$.ajax({
	url: "/transfer/summary",
	dataType:"json",
	success: function(data, status, xhr) {
		let top5InRecord = data.top5InRecord;
		$.each(top5InRecord, function(index, item) {
			let playerName = document.createElement("div").innerText(item.player.player_name)
			playerName.className = "summary-name";
			let fee = document.createElement("div").innerText(item.fee);
			fee.className = "summary-fee";
			let previousTeam = document.createElement("div").innerText(item.previousTeam.team_name);
			previousTeam.className = "summary-previous-team";
			let newTeam = document.createElement("div").innerText(item.newTeam.team_name);
			newTeam.className = "summary-new-team"
			$(".summary-container").append(playerName);
			$(".summary-container").append(fee);
			$(".summary-container").append(previousTeam);
			$(".summary-container").append(newTeam);
		});
	}
})
		