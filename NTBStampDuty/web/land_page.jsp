<%-- 
    Document   : land_page
    Created on : Apr 20, 2015, 3:55:48 AM
    Author     : SonNguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>Land Details</title>
</head>
<tag:MainTag>
    <div id="main_body">
        <div class="edit_menu">

        </div>
        <div class="detail_bar">
            <div  class="table_land">
                <table>
                    <tr>
                        <th >Land ID</th>
                        <th>Size</th>
                        <th>Address</th>
                        <th>Building Types</th>
                        <th>Building Plan</th>
                        <th>Built Status</th>
                    </tr>
                    <c:forEach items="${landList}" var="land">
                        <tr>
                            <td>${land.landID}</td>
                            <td>${land.size}</td>
                            <td>${land.addressID}</td>
                            <td>${land.buildingTypes}</td>
                            <td>${land.buildingPlan}</td>
                            <td>${land.buildStatus}</td>
                        </tr>
                    </c:forEach>
                </table>

                <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <a class="btn_previous" href="login?page=${currentPage - 1}"></a>
                </c:if>

                <%--For displaying Page numbers. 
                The when condition does not display a link for the current page--%>
                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="login?page=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </tr>
                </table>

                <%--For displaying Next link --%>
                <c:if test="${currentPage < noOfPages}">
                    <td><a href="login?page=${currentPage + 1}">Next</a></td>
                </c:if>

            </div>
        </div>
    </div>
</tag:MainTag>

