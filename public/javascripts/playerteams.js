var clickedCyclist;
var teamRow = '<a class="list-group-item" data-cyclist-number="%1$s">Cyclist %1$s<button class="btn-xs btn btn-success" data-cyclist-number="%1$s">Buy</button></a>';//1 param: position (1..9)
var ridersRow = '<a class="list-group-item" data-cyclist-race-id="%d" data-cyclist-number="%s"><span class="name">%s</span>' +
'<button class="btn-xs btn btn-danger modal-button">Sale</button><span class="price">%d</span></a>';//3 params:id,number,name,price

//Click on buy button in my team
$(document).on("click",".list-team button.btn-success",function(event){
    clickedCyclist = Number($(this).parent().attr("data-cyclist-number"));
    $("#modal-player-team").modal("show");
});
//Click on select button in buylist
$(document).on("click","#modal-player-team button.select-button", function(event){
    var row = $(this).parent();
    //Get params
    var cyclistRaceId = Number($(this).parent().attr("data-cyclist-race-id"));
    var name = $(row).find("span.name").text();
    var price = $(row).find("span.price").text();
    //Hide in buylist and show in team
    $(row).hide();
    $(".list-team [data-cyclist-number='" + clickedCyclist +"']").replaceWith(vsprintf(ridersRow,[cyclistRaceId,clickedCyclist,name,price]));
    $("#modal-player-team").modal("hide");
    updateMoney();
});

//Click on sale button in my team
$(document).on("click",".list-team button.btn-danger", function(event){
    var row = $(this).parent();
    var cyclistId = Number($(row).attr("data-cyclist-race-id"));
    clickedCyclist = Number($(row).attr("data-cyclist-number"));
    //Show cyclist in buylist
    $("#modal-player-team a[data-cyclist-race-id="+ cyclistId +"]").show();
    //Replace rider with empty row
    $(".list-team [data-cyclist-number='" + clickedCyclist +"']").replaceWith(vsprintf(teamRow,[clickedCyclist]));
    $("#modal-player-team").modal("hide");
    updateMoney();
});

$(document).on("keyup change","#cyclist-filter",function(event){
    var filterText = $("#cyclist-filter").val().trim().toLowerCase();
    $("#modal-player-team .list-group-item").each(function(){
        var text = $(this).text().toLowerCase();
        if (filterText == "" || text.indexOf(filterText) != -1) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
    hideSelected();
});

$(document).ready(function(){
    updateMoney();
    hideSelected();
});

$(document).on("click","#save-team",function(event) {
    var type;
    var url = window.location.origin + "/playerTeam";
    var data = {};
    if (!isNaN(Number($(this).attr("data-team-id"))) && Number($(this).attr("data-team-id")) != 0) {
        data.id = $(this).attr("data-team-id");
        url = url + "/" + data.id;
        type = "PUT";
    } else {
        data.playerId = $(this).attr("data-player-id");
        data.raceId = $(this).attr("data-race-id");
        type = "POST";
    }
    data.cyclist1 = $(".list-team [data-cyclist-number=1]").attr("data-cyclist-race-id");
    data.cyclist2 = $(".list-team [data-cyclist-number=2]").attr("data-cyclist-race-id");
    data.cyclist3 = $(".list-team [data-cyclist-number=3]").attr("data-cyclist-race-id");
    data.cyclist4 = $(".list-team [data-cyclist-number=4]").attr("data-cyclist-race-id");
    data.cyclist5 = $(".list-team [data-cyclist-number=5]").attr("data-cyclist-race-id");
    data.cyclist6 = $(".list-team [data-cyclist-number=6]").attr("data-cyclist-race-id");
    data.cyclist7 = $(".list-team [data-cyclist-number=7]").attr("data-cyclist-race-id");
    data.cyclist8 = $(".list-team [data-cyclist-number=8]").attr("data-cyclist-race-id");
    data.cyclist9 = $(".list-team [data-cyclist-number=9]").attr("data-cyclist-race-id");
    $.ajax({
        url: url,
        type: type,
        data: data
    }).done(function(){
        alert("Equipo enviado correctamente");
    }).fail(function(a,b,c){
        alert(a + b + c);
    });
});

//Update resting money and resting riders
var updateMoney = function() {
    var total = $("#total").val();
    $("#rest-cyclists").val($(".list-team .btn-success").length);
    var spent = 0;
    $(".list-team span.price").each(function(){
        spent = spent + Number($(this).text());
    });
    $("#rest").val(total-spent);
    if ($(".list-team .btn-success").length == 0) {
        //None buy buttons -> All riders selected
        $("#save-team").prop("disabled",false);
    } else {
        //Missing riders to select
        $("#save-team").prop("disabled",true);
    }
}

//Hide in buylist all selected riders
var hideSelected = function() {
    var ids = [];
    $(".list-team a").each(function(index,value){
        ids.push($(this).attr("data-cyclist-race-id"));
    });
    $("#modal-player-team div.list-group a").each(function(){
        if (ids.indexOf($(this).attr("data-cyclist-race-id")) != -1) {
            $(this).hide();
        }
    });
}