package action;

import java.io.IOException;

import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDetailAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardDetailAction");
		
		ActionForward forward = null;
		
		// 상세정보 조회에 필요한 글번호 가져오기
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("board_num : " + board_num);
		// => pageNum 파라미터는 혀재 작업에서 실제로 활용되는 데이터가 아니므로
		//	  다음 페이지 포워딩 시 URL 또는 request 객체에 함께 전달하기만 하면 된다!
		// => 또한, Dispatch 방식으로 포워딩 시 URL 이 유지되므로 파라미터값 가져오기 불필요
		
		BoardDetailService service = new BoardDetailService();
		BoardBean board = service.getBoard(board_num, true);
		System.out.println(board);
		
		// 뷰 페이지로 데이터 전달을 위해 request 객체에 저장
		request.setAttribute("board", board);
		
		// ActionForward 객체를 통해 qna_board_view.jsp 페이지 포워딩 설정
		// => URL, Request 객체 유지를 위해 Dispatch 방식 포워딩
		forward = new ActionForward();
		forward.setPath("board/qna_board_view.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
