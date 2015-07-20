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
    <link href="css/project_detail_styple.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>    
</head>
<tag:MainTag>
    <div id="main_body">
        <div class="header">
            <div class="edit_menu">
                <div class="btn_new">
                    <input type="submit" value="New" onclick="submitter('addNewLand')"/>
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
                    <div class="project_container" >
                        <form action="editProject" class="editForm" method="post">
                            <div class="projectImage">
                                <img src="images/ic_none_image.png" width="195px" height="250px"/>
                            </div>
                            <div class="projectDetail">
                                <p>Project ID:</p>                   
                                <p>Project Name:</p> 
                                <p>Period: </p>
                                <p>Created Date:</p>
                                <p>Finish Date:</p>
                                <p>Complete Percent: </p>
                            </div>

                            <div class="proDetail">
                                <input type="hidden" value="${project.projectID}" name="editID"/>
                                <p>${project.projectID}</p>
                                <p><input type="text" value="${project.projectName}" name="projName"  maxlength="30" required="true"/></p> 
                                <p><input type="text" readonly="true" value="${project.period}" name="period" required="true"/></p>
                                <p><input type="text" value="${project.createdDate}" name="createdDate" maxlength="10" required="true"/></p>
                                <p><input type="text" value="${project.finishDate}" name="finishDate"  maxlength="10" required="true"/></p>
                                <p><input type="number" value="${project.completePercent}" name="completePercent"  maxlength="4" required="true"/></p>
                            </div>
                            <div class="buildingDetail">
                                <div class="buildingDetailLeft">
                                    <p>Building Id:</p>
                                    <p>Land Id:</p>
                                    <p>Building Name:</p>
                                    <p>Building Type:</p>
                                </div>
                                <div class="buildDetailLeft">
                                    <p>${building.buildingID}</p>
                                    <p>${building.landID}</p>
                                    <p>${building.buildingName}</p>
                                    <p>${building.buildingType}</p>
                                </div>
                                <div class="buildingDetailRight">
                                    <p>Floors number:</p>
                                    <p>Rooms number:</p>
                                    <p>Houses number:</p>
                                    <p>Shops number:</p>                  
                                </div>
                                <div class="buildDetailRight">
                                    <p>${building.floors}</p>
                                    <p>${building.rooms}</p>
                                    <p>${building.houses}</p>
                                    <p>${building.shops}</p>                  
                                </div>
                            </div>
<!--                            <div id="progress-bar-container">
                                <div align="center" class="percent">${project.completePercent}%</div>
                                <div style="width:${project.completePercent}%;background-image: linear-gradient(to top, #9ACD00, #9ACD00); height:30px;">
                                </div>
                            </div>-->
                            <p><input type="submit" class="saveEdit" value="Save"/></p>
                        </form>
                    </div>          
                </table>



            </div>
            <div class="paging_size">

            </div>
        </div>



    </tag:MainTag>

