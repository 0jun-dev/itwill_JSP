package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BoardListAction;
import action.BoardWriteProAction;
import vo.ActionForward;

//@WebServlet("*.bo")
public class BoardFrontController_Backup extends HttpServlet {
	// GET or POST 방식 요청을 공통으로 처리할 doProcess() 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController - doProcess()");
		
		// 서블릿주소 추출
		// 참고 ) 전체 url 가져오기(추출)
//		String requestURL = request.getRequestURL().toString();
//		System.out.println("requestURL : " + requestURL);
		// => 단, 서버마다 IP 주소나 서비스 포트번호가 달라질 수도 있으므로
		//    요청 주소(URL) 전체를 문자열로 판별하는 작업은 효율적이지 못하다.
		// => 공통 부분을 제외한 나머지(=서블릿 주소) 부분만 추출 필요
		// requestURL = requestURL : http://localhost:8080/MVC_Board/*.bo
		
		// 1. 요청 주소 중 URI 부분 (/프로젝트명/서블릿주소) 추출 
//		String requestURI = request.getRequestURI();
//		System.out.println("requestURI : " + requestURI);
		// => 서버정보(프로토콜://서버주소:포트번호)를 제외한 어플리케이션 식별 정보만 추출
		// requestURI = /MVC_Board/*.bo

		// 2. 요청 주소 중 컨텍스트 경로(/프로젝트명) 추출
//		String contextPath = request.getContextPath();
//		System.out.println("contextPath : " + contextPath);
		// => contextPath = /MVC_Board
		
		// 3. 요청 주소 중 서블릿 주소부분(/서블릿주소) 추출
		// => requestURI, contextPath 를 가공하여 추출
		// 1) requestURI 중에서 contextPath 에 해당하느 부분을 널스트링으로 치환(교체)
		//    => String 클래스의 replace() 메서드 활용
//		String command = requestURI.replace(contextPath, "");
//		System.out.println("command : " + command);
		
		// 2) "/서블릿주소" 부분에 해당하는 부분문자열 추출 - String 클래스의 subString() 메서드 활용
		// => "/MVC_Board/*.bo" 주소 중 "/MVC_Board" 부분 추출이 필요하므로
		//    문자열의 길이를 알아낸 후 시작인덱스로 지정하면
		//    해당 인덱스부터 마지막까지 문자열을 추출할 수 있다.
//	    String command = requestURI.substring(contextPath.length());
//		System.out.println("command : " + command);
	    // --------------------- 위의 1 ~ 3 번 과정을 하나의 메서드로 압축하여 제공 - request 객체의 getServletPAth()
		String command = request.getServletPath();
		System.out.println("command : " + command);
		// -----------------------------------------------------------------------------------------------------------
		// 추출 된 서블릿 주소(command)를 if 문을 통해 문자열 비교를 수행하고
		// 각 주소에 따른 액션(작업) 요청
		if(command.equals("/BoardWriteForm.bo")) {
			System.out.println("글쓰기 폼!");
			// 글쓰기 폼을 출력하는 뷰페이지(board/qna_board_write.jsp) 로 이동
			// => 비즈니스 로직(=DB 작업)이 불필요하므로 뷰페이지로 바로 이동
			// => Dispatch 방식 포워딩
			//    (주소표시줄 URL이 변경되지 않고, BoardWriteForm.bo 가 유지됨
			RequestDispatcher dispatcher = request.getRequestDispatcher("board/qna_board_write.jsp");
			dispatcher.forward(request, response);
			
		} else if(command.equals("/BoardWritePro.bo")) {
			System.out.println("글쓰기 작업!");
			
			request.setCharacterEncoding("UTF-8"); // 한글처리
//			String subject = request.getParameter("board_subject");
//			System.out.println("제목 : " + subject);
			BoardWriteProAction action = new BoardWriteProAction();
			ActionForward forward = action.execute(request, response);
			
			// ActionForwad 객체의 포워딩 방식 판별
			if(forward.isRedirect()) { // true : 리다이렉트 방식
				response.sendRedirect(forward.getPath());
			} else { // false : 디스패치 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
			response.sendRedirect("BoardList.bo");
			
		} else if(command.equals("/BoardList.bo")) {
			System.out.println("글목록 작업!");
			
			// BoardListAction 인스턴스 생성 후 execute() 메서드 호출
			// => 파라미터 : request, response 객체 리턴타입 : ActionForward(forward)
			BoardListAction action = new BoardListAction();
			ActionForward forward = action.execute(request, response);
			
			// ActionForwad 객체의 포워딩 방식 판별
			if(forward.isRedirect()) { // true : 리다이렉트 방식
				response.sendRedirect(forward.getPath());
			} else { // false : 디스패치 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
