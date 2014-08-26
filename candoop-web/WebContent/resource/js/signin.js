var confVal = {
	"email" : "N",
	"passwd" : "N",
	"nicname" : "N",
};

function putEmail(){
	confVal.email = "N";
}
function putPasswd(){
	confVal.passwd = "N";
	chkPw();
}
function putNicname(){
	confVal.nicname = "N";
}

function setMail(mailVal){
	if(mailVal.value == "write"){
		$("#email2").removeAttr("disabled");
		$("#email2").val("");
	} else {
		$("#email2").attr("disabled", "disabled");
		$("#email2").val(mailVal.value);
	}
}

// ID 중복 확인
function checkMail() {
	var mail1 = $("#email1").val();
	var mail2 = $("#email2").val();
	var tEmail = mail1+"@"+mail2;
	if(mail1 === ""){
		alert("메일주소를 입력하세요.");
		$("#email1").focus();
		return;
	}
	if(mail2 === ""){
		alert("메일주소를 입력하세요.");
		$("#email2").focus();
		return;
	}
	$.ajax({
		type : "GET",
		data : "cmd=checkMail&email=" + tEmail,
		url : "/AcAjax",
		dataType:"json",
		success : function(data) {
			console.log(data);
			if (data.result == "OK") {
				alert("가입 가능한 메일입니다.");
				confVal.email = "Y";
			} else {
				alert("이미 가입되어 있는 메일입니다.");
				confVal.email = "N";
			}
		}
	});
}

function checkNick(){
	var nick = $("#nicname").val();
	if(nick === ""){
		alert("닉네임을 입력하세요.");
		$("#nicname").focus();
		return;
	}
	$.ajax({
		type : "GET",
		data : "cmd=checkNick&nicname=" + nick,
		url : "/AcAjax",
		dataType:"json",
		success : function(data) {
			console.log(data);
			if (data.result == "OK") {
				alert("사용 가능한 닉네임 입니다.");
				confVal.nicname = "Y";
			} else {
				alert("이미 사용중인 닉네임 입니다.");
				confVal.nicname = "N";
			}
		}
	});
}

// PW 확인
function chkPw() {
	if ($("#passwd").val().length == 0) {
		$("#chkPw").text("비밀번호를 입력하세요.");
	} else {
		if ($("#passwd2").val() == $("#passwd").val()) {
			$("#chkPw").text("비밀번호가 일치합니다.");
			$("#chkPw").attr("class", "text-primary");
			
			confVal.passwd = "Y";
		} else {
			$("#chkPw").text("비밀번호가 일치하지 않습니다.");
			$("#chkPw").attr("class", "text-danger");
			confVal.passwd = "N";
		}
	}
}

function signin() {
	var frm = document.signinFrm;
	if(confVal.email == "Y" && confVal.passwd == "Y" && confVal.nicname == "Y"){
		if(confirm("입력하신 정보로 가입하시겠습니까?")){
			$("#email").val($("#email1").val()+"@"+$("#email2").val());
			console.log($("#email").val());
			frm.method="POST";
			frm.cmd.value="signin";
			frm.toUrl.value="/";
			frm.action="/Confirm";
			frm.submit();
		}
	} else if(confVal.email == "N"){
		alert("이메일 중복 확인이 되지 않았습니다.\n이메일 중복을 확인하십시오.");
		return;
	} else if(confVal.passwd == "N"){
		alert("비밀번호가 일치하지 않습니다.\n비밀번호를 확인하십시오.");
		return;
	} else if(confVal.nicname == "N"){
		alert("닉네임 중복 확인이 되지 않았습니다.\n닉네임 중복을 확인하십시오.");
		return;
	}
}

function checkModifNick(preNick){
	var nick = $("#nicname").val();
	if(nick === preNick){
		if(confirm("닉네임이 변경되지 않았습니다.\n기존 닉네임을 계속 사용하시겠습니까?")){
			confVal.nicname = "Y";
		} else {
			confVal.nicname = "N";
		}
	} else {
		$.ajax({
			type : "GET",
			data : "cmd=checkNick&nicname=" + nick,
			url : "/MpickAjax",
			dataType:"json",
			success : function(data) {
				console.log(data);
				if (data.result == "OK") {
					alert("사용 가능한 닉네임 입니다.");
					confVal.nicname = "Y";
				} else {
					alert("이미 사용중인 닉네임 입니다.");
					confVal.nicname = "N";
				}
			}
		});
	}
}

function modify(){
	var frm = document.signinFrm;
	if(confVal.passwd == "Y" && confVal.nicname == "Y"){
		if(confirm("회원 정보를 수정하시겠습니까?")){
			frm.method="post";
			frm.cmd.value="modify";
//			frm.toUrl.value=hostUrl+"/Calc/Fee";
			frm.action=hostUrl+"/Confirm";
			frm.submit();
		}
	} else if(confVal.passwd == "N"){
		alert("비밀번호가 일치하지 않습니다.\n비밀번호를 확인하십시오.");
	} else if(confVal.nicname == "N"){
		alert("닉네임 중복 확인이 되지 않았습니다.\n닉네임 중복을 확인하십시오.");
	}
}

/**
 * 단체명 선택시 우측에 툴팁 표시.
 */
var grpVal = "N";
function aGrpTitle(){
	var tTitle = $("#grpId option:selected").attr('title');
	$("#grpTitle").html(tTitle);
}
function aGrpDeptTitle(){
	var tTitle = $("#grpDept option:selected").attr('title');
	$("#grpDeptTitle").html(tTitle);
}
function checkGrp(){
	var grpIdVal = $("#grpGrpId").val();
	if(grpIdVal === ""){
		alert("단체 아이디를 입력하세요.");
		$("#grpGrpId").focus();
		return;
	}
	$.ajax({
		type : "GET",
		data : "cmd=checkGrp&grpId=" + grpId,
		url : "/AcAjax",
		dataType:"json",
		success : function(data) {
			console.log(data);
			if (data.result == "OK") {
				alert("사용 가능한 단체 아이디 입니다.");
				grpVal = "Y";
			} else {
				alert("이미 존재하는 아이디 입니다.");
				grpVal = "N";
			}
		}
	});
}
function putGrpId(){
	grpVal = "N";
}
function newGrp(){
	if(grpVal == "N"){
		alert("단체 아이디 중복 확인이 되지 않았습니다.\n단체 아이디 중복을 확인하십시오.");
		return;
	} else {
		if(confirm("입력하신 정보로 단체를 등록하시겠습니까?")){
			var frm = document.newGrpFrm;
			frm.method="POST";
			frm.action="/Confirm";
			frm.submit();
		}
	}
}
