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
    <link href="css/build_page_styple.css" rel="stylesheet" type="text/css"/>
</head>
<tag:MainTag>
    <div id="main_body">
        <div class="header">
            <div class="edit_menu">
                <form class="btn_new">
                    <input type="submit" value="New" />
                </form>
                <!--                <form class="btn_edit">
                                    <input type="submit" value="Edit" />
                                </form>-->
                <form class="btn_delete">
                    <input type="submit" value="Delete" />
                </form>
            </div>
            <div class="search_menu">
                <form class="form_search" action="searchBuilding" method="post">
                    <select name="searchColumn">
                        <option value="BuildID">Building ID</option>
                        <option value="BuildName">Building Name</option>
<!--                        <option value="BuildType">Building Type</option>-->
                    </select>
                    <input class="txt_search" type="text" name="searchValue"/>
                    <input class="btn_search" type="submit" value="" />
                </form>
            </div>
        </div>

        <div class="detail_bar">
            <div  class="table_container">
                <div class="row_build">
<!--                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>-->
                </div>
                <table class="table_build" id="table">
                    <tr>
                        <th style="width: 8%">Building Id</th>
                        <th style="width: 30%">Building Name</th>
                        <th style="width: 14%">Building Type</th>
                        <th style="width: 10%">Floors</th>
                        <th style="width: 14%">Date Constructed</th>
                        <th style="width: 10%">Completed</th>
                        <th style="width: 14%">Detail</th>
                    </tr>
                    <c:forEach items="${buildingList}" var="building">
                        <tr>
                            <td>${building.buildingID}</td>
                            <td>${building.buildingName}</td>
                            <td>${building.buildingType}</td>
                            <td>${building.floors}</td>
                            <td>${building.dateContructed}</td>
                            <td>${building.completedPercent}</td>
                            <td></td>
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


