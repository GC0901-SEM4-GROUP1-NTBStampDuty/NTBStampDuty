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
    <link href="css/land_page_styple.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script src = "javascript/jquery.js" type="text/javascript"></script>
    <script>
        function submitter(btn) {
            $('.' + btn).dialog({modal: true, show: 'fade', hide: 'drop'});
        }

        function getLocationPrice() {
            var location = document.getElementById("landLocation").value;
            var size = document.getElementById("landSize").value;
            var price = document.getElementById(location).value;
            document.getElementById("unitPrice").value = price;
            document.getElementById("totalPrice").value = size * price;
        }

        $(document).ready(function () {
            $("#landSize").keyup(function () {
                var size = $("#landSize").val();
                var price = $("#unitPrice").val();
                $("#totalPrice").val(size * price);
            });
        });
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
                <div class="btn_delete">
                    <input type="submit" value="Delete"  onclick="submitter('deleteLand')"/>
                </div>
            </div>
            <div class="search_menu">
                <form class="form_search" action="searchLand" method="post">
                    <select name="searchColumn" id="searchColumn">
                        <option value="LandID">Land ID</option>
                        <option value="Size">Size</option>
                        <option value="BuildingType">Building Type</option>
                    </select>
                    <input class="txt_search" type="text" name="searchValue" maxlength="20"/>
                    <input class="btn_search" type="submit" value=""/>
                </form>
            </div>
        </div>

        <div class="detail_bar">
            <div class="table_container">
                <div class="row_land">
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                </div>
                <table class="table_land" id="table">
                    <tr>
                        <th style="width: 6%">Land ID</th>
                        <th style="width: 6%">Size</th>
                        <th style="width: 32%">Address</th>
                        <th style="width: 14%">Building Types</th>
                        <th style="width: 14%">Price</th>
                        <th style="width: 14%">Built Status</th>
                        <th style="width: 14%">Detail</th>
                    </tr>
                    <c:forEach items="${landList}" var="land">
                        <tr id="land">
                            <td>${land.landID}</td>
                            <td>${land.size}</td>
                            <td>${land.addressID}</td>
                            <td>${land.buildingTypes}</td>
                            <td>${land.price}</td>
                            <td>${land.buildStatus}</td>
                            <td><input type="submit" value="Show detail" onclick="submitter(${land.landID});
                                    getName(this)"/>
                            </td>
                        </tr>
                        <div class="${land.landID}" class="ui-dialog" title="Land Detail" class="ui-dialog-content" style="display:none;">
                            <div><a>Land Size:</a> <input name="land_size" class="land_size" value="${land.size}"/></div>
                            <div><a>Land Address:</a> <input name="land_address" class="land_address" value="${land.img}"/></div>                                                      
                            <div><a>Build Status:</a> <input name="land_status" class="land_status" value="${land.buildStatus}"/></div> 
                            <div><a>Building Type:</a> <input name="land_type" class="land_type" value="${land.buildingTypes}"/></div> 
                            <div><a>Price:</a> <input name="land_price" class="land_price" value="${land.price}"/></div>
                            <div><a>Building Plan:</a> <input name="land_plan" class="land_plan" value="${land.buildingPlan}"/></div>                          
                            <div><a>Image:</a> <input name="land_image" class="land_image" value="${land.img}"/></div>
                            <div><img src="images/ic_none_image.png" width="145px" height="180px"/></div>
                            <div></div>
                            <div class="edit_menu">
                                <div class="btn_edit">
                                    <input type="submit" value="Save" />
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </table>
                <div class="addNewLand" title="Add New Land" style="display:none">
                    <form action="addLand" method="POST">
                        <!--<div><a>Land Name:</a> <input name="landName" id="landName" class="land_name"/></div>-->
                        <div><a>Land Size:</a> <input name="land_size" class="land_size" value="${land.size}"/></div>
                            <div><a>Land Address:</a> <input name="land_address" class="land_address" value="${land.img}"/></div>                                                      
                            <div><a>Build Status:</a> <input name="land_status" class="land_status" value="${land.buildStatus}"/></div> 
                            <div><a>Building Type:</a> <input name="land_type" class="land_type" value="${land.buildingTypes}"/></div> 
                            <div><a>Price:</a> <input name="land_price" class="land_price" value="${land.price}"/></div>
                            <div><a>Total Price:</a> <input name="land_total" disabled="true" class="land_total"/></div>                          
                            <div><a>Image:</a> <input name="land_image" class="land_image" value="${land.img}"/></div>
                            <div><img src="images/ic_none_image.png" width="145px" height="180px"/></div>
                            <div></div>
                        <div class="edit_menu">
                            <div class="btn_edit">
                                <input type="submit" value="Add"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="deleteLand" title="Delete Land" style="display:none">
                    <form>
                        <div>Are you sure to delete this land ?</div>
                        <div class="edit_menu">
                            <div class="btn_delete">
                                <input type="submit" value="Yes"/>
                            </div>
                        </div>
                    </form>
                </div>
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

