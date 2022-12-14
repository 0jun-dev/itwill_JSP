package action;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardWriteProAction");
		
		ActionForward forward = null;
		
		try {
			//Multipart Request	
			String uploadPath = "upload"; // 업로드 가상 디렉토리 ( 이클립스 )
			String realPath = request.getServletContext().getRealPath(uploadPath); // 업로드 실제 디렉토리 ( 톰캣 )
			System.out.println("실제 업로드 경로 : " + realPath);
			// 실제 업로드 경로 : D:\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\ upload
			int fileSize = 1024 * 1024 * 10 ;
			
			// -----------------------------------------------------------------------------------------
			// 게시물 작성자(= 클라이언트) 의  IP 주소를 얻어와야 할 경우
			String writerIpAddr = request.getRemoteAddr();
			System.out.println("작성자 IP 주소 : " + writerIpAddr);
			// -----------------------------------------------------------------------------------------
			
			// 파일 업로드 처리(enctype="multipart/form-data") 를 위해
			// MultipartRequest 객체 생성 => cos.jar 라이브러리 추가
			MultipartRequest multi = new MultipartRequest(
					request, // 1) 실제 요청 정보(파라미터)가 포함된 request 객체
					realPath, //
					fileSize, //
					"UTF-8", // 
					new DefaultFileRenamePolicy() // 5) 중복파일명 처리 객체
			);
			
			// 전달받은 파라미터 데이터를 BoardBean 클래스 인스턴스 생성 후 저장
			BoardBean board = new BoardBean();
			board.setBoard_name(multi.getParameter("board_name"));
			board.setBoard_pass(multi.getParameter("board_pass"));
			board.setBoard_subject(multi.getParameter("board_subject"));
			board.setBoard_content(multi.getParameter("board_content"));
			
//			System.out.println(board);
			
			// 파일명은 getParameter() 로 단순 처리 불가능
//			System.out.println(multi.getOriginalFileName("board_file")); // 실제 파일명
//			System.out.println(multi.getFilesystemName("board_file")); // 중복처리된 파일명
			board.setBoard_file(multi.getOriginalFileName("board_file"));
			board.setBoard_real_file(multi.getFilesystemName("board_file"));
//			System.out.println(board);
			
			// ----------------------------------------------------------------------------------
			// 파라미터명이 다른 복수개의 파일이 전달될 경우 복수개의 파라미터 처리 방법
			// 1) 파일에 대한 파라미터명을 관리하는 객체를 통해 파라미터 목록 가져오기 (반복)
//			Enumeration e = multi.getFileNames();
//			// 2) while 문을 사용하여 Enumeration 객체의 hasMoreElement() 메서드가
//			//    true 일 동안(다음 요소가 존재할 동안) 반복
//			while(e.hasMoreElements()) { // true일 때
//				// 3) nextElement() 메서드를 호출하여 다음 요소(파라미터 이름) 가져오기
//				// => 리턴타입이 Object 이므로 문자열로 변환
//				String fileElement = e.nextElement().toString();
//				System.out.println(fileElement);
//				System.out.println("원본 파일명 : " + multi.getOriginalFileName(fileElement)); // 실제 파일명
//				System.out.println("실제 파일명 : " + multi.getFilesystemName(fileElement)); // 중복처리된 파일명
//			}
			// -----------------------------------------------------------------------------------
			// BoardWriteProService 클래스 인스턴스 생성 후
			// registBoard() 메서드를 호출하여 글쓰기 작업 요청
			// => 파라미터 : BoardBean 객체		리턴타입 : boolean(isWriteSuccess)
			BoardWriteProService service = new BoardWriteProService();
			boolean isWriteSuccess = service.registBoard(board);
			
			// 글쓰기 요청 처리 결과 판별
			if(!isWriteSuccess) { // false?
				// 업로드된 실제 파일 삭제
				File f = new File(realPath, board.getBoard_real_file());
				// 해당 디렉토리 및 파일 존재 여부 판별
				if(f.exists()) {
					// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				}
				// 자바스크립트를 사용하여 "글쓰기실패" 출력 후 이전페이지로 이동
				// => 웹브라우저로 HTML 태그 등을 내보내기(출력) 위한 작업 수행
				//    (자바 클래스 내에서 출력스트림을 활용하여 HTML 태그 출력해야함)
				// => 응답 데이터 생성을 위해 응답 객체인 response 객체 활용
				// 1) 출력할 HTML 형식에 대한 문서 타입(contentType) 설정
				//    => 응답 데이터의 타입으로 HTML 태그가 사용됨을 클라이언트에게 알려줌
				//    => response 객체의 setContentType() 메서드를 호출하여 문서 타입 전달
				//       (jsp 파일 최상단의 page 디렉티브 내의 contentType= 항목 활용)
				response.setContentType("text/html; charset=UTF-8");
				
				// 2) 자바 코드를 사용하여 HTML 태그 등을 출력(전송)하려면
				//    java.io.PrintWriter 객체가 필요함(= 출력스트림을 사용할 객체)
				//    => response 객체의 getWriter() 메서드를 호출하여 얻어올 수 있다.
				PrintWriter out = response.getWriter();
				
				// 3) PrintWriter 객체의 print() 또는 prinln() 메서드를 호출하여
				//    파라미터로 HTML 태그 등의 코드를 문자열 형태로 전달
				out.println("<script>");
				out.println("alert('글쓰기 실패!')");
				out.println("history.back();");
				out.println("/<script>");
				// ActionForward 객체 생성 x - null 값 나옴
				
			} else { // true?
				// 포워딩 정보 저장을 위해 ActionForward 객체 생성 후
				// 경로 : BoardList.bo, 포워딩 방식 : Redirect
				forward = new ActionForward();
				forward.setPath("BoardList.bo");
				forward.setRedirect(true);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
