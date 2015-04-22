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
                <div class="row">
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                </div>
                <table class="land">
                    <tr>
                        <th style="width: 6%">Land ID</th>
                        <th style="width: 10%">Size</th>
                        <th style="width: 32%">Address</th>
                        <th style="width: 15%">Building Types</th>
                        <th style="width: 25%">Price</th>
                        <th style="width: 12%">Built Status</th>
                    </tr>
                    <c:forEach items="${landList}" var="land">
                        <tr>
                            <td>${land.landID}</td>
                            <td>${land.size}</td>
                            <td>${land.addressID}</td>
                            <td>${land.buildingTypes}</td>
                            <td>${land.price}</td>
                            <td>${land.buildStatus}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="paging_size">
                <%--For displaying Next link --%>
                <c:choose>
                    <c:when test="${currentPage < noOfPages}">
                        <a class="btn_next_forcus" href="login?page=${currentPage + 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_next" href="login?page=${currentPage + 1}"></a>
                    </c:otherwise>
                </c:choose>

                <%--For displaying Page numbers. 
                The when condition does not display a link for the current page--%>
                <table>
                    <tr>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td class="on_select_page">${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="login?page=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </tr>
                </table>

                <%--For displaying Previous link except for the 1st page --%>
                <c:choose>
                    <c:when test="${currentPage != 1}">
                        <a class="btn_previous_forcus" href="login?page=${currentPage - 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_previous" href="login?page=${currentPage - 1}"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</tag:MainTag>

