package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import svc.BoardWriteProService;
import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		MemberBean member = new MemberBean();
		member.setName(request.getParameter("name"));
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setEmail(request.getParameter("email") + "@" + request.getParameter("email2"));
		member.setGender(request.getParameter("gender"));
		
		MemberJoinProService service = new MemberJoinProService();
		boolean isJoinSuccess = service.joinMember(member);
			
		if(!isJoinSuccess) { // 실패했을 경우
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글쓰기 실패!')");
				out.println("history.back();");
				out.println("/<script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // 성공했을 경우
			// 포워딩 정보를 저장할 ActionForward 인스턴스 생성 (forward)
			forward = new ActionForward();
			forward.setPath("member/member_join_result.jsp");
			forward.setRedirect(true);
		}
		
		return forward;
		
	}

}
