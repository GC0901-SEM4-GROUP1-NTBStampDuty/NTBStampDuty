<%-- 
    Document   : LeftTag
    Created on : Apr 20, 2015, 3:27:20 AM
    Author     : SonNguyen
--%>

<%@tag description="Left Bar" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tag"%>
<div id="left_bar">
    <div class="logo">
        <img src="/NTBStampDuty/images/logo_ntb.gif" width="185px" height="120px"/>
    </div>
    <div class="left_menu" id="bar">
        <ul>
            <li>
                <a href="/NTBStampDuty/projectDetail">PROJECT DETAILS</a>
            </li>
            <li>
                <a href="/NTBStampDuty/buildingDetail">BUILDING DETAILS</a>
            </li>
            <li>
                <a href="/NTBStampDuty/landDetail">LAND DETAILS</a>
            </li>
<!--            <li>
                <a href="#">ROOM DETAILS</a>
            </li>-->
            <li>
                <a href="/NTBStampDuty/userDetail">ACCOUNT DETAILS</a>
            </li>
        </ul>
    </div>
</div>