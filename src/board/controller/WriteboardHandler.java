package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class WriteboardHandler implements CommandHandler {

	// 파일 저장위치
	private static String BOARD_IMAGE_REPO = "C:\\board\\board_image";
	BoardService boardService = BoardServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// get방식으로 접근하면 write.jsp로 이동
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return "/WEB-INF/view/board/write.jsp";
			// post방식으로 접근하면 processSubmit실행
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			// GET, POST 외 다른방식으로 접근할때 응답코드 지정
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return "/WEB-INF/view/member/loginForm.jsp";
		}

	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		int readCount = 0;
		int boardNo = 0;

		// 폼에서 넘어온 값들을 추출하고 업로드 파일을 저장한 후에 폼의 필드 이름과 값 그리고 저장 경로와 파일이름을 맵으로 받음
		Map<String, String> boardMap = upload(request, response);
		Auth auth = (Auth) request.getSession().getAttribute("authUser");
		String memberId = auth.getMemberId();
		String nickName = auth.getNickname();
		String title = boardMap.get("title");
		String content = boardMap.get("content");
		String photo = boardMap.get("photo");
		
//		System.out.println("WriteboardHandler>>>auth.getMemberId();>>>"+auth.getMemberId());

		Board board = new Board(boardNo, memberId, nickName, title, content, photo, readCount);

		// error 결과를 담을 errors 맵 생성
		Map<String, Boolean> errors = new HashMap<>();
		// db에 글 정보 가져가서 insert
		try {
			Map<String, Integer> resultAndBoardNo = boardService.boardWrite(board);
			int result = resultAndBoardNo.get("result");
			boardNo = resultAndBoardNo.get("boardNo");
			
			System.out.println("WriteboardHandler>>>>resultAndBoardNo.get(\"result\")>>>>"+resultAndBoardNo.get("result"));
			System.out.println("WriteboardHandler>>>>resultAndBoardNo.get(\"boardNo\")>>>>"+resultAndBoardNo.get("boardNo"));
			
			// 업로드 파일이 있을 경우 upoad 메소드를 통해 temp에 저장 - 저장된 파일을 다시 boardNo로 생성한 폴더로 이동
			if (photo != null && photo.length() != 0) {
				System.out.println("WriteboardHandler>>>boardNo>>>>"+boardNo);
				File srcFile = new File(BOARD_IMAGE_REPO + "\\" + "temp" + "\\" + photo);
				File destDir = new File(BOARD_IMAGE_REPO + "\\" + boardNo);
				destDir.mkdirs();
				try {
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			if (result > 0) { // result가 0이상이면 글쓰기 성공
				return "/WEB-INF/view/board/writeSuccess.jsp";
			} else { // 회원가입 실패
				errors.put("FailToWriteBoard", true); // 변경실패 에러 입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/board/write.jsp";// 실패 시 다시 폼으로 넘어감
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/WEB-INF/view/board/write.jsp";

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
