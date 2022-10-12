// 외부로부터 ID 와 Password 를 전달받아 판별하는 login() 함수 정의
// => 아이디 = admin
// => 패스워드 = 1234 : "로그인 성공!"
//    아니면 "로그인 실패"

function login(id, ps) {
	if(id == "admin" && ps == "1234") {
		document.write("로그인 성공!");
	} else {
		document.write("로그인 실패!");
	}
}