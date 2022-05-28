package board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import auth.dto.Auth;
import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import common.CommandHandler;

public class ModifyboardHandler implements CommandHandler {

	// 파일 저장위치
	private static String BOARD_IMAGE_REPO = "C:\\board\\board_image";
	private BoardService boardService = BoardServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// get방식으로 접근하면 작성자 확인페이지로 이동
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processWriterCheck(request, response);
			// post방식으로 접근하면 processSubmit실행
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			// GET, POST 외 다른방식으로 접근할때 응답코드 지정
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return "/WEB-INF/view/member/loginForm.jsp";
		}

	}

	private String processWriterCheck(HttpServletRequest request, HttpServletResponse response) {
		String boardNo = request.getParameter("boardNo");
		// 세션에서 로그인정보 추출
		Auth auth = (Auth) request.getSession().getAttribute("authUser");
		// 글번호로 게시글 정보 가져오기
		Board board;
		try {
			board = boardService.viewBoard(Integer.parseInt(boardNo));
			String nickNameOfGetAuth = board.getNickName(); // db에서 가져온 정보에서 닉네임 추출
			if (nickNameOfGetAuth.equals(auth.getNickname())) {
				request.setAttribute("board", board);
				return "/WEB-INF/view/board/modify.jsp"; // 정보가 일치하면 수정페이지로 이동
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "${pageContext.request.contextPath }/board/read.do?boardNo=${board.boardNo}&pageNo=${boardPage.currentPage}&nickName=${board.nickName}";
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		Auth auth = (Auth) request.getSession().getAttribute("authUser");
		int readCount = 0;
		Map<String, String> boardMap = upload(request, response);
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String memberId = auth.getMemberId();
		String nickName = auth.getNickname();
		String title = boardMap.get("title");
		String content = boardMap.get("content");
		String photo = boardMap.get("photo");
		// 업데이트 할 정보 객체에 저장
		Board board = new Board(boardNo, memberId, nickName, title, content, photo, readCount);

		// board객체에 저장한 정보들고 db가서 정보 업데이트
		try {
			int result = boardService.modifyBoard(board);
			if (result > 0) { // result가 0이상이면 업데이트 성공
				// 사진 업로드 여부 확인 후 기존 저장이미지 삭제하고 새로 업로드 된 사진 저장
				if (photo != null && photo.length() != 0) {
					String originalFileName = boardMap.get("originalFileName");
					File srcFile = new File(BOARD_IMAGE_REPO + "\\" + "temp" + "\\" + photo);
					File destDir = new File(BOARD_IMAGE_REPO + "\\" + boardNo);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					File oldFile = new File(BOARD_IMAGE_REPO + "\\" + boardNo + "\\" + originalFileName);
					oldFile.delete();
					return "/WEB-INF/view/board/updateSuccess.jsp?boardNo=" + boardNo;
				} else {
					return "/WEB-INF/view/board/list.jsp"; // 실패 시 list로 보내기
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/WEB-INF/view/board/list.jsp";

	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
		// 폼에서 전달된 파라미터 이름을 키로, 값을 값으로 저장하기 위한 맵
		Map<String, String> boardMap = new HashMap<String, String>();
		File currentDirPath = new File(BOARD_IMAGE_REPO); // 사진 최종 저장 경로
		String encoding = "utf-8";

		// 사진 저장해주는 라이브러리 객체 생성(아파치 commons-fileupload-1.3.3.jar) 및 설정
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);

		// 파일 업로드용 객체에 설정 파일 등록
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				// 리스트에서 꺼낸 form 요소가 multipart 요소가 아니면(일반 폼필드면)
				if (fileItem.isFormField()) {
					boardMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {

					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						boardMap.put(fileItem.getFieldName(), fileName);
						// 여기까지 폼에서 넘어온 값들 맵에 저장

						// 그림 파일 임시 저장 경로
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						// 그림 파일을 임시 저장 경로에 저장
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 폼에서 넘어온 매개변수들을 저장한 맵 반환
		return boardMap;
	}

}
