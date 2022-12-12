package vo;
/*
 * member 테이블 정의
 * --------------------------
 * 이름(name) - VARCHAR(20) NN
 * 아이디(id) - VARCHAR(16) PK
 * 패스워드(passwd) - VARCHAR(16) NN
 * E-mail(email) - VARCHAR(50), UN, NN
 * 성별(gender) - VARCHAR(1) NN
 * 가입일(date) - DATE NN
 * --------------------------
 */

import java.sql.Date;

public class MemberBean {
	String name;
	String id;
	String passwd;
	String email;
	String gender;
	Date date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "MemberBean [name=" + name + ", id=" + id + ", passwd=" + passwd + ", email=" + email + ", gender="
				+ gender + ", date=" + date + "]";
	}
	
}
