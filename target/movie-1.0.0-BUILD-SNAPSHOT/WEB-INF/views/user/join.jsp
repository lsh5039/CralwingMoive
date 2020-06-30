<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/bootstrap.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <style>
        /* .help-block 을 일단 보이지 않게 설정 */
        #myForm .help-block{
            display: none;
        }
        /* glyphicon 을 일단 보이지 않게 설정 */
        #myForm .glyphicon{
            display: none;
        }
        .title{text-align:center;}
        #msg{text-align:center; color:red;}
    </style>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	
	<div class="container">
    <h3 class="title">회원가입</h3>
  
    <form:form action ="/join.do" method="post" id="myForm" commandName="vo" onsubmit="return chk()">
        <div class="form-group has-feedback">
            <label class="control-label" for="id">아이디</label>
            <!-- <input class="form-control" type="text" name="id" id="id"/> -->
            <form:input path="id" class="form-control"/>
            <span id="overlapErr" class="help-block">사용할 수 없는 아이디 입니다.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="pw">비밀번호</label>
            <form:input path="pw" class="form-control" type="password"/>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="rePwd">비밀번호 재확인<p id="pwMsg"></p></label>
            <input class="form-control" type="password" name="rePwd" id="rePwd" onkeyup="pwChk()"/>
            <span id="rePwdErr" class="help-block">비밀번호와 일치하지 않습니다. 다시 입력해 주세요.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="email">이메일</label>
            <!-- <input class="form-control" type="text" name="email" id="email"/> -->
            <form:input path="email" class="form-control"/>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="name">이름</label>
            <form:input path="name" class="form-control"/>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        
        <div class="form-group has-feedback">
        	<label class="control-label" for="phone">휴대번호</label>
        	<form:input path="phone" placeholder="휴대번호" class="form-control"  onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
        	<span id="phoneErr" class="help-block">휴대번호를 입력해주세요</span>
        	<span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div>
			<form:radiobutton path="gender" value="M" label="남성"  checked="checked" />
			<form:radiobutton path="gender" value="F" label="여성"/>
		</div>
        <button class="btn btn-primary" type="submit">가입</button>
        </form:form>
        <p id="msg"></p>
	</div>

<script>



	function chk(){
		
		var emailReg=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/g;
		
		
		alert(pwMsgVal);
		var pheonReg = /^[0-9]*$/;
		
		if(myForm.id.value==""){
			msg.innerHTML = "아이디를 입력해주세요";
			return false;
		}else if(myForm.pw.value.length < 4){
			msg.innerHTML = "패스워드를 입력해주세요 최소4글자";
			return false;
		}else if(myForm.email.value.match(emailReg) == null){
			msg.innerHTML = "이메일 양식을 지켜주세요";
			return false;
		}else if(myForm.name.value==""){
			msg.innerHTML = "이름을 입력하세요";
			return false;
		}else if(myForm.phone.value.length!=11){
			msg.innerHTML = "휴대폰 11자리를 입력해주세요 -제외";
			return false;
		}else if(pwMsg.innerText != "패스워드가 일치합니다."){
			msg.innerHTML = "패스워드일치 여부를 확인해주세요"
			return false;
		}
	
	}
	
	function phoneChk(e){
		console.log(e.keyCode);
		if(e.keyCode<48 || e.keyCode>57){
		     e.returnValue=false;
		}

	}
	function pwChk(){
		if(myForm.pw.value != myForm.rePwd.value ){
			pwMsg.innerHTML = "비밀번호가 일치하지 않습니다."
			pwMsg.style.color="red"
		}else{
			pwMsg.innerHTML = "패스워드가 일치합니다."
				pwMsg.style.color="blue"
		}
	}

</script>




</body>
</html>