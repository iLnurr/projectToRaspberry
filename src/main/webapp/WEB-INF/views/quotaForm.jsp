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
        <form:form action="saveQuota" method="post" modelAttribute="userQuotationsTo">

            <div>
                <form:select path="userName">
                    <form:options items="${users}" itemLabel="name" itemValue="name" />
                </form:select>
            </div>
            <div>
                <form:textarea path="description" cols="20" rows="10" cssStyle="width: 50%; text-align: start"/>
            </div>
            <div style="width: 25%; height: 40px">
                <input type="reset" style="background: red; color: white; height: 40px" value="Отменить">
                <input type="submit" style="background: green; color: white; height: 40px;width: 40px" value="+">
            </div>
        </form:form>
    </div>
</div>
</body>
</html>