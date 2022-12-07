package svc;

import java.sql.Connection;
import java.util.List;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardListService {

	public List<BoardBean> getBoardList(String keyword, int startRow, int listLimit) {
		List<BoardBean> boardlist = null;
		// 공통 작업 1 - connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통 작업 2 - dao 객체 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		// 공통 작업 3 - dao 객체에 con 객체 저장하기
		dao.setConnection(con);
		
		// BoardDAO 객체의 selectBoardList() 메서드를 호출하여 글목록 조회 작업 수행
		// => 파라미터 : 검색어
		boardlist = dao.selecBoardList(keyword, startRow, listLimit);
		
		// 공통 작업 4 - con 반환
		JdbcUtil.close(con);
		
		
		return boardlist;
	}

	public int getBoardListCount(String keyword) {
		int listCount = 0;
		
		// 공통 작업 1 - connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통 작업 2 - dao 객체 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		// 공통 작업 3 - dao 객체에 con 객체 저장하기
		dao.setConnection(con);
		
		// BoardDAO 객체의 selectBoardList() 메서드를 호출하여 글목록 조회 작업 수행
		// => 파라미터 : 검색어
		listCount = dao.selectBoardListCount(keyword);
		
		// 공통 작업 4 - con 반환
		JdbcUtil.close(con);
		
		return 0;
	}

	
	
}
