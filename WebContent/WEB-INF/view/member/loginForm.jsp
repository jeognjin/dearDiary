<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Board Project by Jeongjin</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="tooplate_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/coda-slider.css" type="text/css" charset="utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/registForm.css" type="text/css" charset="utf-8" />
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
.panel{
text-align: center;
}
.joinForm{
margin-top: 80px
}
.btn{
margin-top: 40px
}
</style>

</head>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<body>
<c:if test="${!empty LoginCheckFilter }">
<script type="text/javascript">
alert("로그인 후에 이용해주세요.");
</script>
</c:if>
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
              
          
<form action="${contextPath }/member/login.do" method="post" class="joinForm">
                                                                                               
      <h2>로그인</h2>
      <div class="textForm">
        <input name="memberId" type="text" class="id" placeholder="아이디<c:if test="${errors.memberId}">를 입력하세요.</c:if>">
        
        </input>
      </div>
      <div class="textForm">
        <input name="password" type="password" class="pw" placeholder="비밀번호<c:if test="${errors.password}">를 입력하세요.</c:if>">
      </div>
       
       <c:if test="${errors.idOrPwNotMatch}"><p style="color: red;">ID 또는 비밀번호가 일치하지 않습니다.</p></c:if>
      <input type="submit" class="btn" value="L O G I N"/>
    </form>


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
