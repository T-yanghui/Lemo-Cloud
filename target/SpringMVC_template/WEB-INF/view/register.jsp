<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="Kodinger">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>My Login Page &mdash; Bootstrap 4 Login Page Snippet</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="static/css/my-login.css">

</head>
<body class="my-login-page">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="static/img/logo.jpg" alt="logo">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Register</h4>
							<h4 class="card-title">${msg}</h4>
							<form method="POST" class="my-login-validation" novalidate="" action="register">

<!--								<div class="form-group">-->
<!--									<label for="name">Name</label>-->
<!--									<input id="name" type="text" class="form-control" name="name" required autofocus>-->
<!--									<div class="invalid-feedback">-->
<!--										What's your name?-->
<!--									</div>-->
<!--								</div>-->

								<div class="form-group">
									<label for="email">E-Mail Address</label>
									<input id="email" type="email" class="form-control" name="username"value="${username}" required>
									<div class="invalid-feedback">
										Your email is invalid
									</div>
									<button id="verification" style="text-align: right">
										Email Verification
									</button>
								</div>

								<div class="form-group">
									<label for="verification_code">Verification Code</label>
									<input id="verification_code"  class="form-control" name="verificationCode" required pattern="[0-9]{6}">
									<div class="invalid-feedback">
										Verification code is Invalid
									</div>
								</div>

								<div class="form-group">
									<label for="password">Password</label>
									<input id="password" type="password" class="form-control" name="password" required data-eye>
									<div class="invalid-feedback">
										Password is required
									</div>
								</div>

								<div class="form-group">
									<div class="custom-checkbox custom-control">
										<input type="checkbox" name="agree" id="agree" class="custom-control-input" required="">
										<label for="agree" class="custom-control-label">I agree to the <a href="Condition">Terms and Conditions</a></label>
										<div class="invalid-feedback">
											You must agree with our Terms and Conditions
										</div>
									</div>
								</div>

								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block">
										Register
									</button>
								</div>
								<div class="mt-4 text-center">
									Already have an account? <a href="login.jsp">Login</a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer">
						Copyright &copy; 2017 &mdash; Your Company 
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	var iTime=59;
	var Account;
	$("#verification").click(function() {
	var  email= $("#email").val();
	console.log(email);
	if (email != "") {
	$.ajax({
	url : "./registerVerification",  //发送请求
	type : "post",
	data : {
	"username":email
	},
	success : function(res) {
		alert(res);
		RemainTime();
	}
	});
	} else {
	alert("Input your email");
	return false;
	}
	});

	function RemainTime() {
		document.getElementById('verification').disabled = true;
		var iSecond, sSecond = "", sTime = "";
		if (iTime >= 0) {
			iSecond = parseInt(iTime % 60);
			iMinute = parseInt(iTime / 60)
			if (iSecond >= 0) {
				if (iMinute > 0) {
					sSecond = iMinute + "m" + iSecond + "s";
				} else {
					sSecond = iSecond + "s";
				}
			}
			sTime = sSecond;
			if (iTime == 0) {
				clearTimeout(Account);
				sTime = 'Email Verification';
				iTime = 59;
				document.getElementById('verification').disabled = false;
			} else {
				Account = setTimeout("RemainTime()", 1000);
				iTime = iTime - 1;
			}
		} else {
			sTime = 'No Countdown';
		}
		document.getElementById('verification').innerHTML = sTime;
	}
	</script>


<%--	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="static/js/my-login.js"></script>
	</body>
</html>