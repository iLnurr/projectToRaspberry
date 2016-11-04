<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Цитатник</title>
</head>
<body>
<div align="center">
    <h1>Добавить цитату</h1>
    <div>
        <div style="width: 25%; display: inline-block; height: 40px">
            <p>Имя</p>
            <input type="text" name="user" id="field" style="width: 100%; height: 100%" value="">
        </div>
        <div style="width: 25%; display: inline-block; height: 40px">
            <select name="user">
                <c:forEach items="${userService.getAll()}" var="user">
                    <option id="text" onclick="document.getElementById('field').value = this.value;
                    document.getElementById('userId').value = this.value"
                            value="${user.name}" style="width: 100%; height: 100%">${user.name}</option>

                </c:forEach>
            </select>
        </div>
        <form:form action="saveQuota" method="post" modelAttribute="userQuotations">
            <div>
                <form:textarea path="description" cols="20" rows="10" cssStyle="width: 50%; text-align: start"/>
            </div>
            <div style="width: 25%; height: 40px">
                <input type="reset" style="background: red; color: white; height: 40px" value="Отменить">
                <input type="submit" style="background: green; color: white; height: 40px;width: 40px" value="+">
            </div>
            <input type="hidden" id="userId" name="userId" value=""/>
        </form:form>
    </div>
</div>
</body>
</html>