<%--
  Created by IntelliJ IDEA.
  User: owlwinter
  Date: 2020/5/10
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h2>${info}</h2>
<form method="post" action="${base}/disciple/${action}">
    <input type="hidden" name="id" value="${old_disciple.id}">
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <td>姓名</td>
            <td><input type="text" name="student_name" value="${old_disciple.student_name}"></td>
        </tr>

        <tr>
            <td>QQ号码</td>
            <td><input type="number" name="qq_num" value="${old_disciple.qq_num}"></td>
        </tr>

        <tr>
            <td>学号</td>
            <td><input type="number" name="student_id" value="${old_disciple.student_id}"></td>
        </tr>
        <tr>
            <td>科目</td>
            <td><input type="text" name="major_subject" value="${old_disciple.major_subject}"></td>
        </tr>
        <tr>
            <td>毕业院校</td>
            <td><input type="text" name="graduated_school" value="${old_disciple.graduated_school}"></td>
        </tr>
        <tr>
            <td>审核师兄</td>
            <td><input type="text" name="brother" value="${old_disciple.brother}"></td>
        </tr>
        <tr>
            <td>从哪里了解到修真院</td>
            <td><input type="text" name="come_from" value="${old_disciple.come_from}"></td>
        </tr>
        <tr>
            <td>日报链接</td>
            <td><input type="text" name="daily_report" value="${old_disciple.daily_report}"></td>
        </tr>
        <tr>
            <td>口号</td>
            <td><input type="text" name="slogan" value="${old_disciple.slogan}"></td>
        </tr>
        <tr>
            <td colspan = 2>
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
<p>${result}</p>
</body>
</html>
