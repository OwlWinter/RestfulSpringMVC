<%--
  Created by IntelliJ IDEA.
  User: owlwinter
  Date: 2020/5/10
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据</title>
</head>
<body>
    <h2>学生列表</h2>
    <p><a href="/index.jsp">返回菜单</a> </p>
    <div>
        <table border="1" cellpadding="2" cellspacing="0" table-layout:fixed;>
            <tr>
                <th>id</th>
                <th>student_id</th>
                <th>qq_num</th>
                <th>student_name</th>
                <th>major_subject</th>
                <th>graduated_school</th>
                <th>brother</th>
                <th>come_from</th>
                <th>daily_report</th>
                <th>slogan</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${disciplelist}" var="disciple">
                <tr>
                    <td>${disciple.id}</td>
                    <td>${disciple.student_id}</td>
                    <td>${disciple.qq_num}</td>
                    <td>${disciple.student_name}</td>
                    <td>${disciple.major_subject}</td>
                    <td>${disciple.graduated_school}</td>
                    <td>${disciple.brother}</td>
                    <td>${disciple.come_from}</td>
                    <td>${disciple.daily_report}</td>
                    <td>${disciple.slogan}</td>
                    <td>
                        <a href="/web/update?id=${disciple.id}">修改</a>
                        <br/>
                        <a href="/web/delete?id=${disciple.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
