<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	margin: 100px auto;
}
table, th, td {
    border: 1px solid black;
    font-size: 28px;
}

form, h1{
	margin: 10px auto;
	text-align: center;
	font-size: 24px;
}
</style>
</head>
<body>
	<c:if test="${!requestScope.info.books.isEmpty()}">
		<table>
			<tr>
			    <th>Id</th>
			    <th>Title</th> 
			    <th>Author</th>
			    <th>Genre</th>
			    <th>Publish date</th> 
			    <th>Price</th>
	  		</tr>
			<c:forEach items="${requestScope.info.books}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.genre}</td>
					<td>${book.publishDate}</td>
					<td>${book.price}$</td>
				</tr>
			</c:forEach>
		</table>
		<h1>Page: ${requestScope.page}</h1>
	</c:if>
	<form id="pagination" action="FrontContoller" method="get">
		<input type="hidden" name="parserType" value="${requestScope.info.parserType}"/>
		<input id="page" type="hidden" name="page" value="1"/>
		<c:forEach begin="1" end="${requestScope.info.pagesNumber}" varStatus="loop">
			<a href="javascript:submitForm(${loop.index})">${loop.index}</a>
		</c:forEach>
	</form>	
</body>
<script>
    function submitForm(page){
    	var form = document.getElementById("pagination");
    	document.getElementById("page").value = page;
    	form.submit();
    } 	
</script>
</html>
