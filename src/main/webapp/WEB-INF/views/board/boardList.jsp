<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".go").click(function() {
			document.frm.curPage.value = $(this).attr("id");
			document.frm.search.value = '${listInfo.search}';
			document.frm.find.value = '${listInfo.find}';
			document.frm.submit();
			
			/*var cur = $(this).attr("id");
			location.href="${board}List?curPage="+cur+"&search=${listInfo.search}&find=${listInfo.find}" */
		});
	})
</script>
<title>Insert title here</title>
<style type="text/css">
	span {
		cursor: pointer;
	}
	span:hover {
		color : red;
	}
</style>
</head>
<body>
	<!-- List -->
	<h1>${board}</h1>
	
	<form action="${board}List" name="frm">
	<input type="hidden" name="curPage">
		<select name="search">
			<option value="title">TITLE</option>
			<option value="writer">WRITER</option>
			<option value="contents">CONTENTS</option>
		</select>
		<input type="text" name="find">
		<input type="submit" value="SEARCH">
	</form>
	
	<table>
		<tr>
			<td>NUM</td><td>TITLE</td><td>WRITER</td><td>DATE</td><td>HIT</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.num}</td>
			<td>
			<c:catch>
			<c:forEach begin="1" end="${dto.depth}">--</c:forEach>
			</c:catch>
			<a href="noticeView?num=${dto.num}">${dto.title}</a>
			</td>
			<td>${dto.writer}</td>
			<td>${dto.reg_date}</td>
			<td>${dto.hit}</td>
		</tr>
		</c:forEach>
		
	</table>
	<c:if test="${listInfo.curBlock > 1}">
		<span class="go" id="${listInfo.startNum-1}">[이전]</span>
		<%-- <a href="${board}List?curPage=${listInfo.startNum-1}&search=${listInfo.search}&find=${listInfo.find}">[이전]</a> --%>	
	</c:if>
	<c:forEach begin="${listInfo.startNum}" end="${listInfo.lastNum}" var="i">
		<span class="go" id="${i}">${i}</span>
		<%-- <a href="${board}List?curPage=${i}&search=${listInfo.search}&find=${listInfo.find}">${i}</a> --%>
	</c:forEach>
	<c:if test="${listInfo.curBlock < listInfo.totalBlock}">
		<span class="go" id="${listInfo.lastNum+1}">[다음]</span>
		<%-- <a href="${board}List?curPage=${listInfo.lastNum+1}&search=${listInfo.search}&find=${listInfo.find}">[다음]</a> --%>
	</c:if>
	
	
	<a href="${board}Write">WRITE</a>
	
	<div>
		<p>curPage : ${curPage }</p>
		<p>SEARCH : ${search }</p> 
		<p>FIND : ${find }</p>
	</div>
	
</body>
</html>