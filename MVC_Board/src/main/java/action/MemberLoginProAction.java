package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			MemberBean member = new MemberBean();
			member.setId(request.getParameter("id"));
			member.setPasswd(request.getParameter("passwd"));
//		System.out.println(member);
			
			// MemberLoginProService - loginMember()
			// => 파라미터 : MemberBean 객체    리턴타입 : int(loginResult)
			MemberLoginProService service = new MemberLoginProService();
			int loginResult = service.loginMember(member);
			
			// int 타입으로 리턴받는 경우 : 아이디 틀림(-1), 패스워드 틀림(0), 로그인 성공(1)
			if(loginResult == -1) { // 아이디 틀림
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 아이디!')");
				out.println("history.back()");
				out.println("</script>");
			} else if(loginResult == 0) { // 패스워드 틀림
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('패스워드 틀림!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // else if(loginResult == 1) 과 동일 = 로그인 성공
				// 세션 객체에 로그인 성공한 아이디를 "sId" 라는 속성명으로 저장
				// => 단, 서블릿 클래스에서 세션 객체에 직접 접근이 불가능함(내장 객체가 없음)
				//    따라서, request 객체로부터 세션 객체를 얻어와야 함 = getSession() 메서드
				//    (리턴타입 HttpSession 타입)
				HttpSession session = request.getSession();
				session.setAttribute("sId", member.getId());
				
				// 메인페이지로 리다이렉트
				forward = new ActionForward();
				forward.setPath("./");
				forward.setRedirect(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}







