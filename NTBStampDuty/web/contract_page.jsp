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
    </script>
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
                <div class="addNewLand" title="Add New Land" style="display:none">
                    <form action="addLand" method="POST">
                        <!--<div><a>Land Name:</a> <input name="landName" id="landName" class="land_name"/></div>-->
                        <div><a>Land Size:</a> <input type="text" name="size" class="land_size" /></div>
                        <div><a>Land Address:</a> <input type="text" name="address" class="land_address" /></div>                                                      
                        <div><a>Building Types:</a>  <select name="buildingTypes" id="buildingTypes" class="build_type">
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type.id}">${type.typeName}</option>
                                </c:forEach>
                            </select></div> 
                        <div><a>Price:</a> <input type="number" name="price" class="land_price" /></div> 
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
                        <a class="btn_next_forcus" href="userDretail?page=${currentPage + 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_next" href="userDetail?page=${currentPage + 1}"></a>
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
                                    <td><a href="userDetail?page=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </tr>
                </table>

                <%--For displaying Previous link except for the 1st page --%>
                <c:choose>
                    <c:when test="${currentPage != 1}">
                        <a class="btn_previous_forcus" href="userDetail?page=${currentPage - 1}"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn_previous" href="userDetail?page=${currentPage - 1}"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</tag:MainTag>

