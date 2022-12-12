package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import db.JdbcUtil;
import vo.MemberBean;

public class MemberDAO {

private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	//-----------------------------------------------------
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	PreparedStatement pstmt = null;
	// -----------------------------------------------------
	public int insertMember(MemberBean member) {
		System.out.println("MemberDAO - insertMember()");
		System.out.println(member.getId());
		
		int insertCount = 0;
		
		try {
			String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getGender());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertMember()!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}
	
	
	// 로그인
		public int selectMember(MemberBean member) {
			int loginResult = -1;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// 아이디에 해당하는 패스워드 조회
				String sql = "SELECT passwd"
									+ " FROM member"
									+ " WHERE id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getId());
				
				rs = pstmt.executeQuery();
				System.out.println(pstmt);
				
				if(rs.next()) { // 아이디가 일치할 경우
					// 조회된 패스워드를 입력받은 패스워드와 비교
					if(member.getPasswd().equals(rs.getString("passwd"))) { // 패스워드 일치할 경우
						loginResult = 1;
					} else { // 패스워드 불일치할 경우
						loginResult = 0;
					}
				} else { // 아이디가 일치하지 않을 경우(= 아이디가 존재하지 않음)
					loginResult = -1;
				}
			} catch (SQLException e) {
				System.out.println("loginMember() 오류");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return loginResult;
		}

		// 회원목록 조회
		public List<MemberBean> selectMemberList() {
			List<MemberBean> memberList = null;
			
			ResultSet rs = null;
			
			try {
				// 전체 회원 목록 조회(임시로 아이디 오름차순 정렬)
				String sql = "SELECT * FROM member ORDER BY id ASC";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				// 전체 회원정보를 저장할 List 객체 생성
				memberList = new ArrayList<MemberBean>();
				
				while(rs.next()) {
					// 1명의 회원 정보를 저장할 MemberBean 객체 생성
					MemberBean member = new MemberBean();
					member.setName(rs.getString("name"));
					member.setId(rs.getString("id"));
					member.setPasswd(rs.getString("passwd"));
					member.setEmail(rs.getString("email"));
					member.setGender(rs.getString("gender"));
					member.setDate(rs.getDate("date"));
					
					//List 객체에 MemberBean 객체 추가
					memberList.add(member);
				}
			} catch (SQLException e) {
				System.out.println("selectMemberList - SQL 구문 오류!");
				e.printStackTrace();
			}
			
			return memberList;
		}
	
}
