<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" contentType="text/html; charset=GB2312" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="lib/bootstrap.min.css" rel="stylesheet">
    <title>��½</title>

</head>
<body onload='document.loginForm.username.focus();'>

<div class="container">
    <h1>��ŵά�ع���Ա��¼</h1>

    <div id="login-box">

        <h3>�������û���������</h3>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <form role="form" name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
            <div class="form-group">
                <label for="name">�û���</label>
                <input type="text" class="form-control" id="name" name="j_username"
                       placeholder="�������û���">
            </div>
            <div class="form-group">
                <label for="password">����</label>
                <input type="text" class="form-control" id="password" name="j_password"
                       placeholder="����������">
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn btn-default">�ύ</button>
        </form>

    </div>

</div>


</body>
</html>