onload = function () {
    var path = window.location.pathname;

    if(path.match('login')){
        $("#bar ul li:first-child").css("background","#cecfd0");
    }else if(path.match('buildingDetail')){
        $("#bar ul li:nth-child(2)").css("background","#cecfd0");
    }
    
    $("#table").find('tr:nth-child(2)').addClass('selected');
    $("#table tr:nth-child(2) td:last-child").css("background","url(/NTBStampDuty/images/ic_detail_focus.png) no-repeat center");
    $("#table tr").click(function () {
        var index = $("tr").index(this);
        $(this).addClass('selected').siblings().removeClass('selected');
        $("#table tr td:last-child").css("background","url(/NTBStampDuty/images/ic_detail.png) no-repeat center");
        $("#table tr:nth-child("+(index+1)+") td:last-child").css("background","url(/NTBStampDuty/images/ic_detail_focus.png) no-repeat center");
    });
}