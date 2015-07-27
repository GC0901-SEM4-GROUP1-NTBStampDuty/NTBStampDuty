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
    <link href="css/land_page_style.css" rel="stylesheet" type="text/css"/>
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

        function getLandDetails(id) {
            $.ajax({
                type: 'POST',
                url: "getLandDetail",
                data: {"landID": id},
                success: function (data) {
                    $.each(data, function (index, land) {
                        $('.build_type').val(land.buildingTypes);
                        document.getElementById("landID").value = id;
                        document.getElementById("land_size").value = land.size;
                        document.getElementById("land_address").value = land.address;
                        document.getElementById("price").value = land.price;
                        document.getElementById("img").value = land.img;
                        document.getElementById("status").value = land.available_status;
                    });
                }
            });
            $('.landDetails').dialog({modal: true, show: 'fade', hide: 'drop'});
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
<!--                <div class="btn_delete">
                    <input type="submit" value="Delete"  onclick="submitter('deleteLand')"/>
                </div>-->
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
                        <th style="width: 14%">Built Status</th>
                        <th style="width: 14%">Price</th>
                        <th style="width: 14%">Detail</th>
                    </tr>
                    <c:forEach items="${landList}" var="land">
                        <tr id="land">
                            <td>${land.landID}</td>
                            <td>${land.size}</td>
                            <td>${land.address}</td>
                            <td>${land.buildingTypes}</td>
                            <td>${land.available_status}</td>
                            <td>${land.price}</td>
                            <td><input type="submit" value="Show detail" onclick="getLandDetails(${land.landID});
                                    getName(this)"/>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
                <div class="landDetails" class="ui-dialog" title="Land Detail" class="ui-dialog-content" style="display:none;">
                    <form action="editLand" method="POST">
                        <div><a>Land Size:</a> <input id="land_size" name="land_size" class="land_size" value="${land.size}"/></div>
                        <div><a>Land Address:</a> <input id="land_address" name="land_address" class="land_address" value="${land.address}"/></div>                                                      
                        <div><a>Building Types:</a> <select name="typeColumn" id="buildingTypes" class="build_type">
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type.id}">${type.typeName}</option>
                                </c:forEach>
                            </select></div> 
                        <div><a>Price:</a> <input id="price" type="number" name="land_price" class="land_price" value="${land.price}"/></div>
                        <div><input type="hidden" name="landID" id="landID" /></div>
                        <div class="edit_menu">
                            <div class="btn_edit">
                                <input type="submit" value="Save" />
                            </div>
                        </div>
                    </form>
                </div>
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

