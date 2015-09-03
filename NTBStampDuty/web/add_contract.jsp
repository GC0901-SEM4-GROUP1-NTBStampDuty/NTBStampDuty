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
    <title>Project list</title>
    <link href="css/project_page_style.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="css/add_contract_styple.css" rel="stylesheet" type="text/css"/>
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
                    <input class="txt_search" type="text" name="searchValue" maxlength="20"/>
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
                        <th style="width: 100%">Add Contract</th>
                    </tr> 
                </table>

                <form id="myForm" style="margin-bottom: 0px" action="getProjectDetail" method="post">
                    <input type="hidden" name="projectID"/>
                    <div class="project_container" >
                        <div class="customerLeft">
                            <p>Full Name:</p>                        
                            <p>Phone: </p>
                            <p>Address:</p>
                        </div>
                        <div class="cusInsertLeft">
                            <p><input id="customerName" name="customerName" class="customer_name"/></p> 
                            <p><input id="customerPhone" name="customerPhone" class="customer_phone"/></p> 
                            <p><input id="customerAddress" name="customerAddress" class="customer_address"/></p> 
                        </div>
                        <div class="customerRight">
                            <p>Age:</p>                   
                            <p>Gender:</p>     
                            <p>Email:</p>
                        </div>
                        <div class="cusInsertRight">
                            <p><select name="customerAge" id="customerAge" class="customer_age">
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type.id}">${type.typeName}</option>
                                    </c:forEach>
                                </select>
                            </p> 
                            <p><input id="customerGender" name="customerGender" class="customer_gender"/></p> 
                            <p><input id="customerEmail" name="customerEmail" class="customer_email"/></p> 
                        </div>
                        <div class="projectLeft">
                            <p>Project Name</p>                        
                            <p>Period: </p>
                            <p>Created Date:</p>
                            <p>Completed:</p>
                        </div>
                        <div class="proInsertLeft">
                            <p><select name="projectName" id="projectName" class="project_name">
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type.id}">${type.typeName}</option>
                                    </c:forEach>
                                </select></p> 
                            <p><input id="projectPeriod" name="projectPeriod" class="project_period"/></p> 
                            <p><input id="projectCreate" name="projectCreate" class="project_create"/></p> 
                            <p><input id="projectComplete" name="projectComplete" class="project_complete"/></p> 
                        </div>
                        <div class="projectRight">
                            <p>Building Id:</p>                        
                            <p>Building Address: </p>
                            <p>Finish Date:</p>
                            <p>Building Type:</p>
                        </div>
                        <div class="proInsertRight">
                            <p><input id="buildingId" name="BuildingId" class="building_id"/></p> 
                            <p><input id="buildingAddress" name="buildingAddress" class="building_address"/></p> 
                            <p><input id="projectFinish" name="projectFinish" class="project_finish"/></p> 
                            <p><input id="buildingType" name="buildingType" class="building_type"/></p> 
                        </div>
                        <div class="roomLeft">
                            <p>Room Id:</p>                        
                            <p>Room Type: </p>
                            <p>Expected Price:</p>
                            <p>Deposit:</p>
                        </div>
                        <div class="roomInsertLeft">
                            <p><select name="roomId" id="roomId" class="room_id">
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type.id}">${type.typeName}</option>
                                    </c:forEach>
                                </select></p> 
                            <p><input id="roomType" name="roomType" class="room_type"/></p> 
                            <p><input id="expected" name="expected" class="expected"/></p> 
                            <p><input id="deposit" name="deposit" class="deposit"/></p> 
                        </div>
                        <div class="roomRight">
                            <p>Size:</p>                        
                            <p>Floor: </p>
                            <p>Real Price:</p>
                            <p>Total Price:</p>
                        </div>
                        <div class="proInsertRight">
                            <p><input id="roomSize" name="roomSize" class="room_size"/></p> 
                            <p><input id="roomFloor" name="roomFloor" class="room_floor"/></p> 
                            <p><input id="real" name="real" class="real"/></p> 
                            <p><input id="total" name="total" class="total"/></p> 
                        </div>
                    </div>
                </form>

            </div>
            <div class="paging_size">
                <p><input type="submit" class="addContract" value="Save"/></p>
            </div>
        </div>
    </div>
</tag:MainTag>

