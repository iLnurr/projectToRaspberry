<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html lang="ru">
<head>
    <link href="../views/css/style.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Цитатник</title>
</head>
<body>
<p><a href="/new">Зарегистрироваться</a> </p>
<p style="width: 100%; text-align: right ">
    <select onclick="" >
        <option value="Сортировка по дате" onclick="${userQuotationsService.sortByDate(quotationsList)}">Сортировка по дате</option>
    </select>
</p>
<p style="width: 100%; text-align: right">
    <input type="button" onclick='location.href="/newQuota"' value="Добавить цитату">
</p>
<div>
    <div style="width: 45%; display: inline-block">
            <c:choose>
                <c:when test="${quotationsList.size() > '0'}">
                    <c:forEach items="${quotationsList}" var="userQuotations">
                        <tr>
                            <td >${userQuotationsService.getFormattedDate(userQuotations.getDateTime())}</td>
                            <td >${userService.get(userQuotationsService.getUserId(userQuotations.getId())).name}</td>
                        </tr>
                        <tr>
                            <p class="text">
                                    ${userQuotations.getDescription()}
                            </p>
                            <p  style="border-bottom: 1px solid"> </p>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>В цитатнике на данный момент нет цитат.</p>
                </c:otherwise>
            </c:choose>

    </div>
    <div style="width: 45%; display: inline-block"></div>
</div>
<br>
<h2>Описание</h2>
<p>Максимальное количество знаков 8000</p>
</body>
</html>