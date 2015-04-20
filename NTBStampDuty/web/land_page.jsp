<%-- 
    Document   : land_page
    Created on : Apr 20, 2015, 3:55:48 AM
    Author     : SonNguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tag"%>

<tag:MainTag>
    <div id="main_body">
        <div class="detail_bar">
            <h1 style="color:black">Land List</h1>
            <table border="1" style="color:black">
                <tr>
                    <td>Land ID</td>
                    <td>Size</td>
                    <td>Address</td>
                    <td>Building Types</td>
                    <td>Building Plan</td>
                    <td>Built Status</td>
                    <td>Image</td>
                </tr>
                <c:forEach items="${landList}" var="land">
                    <tr>
                        <td>${land.landID}</td>
                        <td>${land.size}</td>
                        <td>${land.addressID}</td>
                        <td>${land.buildingTypes}</td>
                        <td>${land.buildingPlan}</td>
                        <td>${land.buildStatus}</td>
                        <td>${land.img}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</tag:MainTag>
<head>
    <title>Land Details</title>
</head>

