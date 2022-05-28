<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>JSP Board Project by Jeongjin</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="tooplate_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/coda-slider.css" type="text/css"
	charset="utf-8" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap" rel="stylesheet">
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<style>
	body {
		font-family: 'Noto Serif KR', serif;
	}
	
	#header a {
		text-decoration: none;
		color: black;
	}
	.indexImage{
	width: 400px;
	border: 3px solid #70431F;
	border-radius: 25px;
	-moz-border-radius: 25px;
	-khtml-border-radius: 25px;
	-webkit-border-radius: 25px;
	}
	.panel{
	text-align: center;
	}
</style>
</head>
<body>
<div id="tooplate_wrapper">
	<div id="tooplate_sidebar">
		<div id="header">
			<h1>
				<a href="${contextPath }/index.jsp">Dear Diary,</a>
			</h1>
			<br />
		</div>
		<div id="menu">
			<ul class="navigation">
				<li><a href="${contextPath }/main.do" class="selected menu_01">시작하기</a></li>
			</ul>
		</div>
	</div>
	<!-- end of sidebar -->

	<div id="content">
		
	<div class="scrollContainer">
		<div class="panel" id="home">
			
			<br /><br />
			<img src="${contextPath }/images/indexImage.jpg" alt="당신의 일상과 함께합니다." class="indexImage"/>
				<h2>당신의 일상과 함께 합니다.</h2>
				
			</div>
		</div>
		<!-- end of home -->
	</div>
	<div id="footer">
		<div id="footer_left">

			Copyright © 2048 <a href="#">JeongJin Lee</a><br /> Designed by
			<a href="http://www.tooplate.com">Free HTML Templates</a><br />

		</div>
		<div class="cleaner"></div>
	</div>
</body>
</html>