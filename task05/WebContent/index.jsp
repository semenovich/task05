<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
form {
	margin: 40px 620px;
}
input {
	padding: 30px;
}
</style>
</head>
<body>
	<form action="FrontContoller" method="get">
		<input type="hidden" name="parserType" value="SAX"/>
		<input type="hidden" name="page" value="1"/>
		<input type="submit" value="SAX"/>
	</form>
	<form action="FrontContoller" method="get">
		<input type="hidden" name="parserType" value="StAX"/>
		<input type="hidden" name="page" value="1"/>
		<input type="submit" value="StAX"/>
	</form>
	<form action="FrontContoller" method="get">
		<input type="hidden" name="parserType" value="DOM"/>
		<input type="hidden" name="page" value="1"/>
		<input type="submit" value="DOM"/>
	</form>
</body>
</html>