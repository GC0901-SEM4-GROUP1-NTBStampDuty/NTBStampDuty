<%-- 
    Document   : signup_page
    Created on : Jul 19, 2015, 11:35:50 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Here</title>
        <link href="css/general_style.css" rel="stylesheet" type="text/css"/>
        <link href="css/signup_page.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="signup">
        <div class="signup_splash">
            <img id="signup_logo" src="images/ic_ntb.gif">
        </div>
        <div class="signup_content">
            <div class="signup_auth_dialog">
                <h3>Select your Username and Password to Sign Up</h3>
                <form class="signup_auth" action="signup" method="post">
                    <input id="signup_input" type="text" name="username" placeholder="Username" required="true"  maxlength="15"/><br/>
                    <input id="signup_input" type="password" name="password" placeholder="Password" required="true" maxlength="20"/><br/>
                    <select id="signup_input" name="role" >
                        <option value="2">2</option>
                    </select><br/>
                    <input id="signup_input" type="text" name="fullname" placeholder="Fullname" required="true"  maxlength="50"/><br/>
                    <select id="signup_input" name="gender" placeholder="Gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select><br/>
                    <input id="signup_input" type="text" name="phone" placeholder="Phone" required="true"  maxlength="15"/><br/>
                    <input id="signup_input" type="date" name="dateofbirth" placeholder="Birth Date" required="true"/><br/>
                    <input id="signup_input" type="text" name="address" placeholder="Address" required="true"  maxlength="15"/><br/>
                    <input id="signup_input" type="text" name="email" placeholder="Email" required="true"  maxlength="50"/><br/>
                    <input id="signup_submit" type="submit" value="Register"/>
                </form>
            </div>
    </body>
</html>
