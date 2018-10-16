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
                action: 'upload',
                name: 'myFile'
            });

        });
    </script>
</head>
<body>
<input id="but1" type="button" value="upload">
</body>
</html>