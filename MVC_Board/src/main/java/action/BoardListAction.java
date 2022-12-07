package action;

import java.util.Iterator;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardListProAction");
		
		ActionForward forward = null;
		
		// BoardListService 객체를 통해 게시물 목록 조회 후
		// 조회 결과(List 객체)를 request 객체를 통해 qna_board_list.bo 에 전달
		// ---------------------------------------------------------------------
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한

		// 2. 현재 페이지 번호 설정(pageNum 파라미터 사용)
		// => pageNum 파라미터가 존재하면 해당 값을 저장하고, 아니면 기본 값 1 사용
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		// System.out.println(pageNum);

		int startRow = (pageNum - 1) * listLimit; // 시작 조회

		//파라미터로 전달받은 검색어 가져와서 변수에 저장
		String keyword = request.getParameter("keyword");

		//만약, 전달받은 검색어가 NULL 이면 "" 으로 변경(일반 목록일 경우 )
		if(keyword == null) {
			keyword = "";
		}
		
		// ----------------------------------------------------------------------------
		// BoardListService 클래스 인스턴스 생성
		BoardListService service = new BoardListService();
		// BoardListService 객체의 getBoardList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : 검색어, 시작행번호, 목록갯수 	리턴타입 : List<BoardBean>(BoardList)
		List<BoardBean> boardList = service.getBoardList(keyword, startRow, listLimit);
		System.out.println(boardList);
		
		// -------------------------------------------------------------------------------
		// 페이징 처리에 사용 될 게시물 목록 갯수 조회
		// BoardListService - getBoardListCount() 메서드를 호출하여 게시물 목록 갯수 조회
		// => 파라미터 : 검색어, 시작행번호, 목록갯수 	리턴타입 : int(listCount)
		// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
		int listCount = service.getBoardListCount(keyword);
		System.out.println(listCount);
		
		// System.out.println("총 게시물 수 : " + listCount);
		// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
		int pageListLimit = 3; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
		
		// 3. 전체 페이지 목록 수 계산
		// => 전체 게시물 수를 페이지 당 게시물 목록 수로 나눈 몫 계산
		// 삼항연산자(= 조건연산자)를 활용하여 동일한 계산 수행 
		// => 나눗셈 결과에 추가로 1 페이지를 더할지 말지 판별하여 다른값 덧셈
		int maxPage = listCount / listLimit + (listCount % listLimit == 0 ? 0 : 1); 
		// 위 if문과 동일한 연산결과 출력
//		 			System.out.println("전체 페이지 수 : " + maxPage);
		
		// 4. 시작페이지번호 계산
		// => (현재페이지번호 - 1) / 페이지목록 갯수 * 페이지목록갯수 +1
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		// 5. 끝 페이지 번호 계산
		// => 시작페이지 + 페이지 목록 갯수 -1
		int endPage = startPage + pageListLimit - 1;
		
		// 6. 만약 , 끝 페이지 번호(endPage) 가 전체(최대) 페이지번호(maxPage) 보다
		//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// PageInfo 객체 생성 후 페이징 정보 저장
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		// -------------------------------------------------------------------------
		// 글목록(List 객체)과 페이징처리정보를 request 객체에 저장 - setAttribute()
		request.setAttribute(keyword, pageInfo);
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageInfo", pageInfo);
		
		// Actionforward 객체 생성 후 board/qna_board_list.jsp 페이지로 포워딩 설정
		// => URL 및 request 객체 유지 : Dispatch 방식
		forward = new ActionForward();
		forward.setPath("board/qna_board_list.jsp");
		forward.setRedirect(false); // 생략 가능
		
		
		
		
		
		return forward;
	}

}
