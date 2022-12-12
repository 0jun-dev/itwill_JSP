package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;import javax.servlet.RequestDispatcher;

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
			System.out.println(member.getGender());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertMember()!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}
	
	
	public int loginUser(String id, String passwd) {
		System.out.println("MemberDAO - LoginUser()");
		int insertCount = 0;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM member WHERE id = ? AND passwd = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				insertCount++;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - loginUser()!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		System.out.println("DAO - " + insertCount);
		return insertCount;
	}
	
}
