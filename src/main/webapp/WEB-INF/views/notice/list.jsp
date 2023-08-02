<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 목록 조회</title>
		<style>
			table {
				width : 800px;
				border : 1px solid black;
				border-collapse : collapse;				
			}
			
			th, td  {
				border : 1px solid black
			}
		</style>
	</head>
	<body>
		<h1>공지사항 목록</h1>
		<table>
			<colgroup>
				<col width="10%">
				<col width="35%">
				<col width="10%">
				<col width="25%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
<!-- 				for(Notice notice : nList_ -->
				<c:forEach var="notice" items="${ nList }">
					<tr>
<!-- 						notice.getNoticeNo() -->
<!-- 						뒤에 가져올 변수명을 적어준다. -->
						<td>${ notice.noticeNo }</td>
						<td><a href="/notice/detail.do?noticeNo=${ notice.noticeNo }">${ notice.noticeSubject }</a></td>
						<td>${ notice.noticeWriter }</td>
						<td>${ notice.noticeDate }</td>
						<td>${ notice.viewCount }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="center">
<!-- 						<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a> -->
						${ pageNavi }
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>