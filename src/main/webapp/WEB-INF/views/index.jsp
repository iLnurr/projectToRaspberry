<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml" style="height: 100%;">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Записки на быструю руку</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
</head>

<body style="font: 12px/18px Arial, sans-serif;height: 2600px;">

<div class="wrapper" style="min-width: 600px;max-width: 1600px;margin: 0 auto;min-height: 2600px;height: auto !important;height: 2600px;">

	<div class="header" style="	height: 120px;background: #666666;">
		<p><input type="button" onclick='location.href="/new"' value="Зарегистрироваться"></p>
		<p style="width: 100%; text-align: center"><strong style="color: #bfbfbf; font: 24px Arial, sans-serif;">Записки на быструю руку</strong></p>
		<p>
			<input type="button" onclick='location.href="/newQuota"' value="Добавить цитату     ">
			<input type="button" onclick='location.href="/userList"' value="Список пользователей">
		</p>
	</div><!-- .header-->

	<div class="middle" style="	width: 100%;padding: 0 0 100px;position: relative;">
		<div class="container" style="width: 100%;float: left;overflow: hidden;">

				<c:choose>
					<c:when test="${quotationsList.size() > '0'}">
						<c:forEach items="${quotationsList}" var="userQuotations">
					<div class="content" style="padding: 0 120px 0 120px;border: 2px solid #888888;">
							<tr>
								<p class="text">${userQuotations}</p>
							</tr>
					</div><!-- .content-->
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p style="text-align: center">В цитатнике на данный момент нет цитат.</p>
					</c:otherwise>
				</c:choose>

		</div><!-- .container-->

		<div class="left-sidebar" style="float:left;width:120px;height:100%;margin-left:-100%;position:relative;background:#666666;">
			<strong> </strong>
		</div><!-- .left-sidebar -->
		<div class="right-sidebar" style="float:left;width:120px;height:100%;margin-left:-120px;position:relative;background:#666666;">
			<strong style="color: #666666;"> </strong>
		</div><!-- .right-sidebar -->
	</div><!-- .middle-->
</div><!-- .wrapper -->

</body>
</html>