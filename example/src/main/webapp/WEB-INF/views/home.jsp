<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<script>
	function movePage1(){
		location.href="/main/page1"
	}
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p>커밋사항1</p>
<button type="button" onclick="movePage1();">TestPage1</button>
</body>
<script src="/resources/js/home.js" type="text/javascript"></script>
</html>
