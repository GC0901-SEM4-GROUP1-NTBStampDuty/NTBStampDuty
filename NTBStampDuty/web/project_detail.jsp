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
    <title>Project Details</title>
    <link href="css/project_page_styple.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>    
</head>
<tag:MainTag>
    <div id="main_body">
        <div class="header">
            <div class="edit_menu">
                <div class="btn_new">
                    <input type="submit" value="New" onclick="submitter('addNewLand')"/>
                </div>
                <!--                <form class="btn_edit">
                                    <input type="submit" value="Edit" />
                                </form>-->
                <div class="btn_delete">
                    <input type="submit" value="Delete"  onclick="submitter('deleteLand')"/>
                </div>
            </div>
            <div class="search_menu">
                <form class="form_search" action="" method="post">
                    <select name="searchColumn" id="searchColumn">
                        <option value="Username">Username</option>
                        <option value="Role">Role</option>
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
                        <th style="width: 100%"> Project Details</th>
                    </tr> 
                </table>

                <div class="project_container" >
                    <p>Project ID : ${project.projectID}</p>                    
                    <p>Project Name : ${project.projectName}</p>     
                    <p>Building ID: ${project.buildingId}</p>
                    <p>Building Name : ${project.buildingName}</p>
                    <p>Complete percent : ${project.completePercent}</p>                    
                    <p>Created Date :  ${project.createdDate}</p>
                    <p>Finish Date :  ${project.finishDate}</p>  
                    <p>Period :  ${project.period}</p>
                    <br>
                    <br>
                    <p>Building Id : ${building.buildingID}</p>
                    <p>Land Id : ${building.landID}</p>
                    <p>Building Name : ${building.buildingName}</p>
                    <p>Building Type : ${building.buildingType}</p>
                    <p>Floors number : ${building.floors}</p>
                    <p>Rooms number : ${building.rooms}</p>
                    <p>Houses number : ${building.houses}</p>
                    <p>Shops number : ${building.shops}</p>
                    <p>Date constructed : ${building.dateContructed}</p>
                </div>                

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



    </tag:MainTag>

