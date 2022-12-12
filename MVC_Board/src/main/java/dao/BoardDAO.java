package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.BoardBean;

// 실제 비즈니스 로직을 수행하는 BoarDAO 클래스 정의
// => 각 Service 클래스 인스턴스에서 BoardDAO 인스턴스에 접근 시 고유 데이터가 불필요하므로
//    BoardDAO 인슽너스는 애플리케이션에서 단 하나만 생성하여 공유해도 된다.
//    따라서, 싱글톤 디자인 패턴을 적용하여 클래슬르 정의하면 메모리 낭비를 막을 수 있다.
public class BoardDAO {
	// ---------------- 싱글톤 디자인 패턴을 활용한 BoardDAO 인스턴스 생성 작업 ---------------
	// 1. 외부에서 인스턴스 생성이 불가능하도록 생성자를 private 접근제한자로 선언
	// 2. 자신의 클래스 내에서 직접 인스턴스를 생성하여 멤버변수에 저장
	//    => 인스턴스 생성 없이도 클래스가 메모리에 로딩될 때 함께 로딩되도록 static 메서드로 선언
	//    => 외부에서 접근하여 함부로 값을 변경할 수 없도록 private 접근제한자로 선언
	// 3. 생성된 인스턴스를 외부로 리턴하는 Getter 메서드 정의
	//    => 누구나 접근 가능하도록 public 접근제한자로 선언
	
	private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	// 여기까지 싱글톤 패턴
	
	// --------------------------------------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체를 Service 객체로 부터 전달받기 위한
	// Connection 타입 멤버변수 선언 및 Setter 메서드 정의
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// --------------------------------------------------------------------------------------------
	// 글쓰기작업
	// => Service 로 부터 전달받은 BoardBean 객체를 사용하여 INSERT 작업 수행
	
	public int insertBoard(BoardBean board) {
		System.out.println("BoardDAO - insertBoard()");
		int insertCount = 0;
		
		// 데이터베이스 작업에 필요한 변수 선언
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			int board_num =1; // 기본값이 있으므로 밑의 if문에서 else가 따로 필요X
			String sql = "SELECT MAX(board_num) FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board_num = rs.getInt(1) + 1;
		System.out.println("새 글 번호 : " + board_num);
		// ---------------------------------------------------------------------------------
		// 전달받은 데이터 (BoardBean 객체) 를 사용하여 INSERT 작업 수행
//		board = new BoardBean();
		// => 참조글번호(board_re_ref) 는 새 글 번호와 동일한 번호로 지정
		
		// => 들여쓰기레벨(board_re_lev) 과 순서번호(board_re_seq) 는 0으로 지정
		// => INSERT 구문 실행 후 리턴값을 insertCount 변수에 저장
		sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
		pstmt2 = con.prepareStatement(sql);
		pstmt2.setInt(1, board_num); // 글번호
		pstmt2.setString(2, board.getBoard_name()); // 작성자
		pstmt2.setString(3, board.getBoard_pass()); // 패스워드
		pstmt2.setString(4, board.getBoard_subject()); // 제목
		pstmt2.setString(5, board.getBoard_content()); // 내용
		pstmt2.setString(6, board.getBoard_file()); // 원본 파일명
		pstmt2.setString(7, board.getBoard_real_file()); // 실제 파일명
		pstmt2.setInt(8, board_num); // 참조글번호
		pstmt2.setInt(9, 0); // 들여쓰기 레벨
		pstmt2.setInt(10, 0); // 순서번호
		pstmt2.setInt(11, 0); // 조회수
		
		insertCount = pstmt2.executeUpdate();
		
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertBoard()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			// 주의! Connection 객체는 Service 가 관리하므로 DAO에서 반환 금지
		}
		
		
		return insertCount; // Service 클래스로 리턴
	}

	// 글 목록 조회
	public List<BoardBean> selecBoardList(String keyword, int startRow, int listLimit) {
		System.out.println("BoardDAO - selectBoardList()");
		
		List<BoardBean> boardList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// board 테이블의 모든 레코드 조회
			// => 검색어를 포함하는 레코드 조회
			//    단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식 못함
			// => idx 컬럼 기준 내림차순 정렬
			// => 시작행번호부터 게시물 목록 수 만큼으로 갯수 제한
			//    (단, 시작행 번호 첫번째는 0부터 시작)
			//    (또한, LIMIT 에 파라미터 하나만 사용 시 목록 갯수로 사용됨)
			// => 정렬 : 참조글 번호(board_re_ref) 기준 내림차순,
			//           순서번호(board_re_seq) 기준 오름차순
			// => 조회 시작 레코드
			String sql = "SELECT * FROM board WHERE board_subject LIKE ? ORDER BY board_re_ref DESC, board_re_seq ASC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" +keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 목록 저장할 List 객체 생성
			boardList = new ArrayList<BoardBean>();
			// 조회 결과가 있을 경우
			while(rs.next()) {
				// BoardBean 객체(board) 생성 후 조회 데이터 저장
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_real_file(rs.getString("board_real_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
				System.out.println(board);
				// 전체 목록 저장하는 List 객체에 1개 게시물 정보가 저장된 BoardBean 객체 추가
				boardList.add(board);
			}
			
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectBoardList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 가 관리하므로 DAO에서 반환 금지
		}
		
		
		
		return boardList;
	}

	public int selectBoardListCount(String keyword) {
		
		List<BoardBean> boardList = null;
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM board WHERE board_subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" +keyword + "%");
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우 listCount 변수에 저장
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectBoardList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 가 관리하므로 DAO에서 반환 금지
		}
		
		return listCount;
	}

	// 글 상세정보 조회
	public BoardBean selectBoard(int board_num) {
		System.out.println("BoardDAO - selectBoardList()");
		BoardBean board = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// board 테이블의 글번호가 일치하는 1개의 레코드 조회
			// => 검색어를 포함하는 레코드 조회
			//    단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식 못함
			// => idx 컬럼 기준 내림차순 정렬
			// => 시작행번호부터 게시물 목록 수 만큼으로 갯수 제한
			//    (단, 시작행 번호 첫번째는 0부터 시작)
			//    (또한, LIMIT 에 파라미터 하나만 사용 시 목록 갯수로 사용됨)
			// => 정렬 : 참조글 번호(board_re_ref) 기준 내림차순,
			//           순서번호(board_re_seq) 기준 오름차순
			// => 조회 시작 레코드
			String sql = "SELECT * FROM board WHERE board_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			while(rs.next()) {
				// BoardBean 객체(board) 생성 후 조회 데이터 저장
				board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_real_file(rs.getString("board_real_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
				System.out.println(board);
			}
			
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectBoard()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 가 관리하므로 DAO에서 반환 금지
		}
		
		return board; // 
	}

	// 조회수 증가 
	public int updateReadcount(int board_num) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			// 글번호가 일치하는 레코드의 조회수(readcount) 1만큼 증가
			String sql = "UPDATE board SET board_readcount = board_readcount+1 WHERE board_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("BoardDAO = updateReadcount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}		
		return updateCount;
	}

	public boolean isBoardWriter(int board_num, String board_pass) {
		boolean isBoardWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM board WHERE board_num=? AND board_pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.setString(2, board_pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isBoardWriter = true;
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - isBoardWriter()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 가 관리하므로 DAO에서 반환 금지
		}
		
		return isBoardWriter;
	}
	
	// 글 삭제
	public int deleteBoard(int board_num) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// 글번호에 해당하는 레코드 삭제
			String sql = "DELETE FROM board WHERE board_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("BoardDAO - deleteBoard()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}

	// 글 수정
	public int updateBoard(BoardBean board) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// board 테이블의 제목, 내용 변경
			String sql = "UPDATE board SET"
							+ "		board_subject = ? "
							+ "		, board_content = ? ";
							
							// 단, 파일명(board_file) 이 null 이 아닐 경우에만 파일명도 수정
							// => 즉, 파일명을 수정하는 SET 절을 문장에 추가 결합
							if(board.getBoard_file() != null) {
								sql += ", board_file = ?"
									+  ", board_real_file =?";
							}
			sql += "	WHERE board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_subject());
			pstmt.setString(2, board.getBoard_content());
			if(board.getBoard_file() != null) {
				pstmt.setString(3, board.getBoard_file());
				pstmt.setString(4, board.getBoard_real_file());
				pstmt.setInt(5, board.getBoard_num());
			} else {
				// null 이 아닐때는 글번호의 파라미터번호가 5번, 아니면 3번 이기때문에 else 문 필요
				pstmt.setInt(3, board.getBoard_num());
			}
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	// 답글 쓰기
		public int insertReplyBoard(BoardBean board) {
			// INSERT 작업 결과를 리턴받아 저장할 변수 선언
			int insertCount = 0;
			
			// 데이터베이스 작업에 필요한 변수 선언
			PreparedStatement pstmt = null, pstmt2 = null;
			ResultSet rs = null;
			
			try {
				// 새 글 번호 계산을 위해 기존 board 테이블의 모든 번호(board_num) 중 가장 큰 번호 조회
				// => 조회 결과 + 1 값을 새 글 번호로 지정하고, 조회 결과가 없으면 기본값 1 로 설정
				// => MySQL 구문의 MAX() 함수 사용(SELECT MAX(컬럼명) FROM 테이블명)
				int board_num = 1; // 새 글 번호
				
				String sql = "SELECT MAX(board_num) FROM board";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board_num = rs.getInt(1) + 1;
				}
				
//				System.out.println("새 글 번호 : " + board_num);
				// ----------------------------------------------------------------------------
				int ref = board.getBoard_re_ref(); // 원본글의 참조글번호
				int lev = board.getBoard_re_lev(); // 원본글의 들여쓰기 레벨
				int seq = board.getBoard_re_seq(); // 원본글의 순서번호

				// 기존 답글들에 대한 순서번호 증가 = UPDATE 구문
				// => 원본글의 참조글번호(board_re_ref) 와 같고
				//    원본글의 순서번호(board_re_seq) 보다 큰 레코드들의
				//    순서번호를 +1 씩 증가시키기
				sql = "UPDATE board SET board_re_seq = board_re_seq +1 WHERE board_re_ref = ? AND board_re_seq > ?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, ref);
				pstmt2.setInt(2, seq);
				pstmt2.executeUpdate();
				
				JdbcUtil.close(pstmt2);
				
				// 새 답글에 사용될 원본글의 lev, seq 값 +1 처리
				lev++;
				seq++;
				
				// ---------------------------------------------------------------
				// 답글에 대한 INSERT 작업 수행
				// => 글쓰기와 달리 ref, lev, seq 값은 새로 설정된 값으로 변경
				sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, board_num); // 글번호
				pstmt2.setString(2, board.getBoard_name()); // 작성자
				pstmt2.setString(3, board.getBoard_pass()); // 패스워드
				pstmt2.setString(4, board.getBoard_subject()); // 제목
				pstmt2.setString(5, board.getBoard_content()); // 내용
				pstmt2.setString(6, board.getBoard_file()); // 원본 파일명
				pstmt2.setString(7, board.getBoard_real_file()); // 실제 파일명
				pstmt2.setInt(8, ref); // 참조글번호
				pstmt2.setInt(9, lev); // 들여쓰기 레벨
				pstmt2.setInt(10, seq); // 순서번호
				pstmt2.setInt(11, 0); // 조회수
				
				insertCount = pstmt2.executeUpdate();
				
				
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류! - insertReplyBoard()");
				e.printStackTrace();
			} finally {
				// DB 자원 반환
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(pstmt2);
			}
			
			return insertCount;
		}
		
		
	}


