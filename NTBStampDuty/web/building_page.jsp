f<%-- 
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
    <link href="css/build_page_styple.css" rel="stylesheet" type="text/css"/>
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
            land.name = $('#landName').val();
            land.size = $('#landSize').val();
            land.locationId = $('#landLocationID').val();
            land.type = $('#landType').val();
            land.img = $('#img').val();
            $.ajax({
                url: "addLandAjax",
                type: 'POST',
                data: {"land": JSON.stringify(land)},
                success: function (data) {
                    
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
                <div class="btn_delete">
                    <input type="submit" value="Delete"  onclick="submitter('deleteBuilding')"/>
                </div>
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

                    <div><a>Land Name:</a> <input name="landName" id="landName" class="land_size"/></div>
                    <div><a>Land Size:</a> <input name="landSize" id="landSize" class="land_size"/></div>
                    <div><a>Land Address:</a> <select name="landAddress" id="landLocationID" class="land_address" onchange="getLocationPrice()">
                            <option value="0">Choose an address</option>
                            <c:forEach items="${locationList}" var="location">
                                <option value="${location.addressID}">${location.addressName}</option>
                            </c:forEach>
                        </select>
                        <img class="plus_navigation" src="images/ic_plus.png"/>                            
                    </div>                        
                    <div><a>Building Type:</a> <input name="buildingType" id="landType" class="land_type" value="${land.buildingTypes}"/></div> 
                    <div><a>Image:</a> <input name="landImage" id="img" class="land_image" value="${land.img}"/></div>
                    <div style="margin-bottom: 4px;"><img src="images/ic_none_image.png" width="145px" height="180px"/></div>
                    <div class="edit_menu">
                        <div class="btn_edit">
                            <input type="submit" value="Add" onclick="addLand()"/>
                        </div>
                    </div>

                </div>
                <div class="buildingDetail" class="ui-dialog" title="Building Detail" class="ui-dialog-content" style="display:none;">
                    <form action="editBuilding" method="POST" enctype="multipart/form-data">    
                        <div><a>Building:</a> <input id="buildingName" class="build_name" name="buildingName"/></div> 
                        <div><a>Land:</a> <select name="landColumn" id="landLocation" class="land_name">
                                <c:forEach items="${landList}" var="land">
                                    <option value="${land.landID}">${land.addressID}</option>
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
                        <div><a>Floors: </a> <input id="floors" class="floors" name="floors"/><img class="next_navigation" src="images/ic_show.png"/></div> 
                        <div><a>Houses: </a> <input id="houses" class="houses" name="houses"/><img class="next_navigation" src="images/ic_show.png"/></div>  
                        <div><a>Rooms: </a> <input id="rooms" class="rooms" name="rooms"/><img class="next_navigation" src="images/ic_show.png"/></div>
                        <div><a>Shops: </a> <input id="shops" class="shops" name="shops"/><img class="next_navigation" src="images/ic_show.png"/></div>  
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
                        <div><a>Land:</a> <select name="landColumn" id="landLocation" class="land_name">
                                <c:forEach items="${landList}" var="land">
                                    <option value="${land.landID}">${land.addressID}</option>
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
                    <input type="submit" value="New" onclick="submitter('addNewLand')"/>
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


