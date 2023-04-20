//index.js

function createElement (tag,appndElement, text, value){
	const newTag = document.createElement(tag);
	newTag.setAttribute('value',value);
	newTag.innerHTML = text;
	appndElement.appendChild(newTag);
}


$.ajax({
	url:"transfer/country",
	method:"get",
	dataType:"json",
	beforeSend : function(req){
        $("#my-spinner").show();
     },
	success:function(res){
		console.log('success',res)
		res.sort();
		res.forEach((el,i) => {
			createElement('option', document.querySelector("#header-nation"), el,el);
		});
		$("#my-spinner").hide();
	},
	error:function(err){
		console.warn('error',err)
		$("#my-spinner").hide();
	}
});



