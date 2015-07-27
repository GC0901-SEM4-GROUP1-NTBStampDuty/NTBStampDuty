onload = function () {
    var path = window.location.pathname;
    if (path.match('projectDetail') || path.match('editProject') || path.match('getProjectDetail') || path.match('login')) {
        $("#bar ul li:first-child").css("background", "#cecfd0");
    } else if (path.match('buildingDetail') || path.match('searchBuild')) {
        $("#bar ul li:nth-child(2)").css("background", "#cecfd0");
    } else if (path.match('landDetail') || path.match('searchLand')) {
        $("#bar ul li:nth-child(3)").css("background", "#cecfd0");
    } else if (path.match('contractDetail') || path.match('searchContract') || path.match('getContractDetail')) {
        $("#bar ul li:nth-child(4)").css("background", "#cecfd0");
    } else if (path.match('userDetail') || path.match('searchUser')) {
        $("#bar ul li:nth-child(5)").css("background", "#cecfd0");
    }

    $(".period1").hover(function () {
        $(".date_start").css("visibility", "visible");
        $(".date_period1").css("visibility", "visible");
    }, function () {
        $(".date_start").css("visibility", "hidden");
        $(".date_period1").css("visibility", "hidden");
    });
    $(".period2").hover(function () {
        $(".date_period1").css("visibility", "visible");
        $(".date_period2").css("visibility", "visible");
    }, function () {
        $(".date_period1").css("visibility", "hidden");
        $(".date_period2").css("visibility", "hidden");
    });
    $(".period3").hover(function () {
        $(".date_period2").css("visibility", "visible");
        $(".date_period3").css("visibility", "visible");
    }, function () {
        $(".date_period2").css("visibility", "hidden");
        $(".date_period3").css("visibility", "hidden");
    });

    $("#table").find('tr:nth-child(2)').addClass('selected');
    if (!path.match("getContractDetail")) {
    $("#table tr:nth-child(2) td:last-child").css("background", "url(/NTBStampDuty/images/ic_detail_focus.png) no-repeat center");
    }
    $("#table tr").click(function () {

        var index = $("tr").index(this);
        $(this).addClass('selected').siblings().removeClass('selected');
        if (!path.match("getContractDetail")) {
            $("#table tr td:last-child").css("background", "url(/NTBStampDuty/images/ic_detail.png) no-repeat center");
            $("#table tr:nth-child(" + (index + 1) + ") td:last-child").css("background", "url(/NTBStampDuty/images/ic_detail_focus.png) no-repeat center");
        }
    });

    $('.txt_search').focus(
            function () {
                $('.search_menu form').css("outline", "1px solid #CF2525");
                $('.search_menu form').css("border", "1px solid #CF2525");
            });

    $('.txt_search').blur(
            function () {
                $('.search_menu form').css("outline", "none");
                $('.search_menu form').css("border", "1px solid #CBCBCB");
            });

    $('.txt_search').attr("placeholder", $('#searchColumn option:selected').text());
    $("#searchColumn").change(function () {
        var option = $('#searchColumn option:selected').text();
        $('.txt_search').attr("placeholder", option);
    });
}