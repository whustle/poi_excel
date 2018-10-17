<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8" />
    <title>403</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>

    <script>
        $(function(){
            $("#but1").upload({
                action: 'http://localhost:8089/upload',
                name: 'myFile'
            });
            $("#but2").click(function () {
                $.post("http://localhost:8089/export")
            });
        });
    </script>
</head>
<body>
<input id="but1" type="button" value="导入">
</body>
</html>