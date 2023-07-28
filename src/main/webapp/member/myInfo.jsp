<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>마이페이지</title>
		<link rel="stylesheet" href="/resources/css/member/main.css">
	</head>
	<body>
		<h1>마이페이지</h1>
		<form action="/member/modify.do" method="post">
			<fieldset>
				<legend>회원 상세 정보</legend>
				<ul>
					<li>
						<label for="member-id">아이디</label>
						<input type="text" id="member-id" name="member-id" value="${ member.memberId }" readonly>  <!-- id : 기능 / name : 쿼리스트링 -->
					</li>
					<li>
						<label for="member-pw">비밀번호</label>
						<input type="password" id="member-pw" name="member-pw">
					</li>
					<li>
						<label for="member-name">이름</label>
						<input type="text" id="member-name" name="member-name" value="${ member.memberName }" readonly>
					</li>
					<li>
						<label for="member-age">나이</label>
						<input type="number" id="member-age" name="member-age" value="${ member.memberAge }" readonly>
					</li>
					<li>
						<label for="member-gender">성별</label>
							<!-- 기존 성별 수정의 오류를 막기 위해 적어주는 input 태그. hidden으로 사용자에게는 보이지 않음 -->
							<!-- 수정은 하지 않고, 화면 표시만 남자 / 여자 로 적어주기 -->
							<input type="hidden" id="male" name="member-gender" value="${ member.memberGender }"> 
							<c:if test="${ member.memberGender eq 'M' }">남자</c:if>
							<c:if test="${ member.memberGender eq 'F' }">여자</c:if>
						
<%-- 						남<input type="radio" id="male" name="member-gender" value="M" <c:if test="${ member.memberGender eq 'M'}">checked</c:if>> --%>
<%-- 						여<input type="radio" id="female" name="member-gender" value="F" <c:if test="${ member.memberGender eq 'F'}">checked</c:if>> --%>
					</li>
					<li>
						<label for="member-email">이메일</label>
						<input type="email" id="member-email" name="member-email" value="${ member.memberEmail }">
					</li>
					<li>
						<label for="member-phone">전화번호</label>
						<input type="tel" id="member-phone" name="member-phone" value="${ member.memberPhone }">					
					</li>
					<li>
						<label for="member-addr">주소</label>
						<input type="text" id="member-addr" name="member-addr" value="${ member.memberAddress }">
					</li>
					<li>
						<label for="member-hobby">취미</label>
						<input type="text" id="member-hobby" name="member-hobby" value="${ member.memberHobby }">
											
					</li>
					<li>
						<label for="member-date">가입 날짜</label>
						<input type="text" id="member-date" name="member-date" value="${ member.memberDate }" readonly>
											
					</li>
				</ul>
			</fieldset>
			<div>
				<button type="submit">수정하기</button>
				<a href="javascript:void(0)" onclick="checkDelete();">탈퇴하기</a>
<!-- 				<button>탈퇴하기</button> -->
			</div>
		</form>
		<script>
		// /member/delete.do?memberId=${ sessionScope.memberId }
			function checkDelete() {
				const memberId = '${ sessionScope.memberId }';
				if(confirm("탈퇴하시겠습니까?")){
					location.href = "/member/delete.do?memberId=" + memberId;
				}
				
			}
		</script>
	</body>
</html>