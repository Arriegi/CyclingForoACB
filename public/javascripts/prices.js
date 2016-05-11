var teams={};
var raceId = $("h1.title").attr("data-race-id");
var allCyclists;
var selectedCyclists;
var allCyclistsTable;
var selectedCyclistsTable;
$.when($.getJSON(window.location.origin + "/teams").then(function(teamData){
    $.each(teamData,function(index,value) {
        teams[value.id] = value.name;
    });
    readData();
}));

$(document).on("click","a.select-cyclist",function(event){
    var cyclistId = $(this).attr("data-cyclist-id");
    tr = $(this).closest("tr");
    var name = $(tr).find(":nth-child(2)").text();
    var team = $(tr).find(":nth-child(3)").text();
    $("#modal-price-name").val(name);
    $("#modal-price-team").val(team);
    $("#modal-price-price").val(50);
    $("#modal-price button.save").attr("data-cyclist-id",cyclistId);
    $("#modal-price").modal("show");
});

$("#modal-price button.save").on("click",function(event){
    var cyclistId = $(this).attr("data-cyclist-id");
    if (Number($("#modal-price-price").val()) == 0 || Number($("#modal-price-price").val()) < 50) {
        alert("Price format invalid or minor to 50");
    }
    data = {price: Number($("#modal-price-price").val())};
    $.ajax({
        type: "POST",
        data: data,
        url: window.location.origin + "/race/" + raceId + "/cyclist/" + cyclistId
    }).done(function(data) {
        $("#modal-price").modal("hide");
        readData();
    }).fail(function(){
        alert("Error creating cyclistId: " + cyclistId + " for this race");
    });
});

$(document).on("click","a.unselect-cyclist",function(event){
    var cyclistRaceId = $(this).attr("data-cyclist-id");
    console.log("unselect " + cyclistRaceId);
    $.ajax({
        type: "DELETE",
        url: window.location.origin + "/cyclistRace/" + cyclistRaceId
    }).done(function(data) {
        readData();
    }).fail(function() {
        alert("Could not unselect rider");
    });
});

var readData = function() {
    $.getJSON(window.location.origin + "/race/" + raceId + "/cyclists/non-added").done(function(data){
            $.each(data,function(index,value){
                var obj = {};
                obj.name = value.lastName + ", " + value.firstName;
                obj.id = value.id;
                obj.team = teams[value.teamId];
                obj.action = "<a class='select-cyclist btn btn-primary btn-sm' data-cyclist-id='" + value.id + "'>Select</button>";
                data[index] = obj;
            });
            allCyclists = data;
            if (allCyclistsTable != null) {
                 allCyclistsTable.settings.dataset.originalRecords = allCyclists;
                 allCyclistsTable.process();
            } else {
                allCyclistsTable = $("#all-cyclists").dynatable({dataset: {records: allCyclists}}).data('dynatable');
            }
        });
        $.getJSON(window.location.origin + "/race/" + raceId + "/cyclists" ).done(function(data){
            $.each(data,function(index,value){
                var obj = {};
                obj.price = value.price;
                obj.name = value.cyclist.lastName + ", " + value.cyclist.firstName;
                obj.id = value.cyclist.id;
                obj.team = teams[value.cyclist.teamId];
                obj.action = "<a class='unselect-cyclist btn btn-danger btn-sm' data-cyclist-id='" + value.id + "'>Unselect</button>";
                data[index] = obj;
            });
            selectedCyclists = data;
            if (selectedCyclistsTable != null) {
                selectedCyclistsTable.settings.dataset.originalRecords = selectedCyclists;
                selectedCyclistsTable.process();
            } else {
                selectedCyclistsTable = $("#selected-cyclists").dynatable({dataset: {records: data}}).data('dynatable');
            }

        });
}


