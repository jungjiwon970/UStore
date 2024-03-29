<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--
Author: Keenthemes
Product Name: Craft 
Product Version: 1.1.4
Purchase: https://themes.getbootstrap.com/product/craft-bootstrap-5-admin-dashboard-theme
Website: http://www.keenthemes.com
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Dribbble: www.dribbble.com/keenthemes
Like: www.facebook.com/keenthemes
License: For each use you must have a valid license purchased only from above link in order to legally use the theme for your project.
-->
<html lang="en">
	<!--begin::Head-->
	<head>
<base href="../../" />
<title>Craft | Bootstrap 5 HTML Admin Dashboard Theme - Craft by KeenThemes</title>
<meta charset="utf-8" />
<meta name="description" content="Craft admin dashboard live demo. Check out all the features of the admin panel. A large number of settings, additional services and widgets." />
<meta name="keywords" content="Craft, bootstrap, bootstrap 5, admin themes, dark mode, free admin themes, bootstrap admin, bootstrap dashboard" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="canonical" href="https://preview.keenthemes.com/craft" />
<link rel="shortcut icon" href="resource/assets/media/logos/favicon.ico" />
	<!--begin::Fonts(mandatory for all pages)-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700" />
<link href="resource/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css" />
<link href="resource/assets/css/style.bundle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>// Frame-busting to prevent site from being loaded within a frame without permission (click-jacking) if (window.top != window.self) { window.top.location.replace(window.self.location.href); }</script>
<style type="text/css">
	.global-validation{
		color: red;
		visibility: hidden;
	}
	.field-validation{
		color: red;
		visibility: hidden;
	}
</style>
</head>
<!--end::Head-->
<!--begin::Body-->
<body id="kt_body" class="auth-bg">
	<!--begin::Theme mode setup on page load-->
	<script>var defaultThemeMode = "light"; var themeMode; if ( document.documentElement ) { if ( document.documentElement.hasAttribute("data-bs-theme-mode")) { themeMode = document.documentElement.getAttribute("data-bs-theme-mode"); } else { if ( localStorage.getItem("data-bs-theme") !== null ) { themeMode = localStorage.getItem("data-bs-theme"); } else { themeMode = defaultThemeMode; } } if (themeMode === "system") { themeMode = window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light"; } document.documentElement.setAttribute("data-bs-theme", themeMode); }</script>
	<div class="d-flex flex-column flex-root">
		<div class="d-flex flex-column flex-lg-row flex-column-fluid">
			<div class="d-flex flex-column flex-lg-row-auto w-xl-600px positon-xl-relative" style="background: #fffff8;">
				<!--begin::Wrapper-->
				<div class="d-flex flex-column position-xl-fixed top-0 bottom-0 w-xl-600px scroll-y">
					<!--begin::Header-->
					<div class="d-flex flex-row-fluid flex-column text-center p-5 p-lg-10 pt-lg-20">
						<a href="index.go" class="py-2 py-lg-20">
							<img alt="Logo" src="resource/assets/media/logos/mainLogo.svg" class="h-60px h-lg-70px" />
						</a>
						<h1 class="d-none d-lg-block fw-bold fs-2qx pb-5 pb-md-10" style="color: #255000;">어서오세요 UStore 입니다.</h1>
						<p class="d-none d-lg-block fw-semibold fs-4" style="color: #255000;">UStore는 임직원분들의 행복한 하루와 안전한 일자리를 기원합니다.
						<br />오늘도 안전근무 유의해주시기 바랍니다.</p>
					</div>
					<div class="d-none d-lg-block d-flex flex-row-auto bgi-no-repeat bgi-position-x-center bgi-size-contain bgi-position-y-bottom min-h-100px min-h-lg-350px" style="background-image: url(resource/assets/media/illustrations/sigma-1/17.png)"></div>
				</div>
			</div>
			<div class="d-flex flex-column flex-lg-row-fluid py-10">
				<div class="d-flex flex-center flex-column flex-column-fluid">
					<div class="w-lg-550px p-10 p-lg-15 mx-auto">
						<form class="form w-100" novalidate="novalidate" method="post" action="/main/pwReset" id="kt_new_password_form">
							<div class="text-center mb-10">
								<h1 class="text-gray-900 mb-3">비밀번호 재설정</h1>
								<p class="global-validation">개인정보를 다시 확인해주세요</p>
							</div>
							<div class="mb-10 fv-row">
								<div class="mb-1">
									<label class="form-label fw-bold text-gray-900 fs-6">사원번호</label>
									<div class="position-relative mb-3">
										<input class="form-control form-control-lg form-control-solid" type="text" placeholder="사원번호" name="empIdx" autocomplete="off" />
										<p class="field-validation">사원번호를 입력해주세요</p>
									</div>
								</div>
							</div>
							<div class="mb-10 fv-row">
								<div class="mb-1">
									<!--begin::Label-->
									<label class="form-label fw-bold text-gray-900 fs-6">생년월일</label>
									<div class="position-relative mb-3">
										<input class="form-control form-control-lg form-control-solid" oninput="oninputBrith(this)" type="text" placeholder="생년월일 ex) 19990102" name="empBirth" autocomplete="off" />
										<p class="field-validation">생년월일을 입력해주세요</p>
										<p class="field-validation brith">생년월일을 제대로 입력해주세요</p>
									</div>
								</div>
							</div>
							
						</form>
						<div class="text-center">
								<button type="button" id="kt_new_password_submit1" class="btn btn-lg btn-primary fw-bold">
									<span class="indicator-label">임시 비밀번호 발급</span>
									<span class="indicator-progress">Please wait... 
									<span class="spinner-border spinner-border-sm align-middle ms-2"></span></span>
								</button>
						</div>
						<div class="text-center temp-pw" style="margin-top: 50px; display: none">
							<h3 class="mb-8">임시 비밀번호</h3>
							<div class="position-relative mb-3">
								<p class="temp-pw"></p>
							</div>
						</div>
					</div>
				</div>
				<div class="d-flex flex-center flex-wrap ">
					<div>
						<button type="button" id="go-login" class="btn btn-lg btn-primary fw-bold">로그인 하러 가기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>var hostUrl = "/";</script>
	<script src="resource/assets/plugins/global/plugins.bundle.js"></script>
	<script src="resource/assets/js/scripts.bundle.js"></script>
</body>
<script type="text/javascript">


$(function(){
	let msg = '${msg}'
	if(msg == 'fail'){
		console.log(msg);
		$('.global-validation').css('visibility', 'visible');
	}else{
		console.log(msg);
		$('div.temp-pw').css('display', 'block');
		$('p.temp-pw').html(msg);
	}
})

$('#go-login').on('click', function(){
	location.href = '/'
});


$('#kt_new_password_submit1').on('click', function(){
	if(validation() == true){
		$('form').submit();					
	}
})

function validation(){
	let id = $('input[name=empIdx]').val();
	let pw = $('input[name=empBirth]').val();
		
	if(id == '' && pw ==''){
		$('input[name=empBirth]').next().css('visibility', 'visible');
		console.log($('input[name=empIdx]').next());
		$('input[name=empIdx]').next().css('visibility', 'visible');
		return false;
	}
	
	if(id == ''){
		$('input[name=empIdx]').next().css('visibility', 'visible');
		return false;
	}
	
	if(pw == ''){
		$('input[name=empBirth]').next().css('visibility', 'visible');
		return false;
	}
	
	return true;
}

function oninputBrith(target) {
	target.value = target.value
		.replace(/[^0-9]/g, '')
}
</script>
</html>