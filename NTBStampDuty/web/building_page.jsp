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
                        document.getElementById("landAddress").value = building.landName;
                        document.getElementById("buildingType").value = building.buildingType;
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
        ;
    </script>
</head>
<tag:MainTag>
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
                    <input class="txt_search" type="text" name="searchValue"/>
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
                        <th style="width: 14%">Date Constructed</th>
                        <th style="width: 10%">Completed</th>
                        <th style="width: 14%">Detail</th>
                    </tr>
                    <c:forEach items="${buildingList}" var="building">
                        <tr>
                            <td>${building.buildingID}</td>
                            <td>${building.buildingName}</td>
                            <td>${building.buildingType}</td>
                            <td>${building.floors}</td>
                            <td>${building.dateContructed}</td>
                            <td></td>
                            <td><input type="submit" value="Show detail" onclick="getBuildingDetail(${building.buildingID});
                                    getName(this)"/></td>
                        </tr>
                    </c:forEach>
                    <div class="buildingDetail" class="ui-dialog" title="Building Detail" class="ui-dialog-content" style="display:none;">
                        <div><a>Land Location:</a> <input id="landAddress" class="land_address"/></div>
                        <div><a>Building Type:</a> <input id="buildingType" class="land_address"/></div>
                        <div><a>Building Name:</a> <input id="buildingName" class="land_status"/></div> 
                        <div><a>Floors: </a> <input id="floors" class="land_type"/></div> 
                        <div><a>Rooms: </a> <input id="rooms" class="land_price"/></div>
                        <div><a>Houses: </a> <input id="houses" class="land_plan"/></div>  
                        <div><a>Shops: </a> <input id="shops" class="land_plan"/></div>  
                        <div><a>Image:</a> <input name="land_image" class="land_image"/></div>
                        <div><img src="images/ic_none_image.png" width="145px" height="180px"/></div>
                        <div></div>
                        <div class="edit_menu">
                            <div class="btn_edit">
                                <input type="submit" value="Save" />
                            </div>
                        </div>
                    </div>
                </table>
                <div class="addNewBuilding" title="Add New Building" style="display:none">
                    <form action="addBuilding" method="post" enctype="multipart/form-data">
                        <div><a>Building:</a> <input name="build_name" id="buildName" class="build_name"/></div>
                        <div><a>Land:</a> <select name="landColumn" id="landLocation" class="land_name">
                                <c:forEach items="${landList}" var="land">
                                    <option value="${land.landID}">${land.addressID}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div><a>Type:</a> <select name="typeColumn" id="landLocation" class="build_type">
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type.id}">${type.typeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div><a>Floors:</a> <input name="floors" class="floors"/></div> 
                        <div><a>Houses:</a> <input name="houses" class="houses"/></div> 
                        <div><a>Rooms:</a> <input name="rooms" class="rooms"/></div>
                        <div><a>Shops:</a> <input name="shops" class="shops"/></div>
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


