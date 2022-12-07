package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;

public class BoardDeleteProService {
	

	public boolean isBoardWriter(String board_pass, int board_num) {
		boolean isBoardWriter = false;
		
		// 공통 작업 1 - connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통 작업 2 - dao 객체 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		// 공통 작업 3 - dao 객체에 con 객체 저장하기
		dao.setConnection(con);
		
		// BoardDAO 객체의 isBoardWriter() 메서드를 호출하여 패스워드 확인 작업 수행
		isBoardWriter = dao.isBoardWriter(board_num, board_pass);
		
		// 공통 작업 4 - con 반환
		JdbcUtil.close(con);
		
		return isBoardWriter;
	}

	public boolean removeBoard(int board_num) {
		boolean isDeleteSuccess = false;
		
			// 공통 작업 1 - connection 객체 가져오기
			Connection con = JdbcUtil.getConnection();
			// 공통 작업 2 - dao 객체 가져오기
			BoardDAO dao = BoardDAO.getInstance();
			// 공통 작업 3 - dao 객체에 con 객체 저장하기
			dao.setConnection(con);
			
			int deleteCount = dao.deleteBoard(board_num);
			
			// 리턴받은 결과를 판별하여 commit, rollback
			if(deleteCount > 0) {
				JdbcUtil.commit(con);
				isDeleteSuccess = true;
			} else {
				JdbcUtil.rollback(con);
			}
			
			// 공통 작업 4 - con 반환
			JdbcUtil.close(con);
			
			return isDeleteSuccess;
		
	}

	
	
}
