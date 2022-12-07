package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardDeleteProAction");
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_pass = request.getParameter("board_pass");
		
		System.out.println(board_num + ", " + board_pass);
		
		try {
			BoardDeleteProService service = new BoardDeleteProService();
			boolean isBoardWriter = service.isBoardWriter(board_pass, board_num);
			System.out.println((isBoardWriter));
			
			// 만약, 게시물 삭제 권한이 없는 경우(= 패스워드 틀렸을시)
			// 자바스크립트를 사용하여 "삭제권한이 없습니다!" 출력 후 이전페이지로 돌아가기
			// 아니면, 삭제작업 수행
			if(!isBoardWriter) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 삭제 권한이 있을 경우
				// BoardDeleteProService 객체의 getBoardRealFile() 메서드 호출
				// 삭제할 파일명을 조회
				// => 파라미터 : 글번호			리턴타입 : String(boardRealFile)
				BoardDetailService service2 = new BoardDetailService();
				BoardBean board = service2.getBoard(board_num, false);
				// => 주의! 레코드 삭제 전 정보 조회를 먼저 수행해야 한다!
				
				boolean isDeleteSuccess = service.removeBoard(board_num);
				
				if(!isDeleteSuccess) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 실패!')");
					out.println("history.back()");
					out.println("</script>");
				} else { // 삭제 성공시
					
					String uploadPath = "upload"; // 업로드 가상 디렉토리 ( 이클립스 )
					String realPath = request.getServletContext().getRealPath(uploadPath); // 업로드 실제 디렉토리 ( 톰캣 )
					
					// 업로드된 실제 파일 삭제
					File f = new File(realPath, board.getBoard_real_file());
					// 해당 디렉토리 및 파일 존재 여부 판별
					if(f.exists()) {
						// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
						f.delete();
					}
					
					forward = new ActionForward();
					forward.setPath("BoardList.bo?pageNum=" + request.getParameter("pageNum"));
					forward.setRedirect(true);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

	
	
}
