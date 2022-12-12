package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		// 변수 지정
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberLoginProService service = new MemberLoginProService();
		int isLoginSuccess = service.isRightUser(id, passwd);
		
		try {
			if(isLoginSuccess > 0) {
				forward = new ActionForward();
				forward.setPath("./");
				forward.setRedirect(true);
				System.out.println("로그인 성공");
				HttpSession session = request.getSession();
				session.setAttribute("sId", id);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패!<br>"
						+ "비밀번호 또는 아이디를 다시 한 번 확인하시기 바랍니다.')");
				out.println("history.back();");
				out.println("/<script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return forward;
	}
	
}
