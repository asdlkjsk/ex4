<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- List -->
	<h1>${board}</h1>
	
	<form action="${board}List">
		<select name="search">
			<option value="title">TITLE</option>
			<option value="writer">WRITER</option>
			<option value="contents">CONTENTS</option>
		</select>
		<input type="text" name="find">
		<input type="submit" value="SEARCH">
	</form>
	
	
	<a href="${board}Write">WRITE</a>
</body>
</html>