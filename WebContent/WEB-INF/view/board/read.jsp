<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Board Project by Jeongjin</title>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/boardList.css" type="text/css" charset="utf-8" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/write.css" rel="stylesheet">
<style>
body{font-family: 'Noto Serif KR', serif;
 background: rgba( 209, 146, 98, 0.5 ); 
 text-align: center;
}
.container a{
color: black;
text-decoration: none;
}
.container{
text-align: center;
margin-top: 50px;
display: inline-block;
}
.container h1{
font-size: 30px;
text-align: center;
border: 2px outset brown;
outline: 2px solid black;
outline-offset: 2px;
padding: 10px 25px;
display: inline-block;
border-radius: 15px;
margin-bottom: 10px;
margin-top: 0px;

}
#title_span{
font-size: 18px;
color: brown;
}
.panel{
margin-bottom: 0px;
}
.buttonWrap{
text-align: right;
max-width: 90%;
}

.page-title{
margin-bottom: 40px;
}
#writeButton{
color: white;
}
#btn-box-center{
margin-bottom: 40px;
}
#btn-box{
margin-top: 20px;
}
#fileUpload{
	width:70%; 
   text-align: left;
	display: inline-block;
}
.wrap{
text-align: center;
}
#submit-btn{
width: 120px;
}
#cancel-btn{
width: 140px;
}
.boardInfoBar_p{
display: inline-block;
padding: 0 20px;
}

</style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >	 
	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }  
 </script>
</head>
<body>

<section class="notice">
  <div class="page-title">
      <div class="container">
          <h1><a href="${contextPath }/main.do">Dear Diary,   <span id="title_span">당신의 오늘과 함께합니다.</span></a></h1>
      </div>
  </div>
  
    <form name="boardForm" method="post"   action="${contextPath }/board/write.do" enctype="multipart/form-data">
<div class="wrap">
	<div id="tip-title-box">
	   <input id="tip-title" name="title" value="${board.title }"  disabled>
	</div>
	
	 <div class="tip-title-box">
	  <div class="boardInfoBar">
	  	<p class="boardInfoBar_p">글번호: ${board.boardNo }</p>
	  	<p class="boardInfoBar_p">작성자: <a href="#">${board.nickName }</a></p>
	  	<p class="boardInfoBar_p">조회: ${board.readCount }</p>
	  	<p class="boardInfoBar_p">작성날짜: ${board.regDate }</p>
  	</div>
  </div>
	
	<div class="tip-title-box">
	 <textarea class="editor-contents" name="content" disabled>${board.content }</textarea>
 </div>
 
 <div id="fileUpload">
	<input  type= "hidden"   name="originalFileName" value="${board.photo }" />
    <img src="${contextPath}/download.do?boardNo=${board.boardNo}&photo=${board.photo}" id="preview"  />
 </div>
 
     <div id="btn-box">
        <div id="btn-box-center">
            <div id="cancel-btn" ><button type="button" class="btn_right btn_dark_right"><a href="${contextPath }/board/list.do" id="writeButton">글목록</a></button></div>
            <div id="cancel-btn" ><button type="button" class="btn_right btn_dark_right"><a href="${contextPath }/board/write.do" id="writeButton">글쓰기</a></button></div>
            <div id="submit-btn" ><button type="button" class="btn_right btn_dark_right"><a href="${contextPath }/board/modify.do?boardNo=${board.boardNo}&pageNo=${boardPage.currentPage}&nickName=${board.nickName}" id="writeButton">수정</a></button></div>
            <div id="submit-btn" ><button type="button" class="btn_right btn_dark_right"><a href="${contextPath }/board/delete.do?boardNo=${board.boardNo}" id="writeButton">삭제</a></button></div>
        </div>
    </div>
    </div>
  </form>
  
  
</section>


</body>
</html>