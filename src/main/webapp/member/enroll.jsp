<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="/resources/css/member/main.css">
	</head>
	<body>
		<h1>회원가입</h1>
		<h3>회원정보를 입력하시오</h3>
<!-- 		post: 쿼리스트링이 보이지 않음 -->
<!-- 		get : 쿼리스트링이 보임 -->
<!-- 		<form action="/member/register.do" method="get"> -->
		<form action="/member/register.do" method="post">
			<fieldset>
				<legend>회원가입</legend>
				<ul>
					<li>
						<label for="member-id">아이디</label>
						<input type="text" id="member-id" name="member-id">  <!-- id : 기능 / name : 쿼리스트링 -->
					</li>
					<li>
						<label for="member-pw">비밀번호</label>
						<input type="password" id="member-pw" name="member-pw">
					</li>
					<li>
						<label for="member-name">이름</label>
						<input type="text" id="member-name" name="member-name">
					</li>
					<li>
						<label for="member-age">나이</label>
						<input type="number" id="member-age" name="member-age">
					</li>
					<li>
						<label for="member-gender">성별</label>
<!-- 						<label for="male">남</label><input type="radio" id="male" name="member-gender" value="M"> -->
<!-- 						<label for="female">여</label><input type="radio" id="female" name="member-gender" value="F"> -->
						남<input type="radio" id="male" name="member-gender" value="M">
						여<input type="radio" id="female" name="member-gender" value="F">
					</li>
					<li>
						<label for="member-addr">주소</label>
						<input type="text" id="member-addr" name="member-addr">
					</li>
					<li>
						<label for="member-email">이메일</label>
						<input type="email" id="member-email" name="member-email">
					</li>
					<li>
						<label for="member-phone">전화번호</label>
						<input type="tel" id="member-phone" name="member-phone">					
					</li>
					<li>
						<label for="member-hobby">취미</label>
						<input type="text" id="member-hobby" name="member-hobby">					
					</li>
				</ul>
			</fieldset>
			<input type="submit" value="가입하기">
			<input type="reset" value="초기화">
		</form>
	</body>
</html>