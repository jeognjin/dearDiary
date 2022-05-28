<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP Board Project by Jeongjin</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/boardList.css" type="text/css" charset="utf-8" />

<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<!-- setting -->
<script src="${pageContext.request.contextPath }/js/write.js"></script>
<link href="${pageContext.request.contextPath }/css/write.css" rel="stylesheet">

  <!-- font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap" rel="stylesheet">
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
</style>
<script type="text/javascript">
   function readURL(input) {
	   //파일 업로드 1장만 가능
      if (input.files && input.files[0]) {
	      var reader = new FileReader();
         reader.readAsDataURL(input.files[0]);
      }
  } 

</script>
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<body>
 <div class="page-title">
        <div class="container">
            <h1><a href="${contextPath }/main.do">Dear Diary,   <span id="title_span">당신의 오늘과 함께합니다.</span></a></h1>
        </div>
    </div>
<c:if test="${errors.FailToWriteBoard}"><p style="color: red;">저장 실패. 다시 시도해주세요.</p></c:if>
  <form name="boardForm" method="post"   action="${contextPath }/board/write.do" enctype="multipart/form-data">
<div class="wrap">
	<div id="tip-title-box">
	   <input id="tip-title" name="title" placeholder="제목을 입력해 주세요">
	</div>
	<div class="tip-title-box">
	 <textarea class="editor-contents" name="content"></textarea>
 </div>
 <div id="fileUpload">
	     <input type="file" name="photo"  onchange="readURL(this);" />
     </div>
     <div id="btn-box">
        <div id="btn-box-center">
            <div id="cancel-btn" ><button type="button" class="btn_right btn_dark_right"><a href="${contextPath }/board/list.do" id="writeButton">취소</a></button></div>
            <div id="submit-btn" ><button type="submit" class="btn_right btn_dark_right">등록</button></div>
        </div>
    </div>
    </div>
  </form>
</body>
</html>