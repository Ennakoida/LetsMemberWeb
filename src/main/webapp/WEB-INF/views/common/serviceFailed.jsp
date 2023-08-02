<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>서비스 결과 실패</title>
	</head>
	<body>
		<h1>서비스 결과 실패 ㅠ.ㅠ</h1>
		<h2>${ msg }</h2>
		<script>
			const result = '${ msg }';
			const url = '${ url }';
			alert(result);
			location.href = url;
		</script>
	</body>
</html>