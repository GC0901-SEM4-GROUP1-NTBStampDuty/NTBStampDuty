<%-- 
    Document   : login_Page
    Created on : Apr 19, 2015, 10:34:56 AM
    Author     : SonNguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login NTB</title>
        <link href="css/general_stype.css" rel="stylesheet" type="text/css"/>
        <link href="css/login_stype.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="login">
        <div class="login_splash">
            <img id="login_logo" src="images/ic_ntb.png">
        </div>

        <div class="login_content">
            <div class="login_auth_dialog">
                <h3>Sign in with your credentials</h3>
                <form class="login_auth" action="login" method="post">
                    <input id="login_input" name="username" placeholder="Username"/><br/>
                    <input id="login_input" type="password" placeholder="Password" name="password"/><br/>
                    <div class="login_remember_form">
                        <input id="login_remember" name="remember" value="checked" type="checkbox"><span> Remember me</span>
                    </div>
                    <input id="login_submit" type="submit" value="Sign In"/>
                </form>
            </div>
        </div>

        <div class="login_footer">
            <a href="#" class="login_forgot">Forgot password?</a>
        </div>
    </body>
</html>
