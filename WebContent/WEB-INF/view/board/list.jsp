<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
<style>
body{font-family: 'Noto Serif KR', serif;
/* background: linear-gradient( to bottom, white , #D19262 ); */
 background: rgba( 209, 146, 98, 0.5 ); 
/* background: linear-gradient( to bottom, rgba( 209, 146, 98, 0.5 ) , #D19262 ); */

}
#header a{
text-decoration: none;
color: black;
}
.container a{
color: black;
}
.container{
text-align: center;
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
.pageNumber{
display: inline-block;
padding: 10px;
font-size: 16px;
color: brown;
}
#selectPageNo{
color: red;
}
#writeButton{
color: white;
}
</style>
</head>
<body>
<section class="notice">
  <div class="page-title">
        <div class="container">
            <h1><a href="${contextPath }/main.do">Dear Diary,   <span id="title_span">당신의 오늘과 함께합니다.</span></a></h1>
        </div>
    </div>
  <!-- board list area -->
    <div id="board-list">
        <div class="container">
            <table class="board-table">
                <thead>
                <tr>
                    <th scope="col" class="th-num">번호</th>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-date">작성자</th>
                    <th scope="col" class="th-date">등록일</th>
                    <th scope="col" class="th-num">조회수</th>
                </tr>
                </thead>
                <tbody>
<c:if test="${boardPage.total == 0}">
	<tr>
		<td colspan="5">게시글이 없습니다.</td>
	</tr>
</c:if>

<c:forEach var="board" items="${boardPage.content}">
	<tr>
		<td>${board.boardNo}</td>
		<td>
		<a href="${contextPath }/board/read.do?boardNo=${board.boardNo}&pageNo=${boardPage.currentPage}&nickName=${board.nickName}">
		<c:out value="${board.title}"/>
		</a>
		</td>
		<td>${board.nickName}</td>
		<td>${board.regDate}</td>
		<td>${board.readCount}</td>
	</tr>
</c:forEach>
    </tbody>
	</table>
<div>


	
	<c:if test="${boardPage.startPage > 5}">
		<p class="pageNumber"><a href="${contextPath }/board/list.do?pageNo=${boardPage.startPage - 5}">[이전]</a></p>
	</c:if>
	<c:forEach var="pNo" 
			   begin="${boardPage.startPage}" 
			   end="${boardPage.endPage}">
		<p class="pageNumber"><a href="${contextPath }/board/list.do?pageNo=${pNo}">[${pNo}]</a></p>
	</c:forEach>
	<c:if test="${boardPage.endPage < boardPage.totalPages}">
		<p class="pageNumber"><a href="${contextPath }/board/list.do?pageNo=${boardPage.startPage + 5}">[다음]</a></p>
	</c:if>
	
</div>
  <!-- board seach area -->
  <div id="board-search">
      <div class="container">
          <div class="search-window">
              <form action="" class="search_form">
                  <div class="search-wrap">
                      <label for="search" class="blind">게시판 내용 검색</label>
                      <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
                      <button type="submit" class="btn btn-dark">검색</button> 
                      
                  </div>
              </form>
              <button type="button" class="btn_right btn_dark_right"><a href="${contextPath }/board/write.do" id="writeButton">글쓰기</a></button>
          </div>
      </div>
  </div>
</section>
</body>
</html>