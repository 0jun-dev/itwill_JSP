package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardDetailService {
	// 글 상세정보 조회
	// 단, 글번호와 함게 조회수 증가 여부를 파라미터로 전달
	public BoardBean getBoard(int board_num, boolean isUpdateReadcount) {
		BoardBean board = null;
		
		// 공통 작업 1 - connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통 작업 2 - dao 객체 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		// 공통 작업 3 - dao 객체에 con 객체 저장하기
		dao.setConnection(con);
		
		// BoardDAO 의 selectBoard() 메서드를 호출하여 게시물 상세정보 작업수행
		// => 파라미터 : 글번호		리턴타입 : BoardBean(board)
		board = dao.selectBoard(board_num);
		
		// 리턴받은 BoardBean 객체가 null 이 아니고, isUpdateReadcount 가 true 일  경우
		// updateReadcount() 메서드를 호출하여 조회수 증가 작업 수행하고 commit 작업 수행
		// 작업이 성공했을 경우 commit 작업 수행 및 BoardBean 객체의 조회수 값 1 증가 시키기
		// => 파라미터 : 글번호, isUpdateReadcount	리턴타입 : int(updateCount)
		if(board != null && isUpdateReadcount) {
			int updateCount = dao.updateReadcount(board_num);
			
			if(updateCount > 0) {
				JdbcUtil.commit(con);
				
				//만약, 조회수 증가 전 조회 작업을 먼저 수행했을 경우
				// 수동으로  BoardBean 객체의 조회수를 1만큼 증가시켜야함
				board.setBoard_readcount(board.getBoard_readcount() + 1);
			}
			
		}
		
		// 공통 작업 4 - con 반환
		JdbcUtil.close(con);
		
		return board;
	}

	
	
	
}
