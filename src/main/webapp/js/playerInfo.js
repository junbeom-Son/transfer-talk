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