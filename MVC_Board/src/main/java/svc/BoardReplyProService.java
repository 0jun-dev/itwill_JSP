package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardReplyProService {

	public boolean registReplyBoard(BoardBean board) {
		// 1. 글쓰기 작업요청 처리 요청 결과를 저장할 boolean 타입 변수 선언
				boolean isWriteSeuccess = false;
				
				Connection con = JdbcUtil.getConnection();
				
				// 3. BoardDAO 클래스로부터 BoardDAO 객체 가져오기(공통)
				// => 싱글톤 디자인 패턴으로 구현되어있는 객체를 getInstance() 메서드로 리턴받기
				BoardDAO dao = BoardDAO.getInstance();
				
				// 4. BoardDAO 객체의 setConnection() 메서드를 호출하여 Connection 객체 전달(공통)
				dao.setConnection(con);
				
				
				// 5. BoardDAO 객체의 XXXBoard() 메서드를 호출하여 XXX 작업 수행 요청 및 결과 리턴받기
				//    insertBoard() 메서드를 호출하여 글쓰기 작업 요청 및 결과 리턴받기
				//    => 파라미터 : BoardBean 		리턴타입 : int(insertCount)
				int insertCount = dao.insertReplyBoard(board);
				
				// 6. 작업 처리 결과에 따른 트랜잭션 처리
				if(insertCount > 0) {
					JdbcUtil.commit(con);
					isWriteSeuccess = true;
					
				} else {
					JdbcUtil.rollback(con);
					
				}
				
		 		// 7. Connection 자원 반환(공통)
				JdbcUtil.close(con);
				
				// 작업결과 리턴
				return isWriteSeuccess; // BoardWriteProAction 으로 리턴
			}
		
}
