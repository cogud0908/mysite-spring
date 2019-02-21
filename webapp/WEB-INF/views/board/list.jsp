<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board/search" method="post">
					<input type="text" id="search" name="search" value = "${search }">
					<input type="submit" value="제목검색">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<c:choose>
								<c:when test="${pagelist[0] == pagelist[3] }">
									<td>[${ (pagelist[4] - status.index) }]</td>
								</c:when>
								<c:otherwise>
									<td>[${ ((pagelist[3]-pagelist[0])*10)+(pagelist[4] - status.index) }]</td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${vo.depth == 0 }">
									<td style="text-align:left"><a
										href="${pageContext.servletContext.contextPath}/board/view?no=${vo.no}">${vo.title }</a></td>
								</c:when>
								<c:otherwise> 
									<td style="text-align:left; padding-left:${40*vo.depth}px;">
									<img src="${pageContext.servletContext.contextPath}/assets/images/reply.png" style = "width:10px"/>
										<a href="${pageContext.servletContext.contextPath}/board/view?no=${vo.no}">${vo.title }</a></td>
								</c:otherwise>
							</c:choose>
							<td>${vo.name}</td>
							<td>${vo.hit }</td>
							<td>${vo.write_date }</td>
							<c:choose>
								<c:when test="${loginuser.no == vo.user_no }">
									<td><a class="del"
										href="${pageContext.servletContext.contextPath}/board/delete?no=${vo.no}"><img
											src="${pageContext.servletContext.contextPath}/assets/images/recycle.png"></a></td>
								</c:when>
								<c:otherwise>
									<td>&nbsp;</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				<!-- pager 추가 -->
			 	<div class="pager">
					<ul>
						<c:if test ="${pagelist[1] ne 1 }">
							<li><a href="${pageContext.servletContext.contextPath }/board?page=${pagelist[1]-1 }&search=${search }">◀</a></li>
						</c:if>
						<c:forEach var = "i" begin ="${pagelist[1] }" end = "${pagelist[2] }" varStatus="status" step = "1">
							<li><a href="${pageContext.servletContext.contextPath }/board?page=${status.index }&search=${search }">${status.index}</a></li>
						</c:forEach>
						<c:if test ="${pagelist[2] ne pagelist[3]}">
							<li><a href="${pageContext.servletContext.contextPath }/board?page=${pagelist[1]+5 }&search=${search }">▶</a></li>
						</c:if>
					</ul>
				</div>
				</form>
				<!-- pager 추가 -->
				<div class="bottom">
					<c:if test="${!empty loginuser }">
						<a
							href="${pageContext.servletContext.contextPath}/board/write"
							id="new-book">글쓰기</a>
					</c:if>
				</div>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>