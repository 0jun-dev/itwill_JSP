package action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class BoardWriteProAction_Backup {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
	String subject = request.getParameter("board_subject");
	String content = request.getParameter("board_content");
	System.out.println("제목 : " + subject);
	System.out.println("내용 : " + content);
	
	
	// 글쓰기 비즈니스 로직을 수행했다고 가정
	boolean isWriteSuccess = true;
		
	// 글쓰기 작업 결과 판별
	if(!isWriteSuccess) { // 실패했을 경우
		
	} else { // 성공했을 경우
		// 포워딩 정보를 저장할 ActionForward 인스턴스 생성 (forward)
		forward = new ActionForward();
		// 포워딩 경로 저장 => "BoardList.bo"
		forward.setPath("BoardList.bo");
		// 포워딩 방식 저장 => Redirect 방식
		forward.setRedirect(true);
	}
	
	return forward; // BoardFronController 로 이동
	
	
	}
}
