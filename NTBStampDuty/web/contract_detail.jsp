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
    <title>Contract Details</title>
    <link href="css/contract_detail_style.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>    
    <script>
        function setWarning(period, status) {
            if (status == 1) {
                $(".period" + period + ' .warning_yellow').css("visibility", "visible");
                blink(1000, ".period" + period + ' .warning_yellow');
            } else if (status == -1) {
                $(".period" + period + ' .warning_red').css("visibility", "visible");
                blink(1000, ".period" + period + ' .warning_red');
            }
        }

        function blink(interval, img) {
            var timer = window.setInterval(function () {
                $(img).css("opacity", "0.1");
                window.setTimeout(function () {
                    $(img).css("opacity", "1");
                }, 300);
            }, interval);
            window.setTimeout(function () {
                clearInterval(timer);
            }, 1000000);
        }
    </script>
</head>
<tag:MainTag>
    <body onload="setWarning(${project.period},${status})">
        <div id="main_body">
            <div class="header">
                <div class="edit_menu">
                    <div class="btn_edit">
                        <form action="editProject">
                            <input type="hidden" value="${project.projectID}" name="editID"/>
                            <input type="submit" value="Edit"/>
                        </form>
                    </div>
                </div>
                <div class="search_menu">
                    <form class="form_search" action="" method="post">
                        <select name="searchColumn" id="searchColumn">
                            <option value="Username">Username</option>
                            <option value="Role">Role</option>
                        </select>
                        <input class="txt_search" type="text" name="searchValue"  maxlength="20"/>
                        <input class="btn_search" type="submit" value=""/>
                    </form>
                </div>
            </div>
            <div class="detail_bar">
                <div class="table_container">
                    <div class="row_user">
                    </div>
                    <table class="table_contract" id="table">
                        <tr>
                            <th style="width: 100%" > Contract Details</th>
                        </tr> 
                        <div class="contract_container" >
                            <div class="projectImage">
                                <img src="images/ic_none_image.png" width="170px" height="230px"/>
                            </div>
                            <div class="projectDetail">
                                Contract ID:                   
                                <p>Customer:</p>
                                <p>Room ID:</p>
                                <p>Created Date:</p>
                                <p>Total Payment:</p>
                                <p>Total Paid:</p>
                                <p>Total Due:</p>
                            </div>
                            <div class="proDetail">
                                ${contract.contractId}
                                <p>${contract.username}</p>
                                <p>${contract.roomId}</p>
                                <p>${contract.createdDate}</p>
                                <p>${payment} USD</p>
                                <p>${paid} USD</p>
                                <p>${due} USD</p>
                            </div>
                        </div>          
                    </table>

                    <div class="payment_container">
                        <table align="center" class="table_payment" id="table" >
                            <thead>
                                <tr>
                                    <th style="width: 20%">No.</th>                   
                                    <th style="width: 20%">Contract ID</th>
                                    <th style="width: 30%">Date</th>
                                    <th style="width: 30%">Paid</th>
                                </tr>
                            </thead>
                        </table>
                        <div class="payment_value">
                            <table align="center" class="table_payment" id="table" >
                                <c:forEach items="${listPayment}" var="p" varStatus="row">
                                    <tr>
                                        <td style="width:20%">${row.count}</td>
                                        <td style="width: 20%">${p.contractId}</td>
                                        <td style="width: 32%">${p.paymentTime}</td>
                                        <td style="width: 30%">${p.paid}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div id="progress-bar-container">
                        <a class="m1">${period_money}USD</a>
                        <a class="m2">${period_money}USD</a>
                        <a class="m3">${period_money}USD</a>
                        <div class="progress-bar">
                            <div align="center" class="percent">${contract.totalPaid/contract.totalPayment*100}%</div>
                            <div class="period1">
                                <img class="warning_yellow" src="images/ic_warning_yellow.png">
                                <img class="warning_red" src="images/ic_warning_red.png">   
                            </div>
                            <div class="period2">
                                <img class="warning_yellow" src="images/ic_warning_yellow.png">
                                <img class="warning_red" src="images/ic_warning_red.png">
                            </div>
                            <div class="period3">
                                <img class="warning_yellow" src="images/ic_warning_yellow.png">
                                <img class="warning_red" src ="images/ic_warning_red.png">
                            </div>
                            <div style="width:${contract.totalPaid/contract.totalPayment*100}%;background-image: linear-gradient(to top, #9ACD00, #9ACD00); height:30px;">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="paging_size">

                </div>
            </div>
    </body>


</tag:MainTag>

