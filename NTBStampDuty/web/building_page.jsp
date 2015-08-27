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
    <title>Building Details</title>
    <link href="css/build_page_style.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>    
    <script src = "javascript/jquery.js" type="text/javascript"></script>
    <script>
        function submitter(btn) {
            $('.' + btn).dialog({modal: true, show: 'fade', hide: 'drop'});
        }

        function getBuildingDetail(id) {
            $.ajax({
                type: 'POST',
                url: "getBuildingDetail",
                data: {"buildingID": id},
                success: function (data) {
                    $.each(data, function (index, building) {
                        $('.land_name').val(building.landID);
                        $('.build_type').val(building.buildingTypeID);
                        var house = document.getElementById("housesLink");
                        house.href = "/NTBStampDuty/getRoomDetail?"+id+"-1";
                        var shop = document.getElementById("shopsLink");
                        shop.href = "/NTBStampDuty/getRoomDetail?"+id+"-2";
                        var office = document.getElementById("officesLink");
                        office.href = "/NTBStampDuty/getRoomDetail?"+id+"-3";
                        document.getElementById("buildID").value = id;
                        document.getElementById("buildingName").value = building.buildingName;
                        document.getElementById("floors").value = building.floors;
                        document.getElementById("rooms").value = building.rooms;
                        document.getElementById("houses").value = building.houses;
                        document.getElementById("shops").value = building.shops;
                    });
                }
            });
            $('.buildingDetail').dialog({modal: true, show: 'fade', hide: 'drop'});
        }

        function addLand() {
            var land = new Object();
            land.size = $('#landSize').val();
            land.landAddress = $('#landAddress').val();
            land.buildingType = $('#buildingTypes').val();
            land.landPrice = $('#landPrice').val();
            $.ajax({
                url: "addLandAjax",
                type: 'POST',
                data: {"land": JSON.stringify(land)},
                success: function (data) {
                    document.getElementById("landLocationDetail").innerHTML = "";
                    document.getElementById("landLocationAddNew").innerHTML = "";
                    var selectListDetail = document.getElementById("landLocationDetail");
                    var selectListAddNew = document.getElementById("landLocationAddNew");
                    $.each(data, function (index, land) {
                        var option = document.createElement("option");
                        option.text = land.address;
                        option.value = land.landID;
                        var option2 = document.createElement("option");
                        option2.text = land.address;
                        option2.value = land.landID;
                        selectListDetail.add(option);
                        selectListAddNew.add(option2);
                    });
                    $('.addNewLand').dialog('close');
                }
            });
        }
    </script>
</head>
<tag:MainTag >
    <div id="main_body">
        <div class="header">
            <div class="edit_menu">
                <div class="btn_new">
                    <input type="submit" value="New" onclick="submitter('addNewBuilding')"/>
                </div>
                <!--                <form class="btn_edit">
                                    <input type="submit" value="Edit" />
                                </form>-->
<!--                <div class="btn_delete">
                    <input type="submit" value="Delete"  onclick="submitter('deleteBuilding')"/>
                </div>-->
            </div>
            <div class="search_menu">
                <form class="form_search" action="searchBuilding" method="post">
                    <select name="searchColumn">
                        <option value="BuildID">Building ID</option>
                        <option value="BuildName">Building Name</option>
                        <!--                        <option value="BuildType">Building Type</option>-->
                    </select>
                    <input class="txt_search" type="text" name="searchValue" maxlength="20"/>
                    <input class="btn_search" type="submit" value="" />
                </form>
            </div>
        </div>

        <div class="detail_bar">
            <div  class="table_container">
                <div class="row_build">
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                    <a></a>
                </div>
                <table class="table_build" id="table">
                    <tr>
                        <th style="width: 8%">Building Id</th>
                        <th style="width: 30%">Building Name</th>
                        <th style="width: 14%">Building Type</th>
                        <th style="width: 10%">Floors</th>
                        <th style="width: 14%">Total Room</th>
                        <th style="width: 10%">Completed</th>
                        <th style="width: 14%">Detail</th>
                    </tr>
                    <c:forEach items="${buildingList}" var="building">
                        <tr>
                            <td>${building.buildingID}</td>
                            <td>${building.buildingName}</td>
                            <td>${building.buildingType}</td>
                            <td>${building.floors}</td>
                            <td>${building.totalRoom}</td>
                            <td></td>
                            <td><input type="submit" value="Show detail" onclick="getBuildingDetail(${building.buildingID});
                                    getName(this)"/></td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="addNewLand" title="Add New Land" style="display:none">
                    <!--<div><a>Land Name:</a> <input name="landName" id="landName" class="land_name"/></div>-->
                    <div><a>Land Size:</a> <input id="landSize" type="text" name="size" class="land_size" /></div>
                    <div><a>Land Address:</a> <input id="landAddress" type="text" name="address" class="land_address" /></div>                                                      
                    <div><a>Building Types:</a> </a> <select name="typeColumn" id="buildingTypes" class="build_type">
                            <c:forEach items="${typeList}" var="type">
                                <option value="${type.id}">${type.typeName}</option>
                            </c:forEach>
                        </select></div> 
                    <div><a>Price:</a> <input id="landPrice" type="number" name="price" class="land_price" /></div> 
                    <!--<div><a>Image:</a> <input type="text" name="img" class="land_price" /></div>-->
                    <!--                    <div><a>Total Price:</a> <input name="land_total" disabled="true" class="land_total"/></div>                          -->
                    <!--<div><a>Image:</a> <input name="land_image" class="land_image" value=""/></div>-->
                    <!--                    <div><img src="images/ic_none_image.png" width="145px" height="180px"/></div>-->
                    <div></div>
                    <div class="edit_menu">
                        <div class="btn_edit">
                            <input type="submit" value="Add" onclick="addLand()"/>
                        </div>
                    </div>
                </div>     
                <div class="buildingDetail" class="ui-dialog" title="Building Detail" class="ui-dialog-content" style="display:none;">
                    <form action="editBuilding" method="POST" enctype="multipart/form-data">    
                        <div><a>Building:</a> <input id="buildingName" class="build_name" name="buildingName"/></div> 
                        <div><a>Land:</a> <select name="landColumn" id="landLocationDetail" class="land_name">
                                <c:forEach items="${landList}" var="land">
                                    <option value="${land.landID}">${land.address}</option>
                                </c:forEach>
                            </select>
                            <img class="plus_navigation" src="images/ic_plus.png"/>
                        </div>
                        <div><a>Building Type:</a> <select name="typeColumn" id="landLocation" class="build_type">
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type.id}">${type.typeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div><a>Floors: </a> <input id="floors" class="floors" name="floors"/><img class="next_navigation" style="visibility: hidden" src="images/ic_show.png"/></div> 
                        <div><a>Houses: </a> <input id="houses" class="houses" name="houses"/>
                            <a id="housesLink"><img class="next_navigation" src="images/ic_show.png"/></a>    
                        </div>  
                        <div><a>Offices: </a> <input id="rooms" class="rooms" name="rooms"/>
                            <a id="officesLink"><img class="next_navigation" src="images/ic_show.png"/></a>    
                        </div>
                        <div><a>Shops: </a> <input id="shops" class="shops" name="shops"/>
                            <a id="shopsLink"><img class="next_navigation" src="images/ic_show.png"/></a>    
                        </div>  
                        <div><a>Image:</a> <input type="file" name="buildImage" class="build_image" accept="image/gif, image/jpeg, image/png"/></div>
                        <div style="margin-bottom: 4px;"><img src="images/ic_none_image.png" width="145px" height="180px"/></div>
                        <div><input type="hidden" name="buildingID" id="buildID" /></div>
                        <div class="edit_menu">
                            <div class="btn_edit">
                                <input type="submit" value="Save" />
                            </div>
                        </div>
                    </form>
                </div>
                <div class="addNewBuilding" title="Add New Building" style="display:none">
                    <form action="addBuilding" method="post" enctype="multipart/form-data">
                        <div><a>Building:</a> <input name="build_name" id="buildName" class="build_name"/></div>
                        <div><a>Land:</a> <select name="landColumn" id="landLocationAddNew" class="land_name">
                                <c:forEach items="${landList}" var="land">
                                    <option value="${land.landID}">${land.address}</option>
                                </c:forEach>
                            </select>
                            <img class="plus_navigation" src="images/ic_plus.png" onclick="submitter('addNewLand')"/>
                        </div>
                        <div><a>Building Type:</a> <select name="typeColumn" id="landLocation" class="build_type">
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type.id}">${type.typeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div><a>Floors:</a> <input name="floors" class="floors"/><img class="next_navigation" style="visibility: hidden" src="images/ic_show.png"/></div> 
                        <div><a>Houses:</a> <input name="houses" class="houses"/><img class="next_navigation" style="visibility: hidden" src="images/ic_show.png"/></div> 
                        <div><a>Rooms:</a> <input name="rooms" class="rooms"/><img class="next_navigation" style="visibility: hidden" src="images/ic_show.png"/></div>
                        <div><a>Shops:</a> <input name="shops" class="shops"/><img class="next_navigation" style="visibility: hidden" src="images/ic_show.png"/></div>
                        <div><a>Image:</a> <input type="file" name="buildImage" class="build_image" accept="image/gif, image/jpeg, image/png"/></div>
                        <div style="margin-bottom: 4px;"><img src="images/ic_none_image.png" width="145px" height="180px"/></div>
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


