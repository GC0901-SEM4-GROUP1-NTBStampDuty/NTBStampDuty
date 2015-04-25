<%-- 
    Document   : MainTag
    Created on : Apr 20, 2015, 6:56:46 AM
    Author     : SonNguyen
--%>

<%@tag description="Main tag" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/general_stype.css" rel="stylesheet" type="text/css"/>
        <script src = "javascript/jquery.js" type="text/javascript"></script>
        <script src = "javascript/jquery-ui.js" type="text/javascript"></script>
        <script src="javascript/js.js" type="text/javascript"></script>
    </head>
    <body class="main_page">
        <tag:LeftTag></tag:LeftTag>
            <div class="wrapper">
            <jsp:doBody></jsp:doBody>
            <tag:FooterTag></tag:FooterTag>
        </div>
    </body>
</html>
