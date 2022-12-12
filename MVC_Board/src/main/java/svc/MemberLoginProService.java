package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberLoginProService {

   public int isRightUser(String id, String passwd) {
	   int isRightUser = 0;
      
      Connection con = JdbcUtil.getConnection();
      
      MemberDAO dao = MemberDAO.getInstance();
      
      dao.setConnection(con);
      
      isRightUser = dao.loginUser(id, passwd);
      
      JdbcUtil.close(con);
      System.out.println("로그인프로서비스" + isRightUser);
      return isRightUser;
   }
   
}
