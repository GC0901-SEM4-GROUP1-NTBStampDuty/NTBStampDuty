<%-- 
    Document   : building_page
    Created on : Apr 22, 2015, 2:48:56 PM
    Author     : Phuc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>Land Details</title>
    <script src="javascript/jquery-1.11.2.min.js"></script>
    <script src="javascript/table.js" type="text/javascript"></script>
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
                <table class="land" id="land">
                    <tr>
                        <th style="width: 6%">Building ID</th>
                        <th style="width: 10%">Building Type</th>
                        <th style="width: 32%">Building Name</th>
                        <th style="width: 15%">Floors</th>
                        <th style="width: 25%">Rooms</th>
                        <th style="width: 12%">Houses</th>
                        <th style="width: 12%">Shops</th>
                        <th style="width: 12%">Date Constructed</th>
                        <th style="width: 12%">Completed percent</th>
                    </tr>
                    <c:forEach items="${buildingList}" var="building">
                        <tr>
                            <td>${building.buildingID}</td>
                            <td>${building.buidingTypeID}</td>
                            <td>${building.buildingName}</td>
                            <td>${building.floors}</td>
                            <td>${building.rooms}</td>
                            <td>${building.houses}</td>
                            <td>${building.shops}</td>
                            <td>${building.dateContructed}</td>
                            <td>${building.completedPercent}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="paging_size">
                <%--For displaying Next link --%>
                <c:choose>
                    <c:when test="${currentPage < noOfPages}">
                        <a class="btn_next_forcus" href="buildingDetail?page=${currentPage + 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_next" href="buildingDetail?page=${currentPage + 1}"></a>
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
                                    <td><a href="buildingDetail?page=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </tr>
                </table>

                <%--For displaying Previous link except for the 1st page --%>
                <c:choose>
                    <c:when test="${currentPage != 1}">
                        <a class="btn_previous_forcus" href="buildingDetail?page=${currentPage - 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_previous" href="buildingDetail?page=${currentPage - 1}"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</tag:MainTag>


