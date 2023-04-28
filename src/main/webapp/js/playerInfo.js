function getContainerDataFromParameters() {
	let data = {};
	let params = location.search.substring(location.search.indexOf("?") + 1).split("&");
	for (let i = 0; i < params.length; ++i) {
		let tmp = params[i].split("=");
		data[tmp[0]] = tmp[1];
	}
	return data;
}

callAjax({
	url : getContextPath() + "/player/img",
	data: getContainerDataFromParameters(),
	success : (res)=>{
		const imgSrc = res || getContextPath() + "/images/defaultPlayer.webp";
		console.log(imgSrc);
		const parent = document.querySelector("#picture_player");
		const imgEl = document.createElement("img");
		imgEl.setAttribute("src",imgSrc);
		parent.appendChild(imgEl);
	},
})

function myFunction() {
  const starFilled = document.querySelector('.starImg:not(.hidden)');
  const starEmpty = document.querySelector('.starImg.hidden');

  if (starFilled) {
    starFilled.classList.add('hidden');
    starEmpty.classList.remove('hidden');
  } else {
    starEmpty.classList.add('hidden');
    document.querySelector('.starImg:not(.hidden)').classList.remove('hidden');
  }
}  

function myFunction() {
  const user_id = "??"; // 사용자 ID
  const player_id = new URLSearchParams(location.search).get('playerId'); // 선수 ID

  callAjax({
    url: "",
    type: "POST",
    data: {
      user_id: user_id,
      player_id: player_id
    },
    success: function(response) {
      console.log("데이터 저장 완료");
    },
    error: function(xhr, status, error) {
      console.error("에러 발생:", error);
    }
   
  });
  
  }
  

  








/*callAjax({
	url : getContextPath() + "/player/img",
	data: getContainerDataFromParameters(),
	success : (res)=>{
		const imgSrc = res || getContextPath() + "/images/defaultTeam.webp";
		console.log(imgSrc);
		const parent = document.querySelector("#picture_previousteam");
		const imgEl = document.createElement("img");
		imgEl.setAttribute("src",imgSrc);
		parent.appendChild(imgEl);
	},
})
callAjax({
	url : getContextPath() + "/player/img",
	data: getContainerDataFromParameters(),
	success : (res)=>{
		const imgSrc = res || getContextPath() + "/images/defaultTeam.webp";
		console.log(imgSrc);
		const parent = document.querySelector("#picture_newteam");
		const imgEl = document.createElement("img");
		imgEl.setAttribute("src",imgSrc);
		parent.appendChild(imgEl);
	},
})*/