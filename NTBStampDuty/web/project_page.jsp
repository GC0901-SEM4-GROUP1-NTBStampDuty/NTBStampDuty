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
    <link href="css/project_page_styple.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script>
        function submitter(btn) {
            $('.' + btn).dialog({modal: true, show: 'fade', hide: 'drop'});
        }

        function getName(btn) {
            var category = btn.parentElement.parentElement.id;
            return category;
        }

        function getProjectID(btn) {
            var getID = btn;
            var myForm = document.forms["myForm"];
            myForm.elements["projectID"].value = getID;
            myForm.submit();
        }

        function getFilter() {
            var myForm1 = document.forms["myForm1"];
            myForm1.submit();
        }
    </script>
</head>
<tag:MainTag>
    <div id="main_body">
        <div class="header">
            <div class="edit_menu">
                <div class="btn_new">
                    <input type="submit" value="New" onclick="submitter('addNewProject')"/>
                </div>
                <div class="filter_menu">
                    <form class="form_filter" id="myForm1" action="projectFilter" method="post">
                        <select name="FilterColumn" id="filterColumn" onchange="getFilter()">
                            <option value="0">Select an option</option>
                            <option value="ProjectName">Project Name</option>
                            <option value="DateCreated">Date Created</option>
                        </select>
                    </form>
                </div>
            </div>

            <div class="search_menu">
                <form class="form_search" action="" method="post">
                    <select name="searchColumn" id="searchColumn">
                        <option value="ProjectName">Project Name</option>
                        <option value="BuildingName">Building Name</option>
                    </select>
                    <input class="txt_search" type="text" name="searchValue"/>
                    <input class="btn_search" type="submit" value=""/>
                </form>
            </div>
        </div>

        <div class="detail_bar">
            <div class="table_container">
                <div class="row_user">
                </div>
                <table class="table_project" id="table">
                    <tr>
                        <th style="width: 100%">List Project</th>
                    </tr> 
                </table>

                <form id="myForm" action="getProjectDetail" method="post">
                    <input type="hidden" name="projectID"/>
                    <div class="project_container" >
                        <c:forEach items="${projectList}" var="pro">
                            <div id="project" align="center" onclick="getProjectID(${pro.projectID})">
                                <a><img id="pro_img" src="images/ic_none_image.png"/></a><br/>
                                <a style="font-weight: bold">${pro.projectName}</a><br/>
                                <a>${pro.buildingName}</a><br/>
                                <a>
                                    <div align="left" id="progress-bar-container">
                                        <div style="width:${pro.completePercent}%;background-image: linear-gradient(to top, #9ACD00, #9ACD00); height:10px;">
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </form>

            </div>
            <div class="paging_size">
                <%--For displaying Next link --%>
                <c:choose>
                    <c:when test="${currentPage < noOfPages}">
                        <a class="btn_next_forcus" href="projectDetail?page=${currentPage + 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_next" href="projectDetail?page=${currentPage + 1}"></a>
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
                                    <td><a href="projectDetail?page=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </tr>
                </table>
                <div class="addNewProject" title="Add New Project" style="display:none">
                    <form action="addProject">
                        <div><a>Project Name:</a> <input name="proName" id="proName" class="pro_name"/></div>
                        <div><a>Building:</a> <select name="builidingID" id="buildingID" class="building_id">
                                <c:forEach items="${buildingList}" var="building">
                                    <option value="${building.buildingID}">${building.buildingName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div><a>Complete:</a> <input name="completePercent" class="complete_percent"/></div> 
                        <div><a>Create Date:</a> <input name="createData"  disabled="disabled" class="create_data"/></div> 
                        <div><a>Period:</a> <input name="period" id="building_period" class="building_period"/></div>
                        <div><a>Finish Date:</a> <input type="date" name="finishDate" class="finish_data"/></div>
                        <div class="edit_menu">
                            <div class="btn_edit">
                                <input type="submit" value="Add"/>
                            </div>
                        </div>
                    </form>
                </div>
                <%--For displaying Previous link except for the 1st page --%>
                <c:choose>
                    <c:when test="${currentPage != 1}">
                        <a class="btn_previous_forcus" href="projectDetail?page=${currentPage - 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_previous" href="projectDetail?page=${currentPage - 1}"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</tag:MainTag>

