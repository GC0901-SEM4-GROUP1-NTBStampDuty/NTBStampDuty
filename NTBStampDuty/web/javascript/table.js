onload = function () {
    $("#land").find('tr:nth-child(2)').addClass('selected');
    $("#land tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });
}