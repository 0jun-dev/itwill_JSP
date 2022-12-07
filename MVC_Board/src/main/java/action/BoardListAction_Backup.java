package action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class BoardListAction_Backup {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
			
			forward = new ActionForward();
			// 포워딩 경로 저장 => "BoardList.bo"
			forward.setPath("board/qna_board_list.jsp");
			// 포워딩 방식 저장 => 디스패치 방식
			forward.setRedirect(false);
		
			return forward; // BoardFronController 로 이동
		
	
	}
}
