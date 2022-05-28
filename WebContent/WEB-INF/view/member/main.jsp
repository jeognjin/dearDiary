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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/coda-slider.css" type="text/css" charset="utf-8" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap" rel="stylesheet">

<style>
body{font-family: 'Noto Serif KR', serif;
}
#header a{
text-decoration: none;
color: black;
}
</style>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<div id="slider">
	<div id="tooplate_wrapper">
        <div id="tooplate_sidebar">
            <div id="header">
                <h1><a href="${contextPath }/index.jsp">Dear Diary,</a></h1><br />
            </div>    
            <div id="menu">
                <ul class="navigation">
                <c:if test="${empty authUser}">
                    <li><a href="${contextPath }/member/login.do" class="selected menu_01">로그인</a></li>
                    <li><a href="${contextPath }/member/regist.do" class="menu_02">회원가입</a></li>
                    <li><a href="${contextPath }/board/list.do" class="menu_03">게시판</a></li>
                </c:if>
                <c:if test="${! empty authUser}">
                <%-- ${authUser.nickName}님, 안녕하세요. --%>
                	<li><a href="${contextPath }/member/logout.do" class="selected menu_01">로그아웃</a></li>
                    <li><a href="${contextPath }/member/changeInfo.do" class="menu_02">정보변경</a></li>
                    <li><a href="${contextPath }/board/list.do" class="menu_03">게시판</a></li>
                    <li><a href="${contextPath }/member/delete.do" class="menu_04">회원탈퇴</a></li>
                </c:if>
                </ul>
            </div>
		</div> <!-- end of sidebar -->  
    
       <div id="content">
         
          <div class="scrollContainer">
            <div class="panel" id="home">
              
              <br />
              <br />
                <h2>오늘 하루는 어땠나요?</h2>
				<h2>당신의 오늘을 기록하고 함께 나눠보세요.</h2>
				<br /> <br />
				<h2>Dear Diary,는 익명으로 작성하는 공간입니다.</h2>
				<br />
				<h2>오늘의 감정과 함께 나누고 싶은</h2>
				<h2>이야기들을 적어보세요.</h2>
              </div>
            </div> <!-- end of home -->
</div>

<div id="footer">
    <div id="footer_left">
		Copyright © 2048 <a href="#">JeongJin Lee</a><br />
		Designed by <a href="http://www.tooplate.com">Free HTML Templates</a><br />  
    </div>	
    <div class="cleaner"></div>
</div>

</body>
</html>