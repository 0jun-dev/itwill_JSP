package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import encrypt.MyMessageDigest;
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
		member.setPasswd(request.getParameter("passwd")); // 아래 암호화에서 수행
		member.setEmail(request.getParameter("email1") + "@" + request.getParameter("email2")); // "@" 부분 '@' 가능(? String에 결합되면 String이 되므로)
		member.setGender(request.getParameter("gender"));
//		System.out.println(member);
		
		// -------------------------------------------------------------------------------
		// 패스워드 암호화(해싱) 기능 추가
		// encrypt.MyMessageDigest 클래스 인스턴스 생성
		MyMessageDigest md = new MyMessageDigest("SHA-256");
		// MyMessageDigest 객체의 hashing() 메서드를 호출하여 암호화 수행
		md.hashing(request.getParameter("passwd"));
		// -------------------------------------------------------------------------------
		
		
		MemberJoinProService service = new MemberJoinProService();
		boolean isJoinSuccess = service.joinMember(member);
			
		if(!isJoinSuccess) { // 실패했을 경우
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입 실패!')");
				out.println("history.back();");
				out.println("/<script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // 성공했을 경우
			// 포워딩 정보를 저장할 ActionForward 인스턴스 생성 (forward)
			forward = new ActionForward();
			forward.setPath("MemberJoinResult.me");
			forward.setRedirect(true);
		}
		
		return forward;
		
	}

}
