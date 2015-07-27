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
    <link href="css/contract_page_style.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script>
        function submitter(btn) {
            $('.' + btn).dialog({modal: true, show: 'fade', hide: 'drop'});
        }

        function getName(btn) {
            var category = btn.parentElement.parentElement.id;
            return category;
        }

        function getRoom() {
            var projectList = document.getElementById("projectID");
            var projectID = projectList.options[projectList.selectedIndex].value
            $.ajax({
                url: "getRoomAjax",
                type: 'POST',
                data: {"projectID": projectID},
                success: function (data) {
                    document.getElementById("roomIDToAddContract").innerHTML = "";
                    var roomIDToAddContractList = document.getElementById("roomIDToAddContract");
                    $.each(data, function (index, roomItem) {
                        var room = document.createElement("option");
                        room.text = roomItem.roomFloor + "-" + roomItem.roomId;
                        room.value = roomItem.roomId;
                        roomIDToAddContractList.add(room);
                    });
                    $('.addNewLand').dialog('close');
                }
            });
        }

        function getRoomPrice() {
            var roomList = document.getElementById("roomIDToAddContract");
            var roomID = roomList.options[roomList.selectedIndex].value
            $.ajax({
                url: "getRoomPriceAjax",
                type: 'POST',
                data: {"roomId": roomID},
                success: function (data) {
                    $.each(data, function (index, roomItem) {
                        document.getElementById("totalPayment").value = roomItem.roomPrice;
                    });
                    $('.addNewLand').dialog('close');
                }
            });
        }
        
        function submitterAddNew(btn) {
            $("#roomIDToAddContract").chosen();
            $("#customerAddNew").chosen();
            $("#projectID").chosen();
            $('.' + btn).dialog({modal: true, show: 'fade', hide: 'drop'});
        }
    </script>
</head>
<tag:MainTag>
    <div id="main_body">
        <div class="header">
            <div class="edit_menu">
                <div class="btn_new">
                    <input type="submit" value="New" onclick="submitter('addNewContract')"/>
                </div>
                <!--                <form class="btn_edit">
                                    <input type="submit" value="Edit" />
                                </form>-->
                <!--                <div class="btn_delete">
                                    <input type="submit" value="Delete"  onclick="submitter('deleteLand')"/>
                                </div>-->
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
                <div class="row_contract">
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                </div>
                <table class="table_contract" id="table">
                    <tr>
                        <th style="width: 9%">Contract ID</th>
                        <th style="width: 9%">Room ID</th>
                        <th style="width: 17%">Username</th>
                        <th style="width: 17%">Created Date</th>
                        <th style="width: 17%">Paid</th>
                        <th style="width: 17%">Total</th>
                        <th style="width: 14%">Detail</th>
                    </tr>
                    <c:forEach items="${contractList}" var="con">
                        <tr id="land">
                            <td>${con.contractId}</td>
                            <td>${con.roomId}</td>
                            <td>${con.username}</td>
                            <td>${con.createdDate}</td>
                            <td>${con.totalPaid}</td>
                            <td>${con.totalPayment}</td>
                            <td>
                                <form style="margin-bottom: 0px" action="getContractDetail" method="get">
                                    <input type="hidden" name="roomId" value="${con.roomId}"/>
                                    <input type="submit" value="Show Contract"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="addNewContract" title="Add New Contract" style="display:none">
                    <form action="addContract" method="POST">
                        <!--<div><a>Land Name:</a> <input name="landName" id="landName" class="land_name"/></div>-->
                        <div><a>Project name:</a> <select style="width: 170px" onchange="getRoom()" id="projectID" class="roomId">
                                <option value="" disabled="true" selected="true">Choose a project</option>
                                <c:forEach items="${proList}" var="pro">
                                    <option value="${pro.projectID}">${pro.projectName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div><a>Room ID:</a> <select style="width: 170px" onchange="getRoomPrice()" name="roomId" id="roomIDToAddContract" class="roomId">
                            </select>
                            <img class="plus_navigation" src="images/ic_plus.png" onclick="submitter('addNewLand')"/>
                        </div>
                        <div><a>Customer: </a> <select style="width: 170px" name="customer" id="customerAddNew" class="customer">
                                <c:forEach items="${userList}" var="user">
                                    <option value="${user.username}">${user.username}</option>
                                </c:forEach>
                            </select>
                            <img class="plus_navigation" src="images/ic_plus.png" onclick="submitter('addNewLand')"/>
                        </div>
                        <div><a>Date:</a> <input type="date" name="date" class="date" /></div>
                        <div><a>Total Payment:</a> <input type="number" name="payment" class="payment" id="totalPayment" /></div> 
                        <div><a>Deposit:</a> <input type="number" name="deposit" class="deposit" /></div>
                        <div></div>
                        <div class="edit_menu">
                            <div class="btn_edit">
                                <input type="submit" value="Add"/>
                            </div>
                        </div>
                    </form>
                </div>         
            </div>
            <div class="paging_size">
                <%--For displaying Next link --%>
                <c:choose>
                    <c:when test="${currentPage < noOfPages}">
                        <a class="btn_next_forcus" href="contractDretail?page=${currentPage + 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_next" href="contractDretail?page=${currentPage + 1}"></a>
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
                                    <td><a href="contractDretail?page=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </tr>
                </table>

                <%--For displaying Previous link except for the 1st page --%>
                <c:choose>
                    <c:when test="${currentPage != 1}">
                        <a class="btn_previous_forcus" href="contractDretail?page=${currentPage - 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_previous" href="contractDretail?page=${currentPage - 1}"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</tag:MainTag>

