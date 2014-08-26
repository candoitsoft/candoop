/** 생년월일 날짜 컨트롤 스크립트. */
var today = new Date();
for(var y = 0; y < 100; y++ ){
	if(y === 25){
		$("#birthY").append("<option value='"+(today.getFullYear()-y)+"' selected='selected'>"+(today.getFullYear()-y)+"</option>");
	} else {
		$("#birthY").append("<option value='"+(today.getFullYear()-y)+"'>"+(today.getFullYear()-y)+"</option>");
	}
}
for(var m = 1; m <= 12; m++ ){
	var mVar = m+"";
	if(m < 10){ mVar = "0"+mVar;}
	$("#birthM").append("<option value='"+mVar+"'>"+mVar+"</option>");
}
for(var d = 1; d <= 31; d++ ){
	var dVar = d+"";
	if(d < 10){ dVar = "0"+dVar;}
	$("#birthD").append("<option value='"+dVar+"'>"+dVar+"</option>");
}

// 년/월 선택시 일 변경.
function setBDate(){
	var mVal = $("#birthM").val();
	$("#birthD option[value='29']").remove();
	$("#birthD option[value='30']").remove();
	$("#birthD option[value='31']").remove();
	if(mVal==2){
		// 윤달 계산
		if( ($("#birthY").val() % 4 ) == 0 && ($("#birthY").val() % 100 ) !=0 || ($("#birthY").val() % 400 ) == 0){
	$("#birthD").append("<option value='29'>29</option>");
		}
	} else if(mVal==1 || mVal==3 || mVal==5 || mVal==7 || mVal==8 || mVal==10 || mVal==12){
		$("#birthD").append("<option value='29'>29</option>");
		$("#birthD").append("<option value='30'>30</option>");
		$("#birthD").append("<option value='31'>31</option>");
	} else {
		$("#birthD").append("<option value='29'>29</option>");
		$("#birthD").append("<option value='30'>30</option>");
	}
}